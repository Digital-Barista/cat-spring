package com.digitalbarista.cat.controller.userswitcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.api.UserService;
import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class AddUserController extends ShellController 
{
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_USER_ADD)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView ret = super.init(request);
    ret.addObject("mainContent", "user/user_add.ftl");
    ret.addObject("userListJson", serializeToJson(userService.getAllUsers()));
		return ret;
	}
	
}
