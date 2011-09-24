package com.digitalbarista.cat.controller.accountadmin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class GeneralController extends ShellController 
{

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_ACCOUNT_ADMIN_GENERAL)
	public ModelAndView init()
	{
		ModelAndView ret = super.init();
		return ret;
	}
	
}
