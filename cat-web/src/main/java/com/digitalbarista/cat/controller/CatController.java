package com.digitalbarista.cat.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
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
  protected ObjectMapper jsonSerializer;
  
	@Autowired
	private ApplicationContextProvider ctxProvider;

	protected ModelAndView init(HttpServletRequest request)
	{
		ModelAndView ret = new ModelAndView();
		ret.addObject("navigation", getNavigation());
		setSelectedNavigation(getNavigation().getAllNavigationItems());
		applyQueryStringParams(getNavigation().getAllNavigationItems(), request);
		return ret;
	}
	
	
	private void applyQueryStringParams(List<NavigationItem> items, HttpServletRequest request)
	{
		if (items != null)
		{
			String clientId = request.getParameter("client_id");
			for (NavigationItem item : items)
			{
				if (clientId != null &&
						item.getUrl() != null)
				{
					item.setUrl(item.getUrl().replace("{client_id}", clientId));
				}
				applyQueryStringParams(item.getNavigationItems(), request);
			}
		}
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
        
        if (item.isSelected())
        {
          return true;
        }
        
				String strippedUrl = item.getUrl();
				if(strippedUrl != null)
				{
					if (strippedUrl.startsWith("/app/"))
					{
						strippedUrl=strippedUrl.substring(5);
					}
					if (strippedUrl.contains("?"))
					{
						strippedUrl = strippedUrl.substring(0, strippedUrl.indexOf("?"));
					}
				}
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
		try
    {
			Method[] methods = getClass().getMethods();
			for (Method method : methods)
			{
		    for (Annotation a : method.getAnnotations())
		    {
		    	if (RequestMapping.class.isAssignableFrom(a.annotationType()))
		    	{
		    		RequestMapping map = (RequestMapping)method.getAnnotation(RequestMapping.class);
		    		if (map.value().length > 0)
		    		{
		    			return map.value()[0];
		    		}
		    	}
		    }
			}
    }
    catch (SecurityException e)
    {
    }
		return null;
	}
	
	public Navigation getNavigation()
  {
	  return this.ctxProvider.getApplicationContext().getBean(Navigation.class);
  }
	
	public String serializeToJson(Object obj)
	{
	  try {
      return jsonSerializer.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException("Error serializing object to JSON: ",e);
    }
	}
}
