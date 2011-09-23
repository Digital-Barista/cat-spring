package com.digitalbarista.cat.controller.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class CouponController extends ShellController 
{

	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_COUPON)
	public ModelAndView init()
	{
		ModelAndView ret = super.init();
		return ret;
	}
	
}
