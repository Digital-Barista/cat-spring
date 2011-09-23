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

import com.digitalbarista.cat.controller.messaging.MessagingController;
import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/cat-web-servlet.xml",
		 						 "file:src/main/webapp/WEB-INF/navigation.xml"})
public class TestMessagingController extends AbstractTestNGSpringContextTests {

	private MessagingController controller;
	
	@BeforeMethod(alwaysRun=true)
	public void setup()
	{
		controller = applicationContext.getBean(MessagingController.class);
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
	
//	@Test
//	public void testModelPopulatedCorrectly()
//	{
//		ModelAndView mv = controller.init();
//		assertThat("Main Content template was not set correctly",(String)mv.getModel().get("mainContent"),equalTo("home.ftl"));
//	}
	
	@Test
	public void testNavSetCorrectly()
	{
		ModelAndView mv = controller.init();
		Navigation nav = (Navigation)mv.getModel().get("navigation");
		
		NavigationItem messagingItem = null;
		for(NavigationItem item : nav.getClientItems())
		{
			if(item.getUrl().equals("/app/messaging"))
			{
				messagingItem=item;
				break;
			}
		}
		
		assertThat("Messaging nav couldn't be found.",messagingItem,notNullValue());
		assertThat("Messaging nav was not selected.",messagingItem.isSelected(),equalTo(true));
	}
}
