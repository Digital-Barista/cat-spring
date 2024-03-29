package com.digitalbarista.cat.controller.messaging;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class BuildCampaignController extends ShellController 
{

	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_MESSAGING_BUILD_CAMPAIGN)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView ret = super.init(request);
		ret.addObject("mainContent", "messaging/build.ftl");
		return ret;
	}
	
}
