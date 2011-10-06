package com.digitalbarista.cat.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public User getUser(String username)
	{
		return (User)sessionFactory.getCurrentSession().load(User.class, username);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User save(User user)
	{
		User ret = (User)sessionFactory.getCurrentSession().merge(user);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> getAllUsers()
	{
		List<User> ret = sessionFactory.getCurrentSession().createCriteria(User.class).list();
		return ret;
	}
}
