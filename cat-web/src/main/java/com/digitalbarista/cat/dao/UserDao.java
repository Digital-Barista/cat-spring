package com.digitalbarista.cat.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.ChildrenExistException;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.User;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class UserDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private MutableAclService aclService;
	
	@Autowired
	private SaltSource salt;

  @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name==#username")
	public User getUser(String username)
	{
		return (User)sessionFactory.getCurrentSession().load(User.class, username);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name==#user.username")
	public User save(User user)
	{
	  User existing = (User)sessionFactory.getCurrentSession().get(User.class,user.getUsername());
    if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new GrantedAuthorityImpl("ROLE_ADMIN")))
    {
      //Not sure how we get here, BUT, if we do . . . you can't make yourself an Admin.
      user.getAuthorities().clear();
      user.getAuthorities().add("ROLE_USER");
    }
	  if(existing==null)
	  {
	    if(user.getAuthorities().size()==0)
	        user.getAuthorities().add("ROLE_USER");
	    sessionFactory.getCurrentSession().save(user);
	  } else {
	    existing.setEnabled(false);
	    //And that's it for now.  Changing password happens elsewhere, and you can't change a username.
	    //Until we have personal information, there's not much to save.
	  }
		return user;
	}
	
  @PostFilter("hasRole('ROLE_ADMIN') or authentication.name.equals(filterObject.username)")
	public List<User> getAllUsers()
	{
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
	
	@Cacheable(value="userReadableClientCache")
	public Set<Long> getAuthorizedClients(String username)
	{
	  List<Sid> userSid = new ArrayList<Sid>();
	  userSid.add(new PrincipalSid(username));
	  Map<ObjectIdentity,Acl> aclList = aclService.readAclsById(clientDao.getAllClientIdentities(), userSid);
	  Set<Long> ret = new HashSet<Long>();
	  for(ObjectIdentity ident : aclList.keySet())
	  {
	    if(!ident.getClass().equals(Client.class))
	      continue;
	    
	    ret.add((Long)ident.getIdentifier());
	  }
	  return ret;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void associateUserWithClient(String username,Long clientId)
	{
	  if(username==null || username.trim().length()==0)
	    throw new IllegalArgumentException("A username must be specified.");
	  
	  if(clientId==null)
	    throw new IllegalArgumentException("A valid client must be specified.");
	  
	  User user = getUser(username);
	  if(user==null)
	    throw new IllegalArgumentException("The specified user does not exist.");
	  
	  Client client = clientDao.getClient(clientId);
	  if(client==null)
	    throw new IllegalArgumentException("The specified client does not exist.");
	  
	  Sid sid = new PrincipalSid(username);
	  List<Sid> sids = new ArrayList<Sid>();
	  sids.add(sid);
	  
	  MutableAcl acl = null;
	  
	  if(user.getClientid()!=null)
	  {
	    try
	    {
	      acl = (MutableAcl) aclService.readAclById(new ObjectIdentityImpl(Client.class,user.getClientid()));
	      for(int loop=0; loop<acl.getEntries().size(); loop++)
	      {
	        if(acl.getEntries().get(loop).getSid().equals(sid))
	        {
	          acl.deleteAce(loop);
	          loop--;
	        }
	      }
	      if(acl.getEntries().size()==0)
	      {
	        aclService.deleteAcl(new ObjectIdentityImpl(Client.class,user.getClientid()), false);
	      } else {
	        aclService.updateAcl(acl);
	      }
	    }
	    catch(NotFoundException e){}
	    catch(ChildrenExistException e){}
	  }
	  
	  acl=null;
	  try
	  {
	    acl = (MutableAcl) aclService.readAclById(new ObjectIdentityImpl(Class.class,clientId));
	  }catch (NotFoundException e){}
	  
	  if(acl==null)
	      acl = aclService.createAcl(new ObjectIdentityImpl(Client.class,clientId));
    acl.insertAce(0, BasePermission.READ, new PrincipalSid(username), true);
    aclService.updateAcl(acl);
    
    user.setClientid(clientId);
	}
}
