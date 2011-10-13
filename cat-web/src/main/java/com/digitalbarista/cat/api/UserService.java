package com.digitalbarista.cat.api;

import javax.validation.Valid;

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
	public User saveUser(@RequestBody @Valid User user)
	{
		return userDao.save(user);
	}
	
	@RequestMapping(value="/users/{username}/assign/{clientid}",method={RequestMethod.POST})
	public void associate(@PathVariable("username") String username, @PathVariable("clientid") Long clientid)
	{
	  userDao.associateUserWithClient(username, clientid);
	}
}
