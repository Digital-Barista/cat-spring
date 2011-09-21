package com.digitalbarista.cat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@Controller
public abstract class CatController
{
	private Navigation navigation;
	private NavigationItem selectedNavigationItem;

	protected ModelAndView init()
	{
		ModelAndView ret = new ModelAndView();
		ret.addObject("navigation", navigation);
		return ret;
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
					selectedNavigationItem = item;
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


	public NavigationItem getSelectedNavigationItem()
  {
  	return selectedNavigationItem;
  }


	public void setSelectedNavigationItem(NavigationItem selectedNavigationItem)
  {
  	this.selectedNavigationItem = selectedNavigationItem;
  }
	
}
