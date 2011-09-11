package com.digitalbarista.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, value="/helloWorld.form")
	public ModelAndView helloWorld()
	{
		ModelAndView ret = new ModelAndView();
		ret.setViewName("helloWorld");
		return ret;
	}
	
}
