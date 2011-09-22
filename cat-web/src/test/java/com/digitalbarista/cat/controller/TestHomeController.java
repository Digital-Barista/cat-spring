package com.digitalbarista.cat.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/cat-web-servlet.xml",
		 						 "file:src/main/webapp/WEB-INF/navigation.xml"})
public class TestHomeController extends AbstractTestNGSpringContextTests {

	private HomeController controller;
	
	@BeforeMethod(alwaysRun=true)
	public void setup()
	{
		controller = applicationContext.getBean(HomeController.class);
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		controller=null;
	}
	
	@Test
	public void testViewSelectedCorrectly()
	{
		ModelAndView mv = controller.init();
		assertThat("View was set wrong.",mv.getViewName(),equalTo("shell"));
	}
	
	@Test
	public void testModelPopulatedCorrectly()
	{
		ModelAndView mv = controller.init();
		assertThat("Main Content template was not set correctly",(String)mv.getModel().get("mainContent"),equalTo("home.ftl"));
	}
	
	@Test
	public void testNavSetCorrectly()
	{
		ModelAndView mv = controller.init();
		Navigation nav = (Navigation)mv.getModel().get("navigation");
		
		NavigationItem homeItem = null;
		for(NavigationItem item : nav.getClientItems())
		{
			if(item.getUrl().equals("/app/home"))
			{
				homeItem=item;
				break;
			}
		}
		
		assertThat("Home nav couldn't be found.",homeItem,notNullValue());
		assertThat("Home nav was not selected.",homeItem.isSelected(),equalTo(true));
	}
}
