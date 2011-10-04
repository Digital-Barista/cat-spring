package com.digitalbarista.cat.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;
import com.digitalbarista.cat.util.ApplicationContextProvider;

@Controller
public abstract class CatController
{

	@Autowired
	private ApplicationContextProvider ctxProvider;

	protected ModelAndView init()
	{
		ModelAndView ret = new ModelAndView();
		ret.addObject("navigation", getNavigation());
		setSelectedNavigation(getNavigation().getAllNavigationItems());
		return ret;
	}
	
	
	protected boolean setSelectedNavigation(List<NavigationItem> items)
	{
		String navItemName = getUrl();
		if (navItemName != null &&
				items != null)
		{
			for (NavigationItem item : items)
			{
        item.setSelected(setSelectedNavigation(item.getNavigationItems())); 
        
				String strippedUrl = item.getUrl();
				if(strippedUrl!=null && strippedUrl.startsWith("/app/"))
					strippedUrl=strippedUrl.substring(5);			
				if (navItemName.equals(strippedUrl))
				{
					item.setSelected(true);
					return true;
				}
			}
		}
		return false;
	}
	
	private String getUrl()
	{
		String ret = null;
		try
    {
			Method method = this.getClass().getMethod("init");
	    for (Annotation a : method.getAnnotations())
	    {
	    	if (a.annotationType() == RequestMapping.class)
	    	{
	    		RequestMapping map = (RequestMapping)method.getAnnotation(RequestMapping.class);
	    		if (map.value().length > 0)
	    		{
	    			ret = map.value()[0];
	    		}
	    	}
	    }
    }
    catch (SecurityException e)
    {
    }
    catch (NoSuchMethodException e)
    {
    }
		return ret;
	}
	
	public Navigation getNavigation()
  {
	  return this.ctxProvider.getApplicationContext().getBean(Navigation.class);
  }
}
