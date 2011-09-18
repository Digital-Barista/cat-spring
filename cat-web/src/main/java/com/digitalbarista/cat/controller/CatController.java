package com.digitalbarista.cat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@Controller
public abstract class CatController
{
	private Navigation navigation;

	protected ModelAndView init()
	{
		ModelAndView ret = new ModelAndView();
		setupNavigation();
		ret.addObject("navigation", navigation);
		return ret;
	}
	
	protected void setupNavigation()
	{
		navigation = new Navigation();
		navigation.setClientItems(new ArrayList<NavigationItem>());
		navigation.setAdminItems(new ArrayList<NavigationItem>());
		
		NavigationItem home = new NavigationItem();
		home.setName(Navigation.NAV_ITEM_HOME);
		home.setDisplayName("Home");
		home.setUrl("home");
		navigation.getClientItems().add(home);
		
		NavigationItem messaging = new NavigationItem();
		messaging.setName(Navigation.NAV_ITEM_MESSAGING);
		messaging.setDisplayName("Messaging");
		messaging.setUrl("messaging");
		navigation.getClientItems().add(messaging);
		
		NavigationItem coupons = new NavigationItem();
		coupons.setName(Navigation.NAV_ITEM_COUPONS);
		coupons.setDisplayName("Coupons");
		coupons.setUrl("coupons");
		navigation.getClientItems().add(coupons);
		
		NavigationItem reporting = new NavigationItem();
		reporting.setName(Navigation.NAV_ITEM_REPORTING);
		reporting.setDisplayName("Reporting");
		reporting.setUrl("reporting");
		navigation.getClientItems().add(reporting);
		
		NavigationItem account = new NavigationItem();
		account.setName(Navigation.NAV_ITEM_ACCOUNT);
		account.setDisplayName("Account");
		account.setUrl("account");
		navigation.getClientItems().add(account);

		
		NavigationItem userSwitcher = new NavigationItem();
		userSwitcher.setName(Navigation.NAV_ITEM_USER_SWITCHER);
		userSwitcher.setDisplayName("User Switcher");
		userSwitcher.setUrl("user_switcher");
		navigation.getAdminItems().add(userSwitcher);

		
		NavigationItem systemAdmin = new NavigationItem();
		systemAdmin.setName(Navigation.NAV_ITEM_SYSTEM_ADMIN);
		systemAdmin.setDisplayName("System Administration");
		systemAdmin.setUrl("system_admin");
		navigation.getAdminItems().add(systemAdmin);

		
		NavigationItem accountAdmin = new NavigationItem();
		accountAdmin.setName(Navigation.NAV_ITEM_ACCOUNT_ADMIN);
		accountAdmin.setDisplayName("Account Admin");
		accountAdmin.setUrl("account_admin");
		navigation.getAdminItems().add(accountAdmin);
	}
	
	protected void setSelectedNavigation(String navItemName)
	{
		if (navItemName != null &&
				navigation != null)
		{
			List<NavigationItem> items = new ArrayList<NavigationItem>();
			items.addAll(navigation.getClientItems());
			items.addAll(navigation.getAdminItems());
			
			for (NavigationItem item : items)
			{
				if (navItemName.equals(item.getName()))
				{
					item.setSelected(true);
					break;
				}
			}
		}
	}
	
	public Navigation getNavigation()
  {
  	return navigation;
  }

	public void setNavigation(Navigation navigation)
  {
  	this.navigation = navigation;
  }
	
}