package com.digitalbarista.cat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ClientDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PreAuthorize("hasPermission(#id,'com.digitalbarista.cat.data.Client','read') or hasRole('ROLE_ADMIN')")
	public Client getClient(long id)
	{
		return (Client)sessionFactory.getCurrentSession().load(Client.class, id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PreAuthorize("((#client.id==null) and hasRole('ROLE_ADMIN')) or hasPermission(#client,'write')")
	public Client save(Client client)
	{
		return (Client)sessionFactory.getCurrentSession().merge(client);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostFilter("hasPermission(filterObject,'read') or hasRole('ROLE_ADMIN')")
	public List<Client> getAllClients()
	{
		List<Client> ret = sessionFactory.getCurrentSession().createCriteria(Client.class).list();
		System.out.println("Got "+ret.size()+" items");
		return ret;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
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
