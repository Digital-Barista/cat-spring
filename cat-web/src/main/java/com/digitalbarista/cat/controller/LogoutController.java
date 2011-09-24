package com.digitalbarista.cat.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitalbarista.cat.model.Navigation;

@Controller
public class LogoutController {

	@RequestMapping(value=Navigation.NAV_ITEM_LOGOUT)
	public void logout()
	{
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
}
