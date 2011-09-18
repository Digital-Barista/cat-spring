package com.digitalbarista.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.model.Navigation;

@Controller
public class MessagingController extends CatController 
{

	@RequestMapping(method=RequestMethod.GET, value="/messaging")
	public ModelAndView init()
	{
		ModelAndView ret = super.init();
		ret.setViewName("shell");
		setSelectedNavigation(Navigation.NAV_ITEM_MESSAGING);
		return ret;
	}
	
}