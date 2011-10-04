package com.digitalbarista.cat.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbarista.cat.data.User;

@Repository
public class UserDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PreAuthorize("hasPermission(#username,'com.digitalbarista.cat.dat.User','read') or hasRole('ROLE_ADMIN')")
	public User getUser(String username)
	{
		return (User)sessionFactory.getCurrentSession().load(User.class, username);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#user.username,'com.digitalbarista.cat.data.User','write')")
	public User save(User user)
	{
		User ret = (User)sessionFactory.getCurrentSession().merge(user);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostFilter("hasPermission(filterObject.username,'com.digitalbarista.cat.data.User','read') or hasRole('ROLE_ADMIN')")
	public List<User> getAllUsers()
	{
		List<User> ret = sessionFactory.getCurrentSession().createCriteria(User.class).list();
		System.out.println("Got "+ret.size()+" items");
		return ret;
	}
}
