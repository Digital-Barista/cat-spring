package com.digitalbarista.cat.controller.accountadmin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.api.ClientService;
import com.digitalbarista.cat.api.NetworkAccountService;
import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class SocialController extends ShellController 
{
  @Autowired
  private ClientService clientService;
  
	@Autowired
	private NetworkAccountService networkSerivce;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_ACCOUNT_ADMIN_SOCIAL)
	public ModelAndView init(HttpServletRequest request, @RequestParam(value="client_id") Long clientId)
	{
		ModelAndView ret = super.init(request);

    Client client = clientService.getClient(clientId);
    ret.addObject("currentClient", client);
    ret.addObject("mainContent", "accountadmin/social_account.ftl");
    ret.addObject("accountList", networkSerivce.getAllNetworkAccounts());
		return ret;
	}
	
}
