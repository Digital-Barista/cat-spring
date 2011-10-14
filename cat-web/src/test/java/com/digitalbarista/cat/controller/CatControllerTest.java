package com.digitalbarista.cat.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;
import com.digitalbarista.cat.utils.test.WebContextTestExecutionListener;

@ContextConfiguration(locations={"file:src/test/resources/test-dao.xml",
								  "file:src/main/webapp/WEB-INF/cat-web-servlet.xml",
								 "file:src/main/webapp/WEB-INF/navigation.xml",
								 "file:src/test/resources/test-spring-security.xml"})
@TestExecutionListeners({ 
    WebContextTestExecutionListener.class, 
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class })
public class CatControllerTest extends AbstractTestNGSpringContextTests {

  @BeforeMethod(alwaysRun=true)
  public void setBlankCredentials()
  {
      Authentication auth = new TestingAuthenticationToken("test","test");
      auth.setAuthenticated(true);
      SecurityContextHolder.getContext().setAuthentication(auth);
  }
  
	@Test(enabled=false)
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
			ModelAndView mv = cc.init(new MockHttpServletRequest());
			
			assertThat(mv.getModel().get("navigation"),not(nullValue()));
			
			Navigation nav = (Navigation)mv.getModel().get("navigation");
			
			String controllerUrl="/app/"+m.invoke(cc)+"/";
			if(controllerUrl.equals("/app/null/"))
			{
			  LogManager.getLogger(getClass()).error("Controller "+cc.getClass().getName()+" is returning null.");
			  continue;
			}
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
