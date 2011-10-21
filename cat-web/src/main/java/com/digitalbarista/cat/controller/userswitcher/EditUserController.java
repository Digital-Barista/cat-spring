package com.digitalbarista.cat.controller.userswitcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.api.UserService;
import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.data.User;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class EditUserController extends ShellController 
{
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_USER_EDIT)
	public ModelAndView init(HttpServletRequest request, @RequestParam(value="username") String username)
	{
		ModelAndView ret = super.init(request);
    ret.addObject("mainContent", "user/user_edit.ftl");
    if (username != null)
    {
      User user = userService.getUser(username);
      if (user != null)
      {
        ret.addObject("currentUser", user);
        ret.addObject("currentUserJson", serializeToJson(user));
      }
    }
		return ret;
	}
	
}
