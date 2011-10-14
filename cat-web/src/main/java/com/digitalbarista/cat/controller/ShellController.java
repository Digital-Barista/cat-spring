package com.digitalbarista.cat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class ShellController extends CatController
{

	@Override
  protected ModelAndView init(HttpServletRequest request)
  {
		ModelAndView ret = super.init(request);
		ret.setViewName("shell");
	  return ret;
  }

}
