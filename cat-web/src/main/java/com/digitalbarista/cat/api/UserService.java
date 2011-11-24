package com.digitalbarista.cat.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	private PasswordEncoder pwdEncoder = new ShaPasswordEncoder();
	
	@Autowired
	private SaltSource saltSource;
	
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
    if(user.getPassword()!=null)
    {
      if(!user.getPassword().equals(user.getConfirm()))
        throw new IllegalArgumentException("The password and confirmation don't match.");
      boolean isAdmin=false;
      for(GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities())
      {
        if(authority.getAuthority().equals("ROLE_ADMIN"))
        {
          isAdmin=true;
          break;
        }
      }
      if(isAdmin || user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        user.setEncryptedPassword(pwdEncoder.encodePassword(user.getPassword(), user.getUsername()+"DBI-Rules"));
    }
	  user.setPassword(null);
	  user.setConfirm(null);
	  return userDao.save(user);
	}
	
	@RequestMapping(value="/users/{username}/assign/{clientid}",method={RequestMethod.POST})
	public void associate(@PathVariable("username") String username, @PathVariable("clientid") Long clientid)
	{
	  userDao.associateUserWithClient(username, clientid);
	}
}
