package com.digitalbarista.cat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbarista.cat.data.Client;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class ClientDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@PreAuthorize("hasPermission(#id,'com.digitalbarista.cat.data.Client','read') or hasRole('ROLE_ADMIN')")
	@Cacheable(value="clientCache", key="#id")
	public Client getClient(long id)
	{
		return (Client)sessionFactory.getCurrentSession().load(Client.class, id);
	}
	
	@PreAuthorize("((#client.id==null) and hasRole('ROLE_ADMIN')) or hasPermission(#client,'write')")
	@CacheEvict(value="clientCache", key="#client.id")
	public Client save(Client client)
	{
		return (Client)sessionFactory.getCurrentSession().merge(client);
	}
	
	@PostFilter("hasPermission(filterObject,'read') or hasRole('ROLE_ADMIN')")
	public List<Client> getAllClients()
	{
		return sessionFactory.getCurrentSession().createCriteria(Client.class).list();
	}
	
	@Cacheable("clientObjectIDCache")
	public List<ObjectIdentity> getAllClientIdentities()
	{
		List<ObjectIdentity> ret = new ArrayList<ObjectIdentity>();
		for(Client client : getAllClients())
		{
			ret.add(new ObjectIdentityImpl(Client.class,client.getId()));
		}
		return ret;
	}
}
