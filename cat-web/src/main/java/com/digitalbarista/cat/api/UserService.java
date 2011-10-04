package com.digitalbarista.cat.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digitalbarista.cat.dao.UserDao;
import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.User;

@Controller
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/users",method={RequestMethod.GET})
	public List<User> getAllUsers()
	{
		return userDao.getAllUsers();
	}
	
	@RequestMapping(value="/users/{username}",method={RequestMethod.GET})
	public User getUser(@PathVariable String username)
	{
		return userDao.getUser(username);
	}
	
	@RequestMapping(value="/users",method={RequestMethod.POST})
	public User saveUser(@RequestBody @Valid User user)
	{
		return userDao.save(user);
	}
}
