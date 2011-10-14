package com.digitalbarista.cat.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.NetworkAccount;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class NetworkAccountDao {

  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @PostAuthorize("hasRole('ROLE_ADMIN') or (returnValue?.clientid!=null and hasPermission(returnValue?.clientid,'com.digitalbarista.cat.data.Client','read'))")
  public NetworkAccount getNetworkAccount(long id)
  {
    return (NetworkAccount)sessionFactory.getCurrentSession().load(NetworkAccount.class, id);
  }

  @PostFilter("hasRole('ROLE_ADMIN') or (filterObject?.clientid!=null and hasPermission(filterObject?.clientid,'com.digitalbarista.cat.data.Client','read'))")
  public List<NetworkAccount> getAllNetworkAccounts()
  {
    return sessionFactory.getCurrentSession().createCriteria(NetworkAccount.class).list();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN') or (#account.clientid!=null and hasPermission(#account.clientid,'com.digitalbarista.cat.data.Client','administration'))")
  public NetworkAccount save(NetworkAccount account)
  {
    NetworkAccount netAccount = null;
    if(account.getId()!=null)
      netAccount = (NetworkAccount)sessionFactory.getCurrentSession().get(NetworkAccount.class, account.getId());
    if(netAccount==null)
    {
      sessionFactory.getCurrentSession().save(account);
    } else {
      netAccount.setDescription(account.getDescription());  //Description is really the only thing that can change.
      if(account.getCredentials()!=null)
        netAccount.setCredentials(account.getCredentials());
    }
    return netAccount;
  }
}
