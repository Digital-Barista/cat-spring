package com.digitalbarista.cat.controller.accountadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.api.ClientService;
import com.digitalbarista.cat.controller.ShellController;
import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.model.Navigation;

@Controller
public class GeneralController extends ShellController 
{
  @Autowired
  private ClientService clientService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value=Navigation.NAV_ITEM_ACCOUNT_ADMIN_GENERAL)
	public ModelAndView init(@RequestParam(value="client_id") Long clientId)
	{
		ModelAndView ret = super.init();
    ret.addObject("mainContent", "accountadmin/general.ftl");
    
    if (clientId != null)
    {
      Client client = clientService.getClient(clientId);
      if (client != null)
      {
        ret.addObject("currentClient", client);
        ret.addObject("currentClientJson", serializeToJson(client));
      }
    }
		return ret;
	}
	
}
