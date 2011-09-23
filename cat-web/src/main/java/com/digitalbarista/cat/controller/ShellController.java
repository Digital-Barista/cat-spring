package com.digitalbarista.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class ShellController extends CatController
{

	@Override
  protected ModelAndView init()
  {
		ModelAndView ret = super.init();
		ret.setViewName("shell");
	  return ret;
  }

}
