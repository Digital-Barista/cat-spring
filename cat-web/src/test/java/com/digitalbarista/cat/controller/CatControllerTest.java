package com.digitalbarista.cat.controller;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/cat-web-servlet.xml",
								 "file:src/main/webapp/WEB-INF/navigation.xml",
								 "file:src/test/resources/test-dao.xml"})
public class CatControllerTest extends AbstractTestNGSpringContextTests {

	@Test
	public void allControllerQuickTest() throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Method m = CatController.class.getDeclaredMethod("getUrl");
		m.setAccessible(true);
		
		Map<String,Object> controllers = applicationContext.getBeansWithAnnotation(Controller.class);
		for(Object controller : controllers.values())
		{
			if(!CatController.class.isAssignableFrom(controller.getClass()))
				continue;
			CatController cc = (CatController)controller;
			ModelAndView mv = cc.init();
			
			assertThat(mv.getModel().get("navigation"),not(nullValue()));
			
			Navigation nav = (Navigation)mv.getModel().get("navigation");
			
			String controllerUrl="/app/"+m.invoke(cc)+"/";
			for(NavigationItem item : nav.getAllNavigationItems())
			{
				if(item.isSelected())
				{
					assertThat(controllerUrl,startsWith(item.getUrl()+"/"));
				} else {
					assertThat(controllerUrl,not(startsWith(item.getUrl()+"/")));
				}
			}
		}
	}
}
