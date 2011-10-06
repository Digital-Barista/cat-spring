package com.digitalbarista.cat.api;

import org.hibernate.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digitalbarista.cat.dao.UserDao;
import com.digitalbarista.cat.data.User;
import com.digitalbarista.cat.util.SerializableList;

@Controller
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/users",method={RequestMethod.GET})
	public SerializableList<User> getAllUsers()
	{
		return new SerializableList<User>(userDao.getAllUsers());
	}
	
	@RequestMapping(value="/users/{username}",method={RequestMethod.GET})
	public User getUser(@PathVariable String username)
	{
		return userDao.getUser(username);
	}
	
	@RequestMapping(value="/users",method={RequestMethod.POST})
	@Valid
	public User saveUser(@RequestBody User user)
	{
		return userDao.save(user);
	}
}
