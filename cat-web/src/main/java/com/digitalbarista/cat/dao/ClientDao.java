package com.digitalbarista.cat.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbarista.cat.data.Client;

@Repository
public class ClientDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@PreAuthorize("hasPermission(#id,'com.digitalbarista.cat.data.Client','read') or hasRole('ROLE_ADMIN')")
	public Client getClient(long id)
	{
		return (Client)sessionFactory.getCurrentSession().load(Client.class, id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PreAuthorize("((client.id==null) and hasRole('ROLE_ADMIN')) or hasPermission(#client,'write')")
	public Client save(Client client)
	{
		sessionFactory.getCurrentSession().save(client);
		return client;
	}
	
	@PostFilter("hasPermission(filterObject,'read') or hasRole('ROLE_ADMIN')")
	public List<Client> getAllClients()
	{
		List<Client> ret = sessionFactory.getCurrentSession().createCriteria(Client.class).list();
		System.out.println("Got "+ret.size()+" items");
		return ret;
	}
}
