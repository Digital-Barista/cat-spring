package com.digitalbarista.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitalbarista.cat.model.Navigation;

@Controller
public class LoginController {

	@RequestMapping(value=Navigation.NAV_ITEM_LOGIN)
	public String goToLogin()
	{
		return "login";
	}
	
	@RequestMapping(value=Navigation.NAV_ITEM_LOGIN+"/afterlogout")
	public String goToRelogin()
	{
		return "logout";
	}
}
