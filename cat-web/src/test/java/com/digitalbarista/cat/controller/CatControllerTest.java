package com.digitalbarista.cat.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.typeCompatibleWith;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.digitalbarista.cat.model.Navigation;
import com.digitalbarista.cat.model.NavigationItem;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/navigation.xml"})
public class CatControllerTest extends AbstractTestNGSpringContextTests {

	private CatController controller;
	
	@BeforeMethod(alwaysRun=true)
	public void setup()
	{
		controller=new CatController(){};
		controller.setNavigation((Navigation)applicationContext.getBean("navigation"));
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		controller=null;
	}
	
	@Test
	public void testInitNavOptions()
	{
		ModelAndView mv = controller.init();
		assertThat("Navigation is not in the model!",mv.getModel().get("navigation"),notNullValue());
		assertThat("Navigation is the wrong type of object!",mv.getModel().get("navigation").getClass(),typeCompatibleWith(Navigation.class));
		
		Navigation nav = (Navigation)mv.getModel().get("navigation");
		
		assertThat("Wrong number of nav Admin Items.",nav.getAdminItems().size(),equalTo(3));

		assertThat("User Switcher nav is wrong or missing",nav.getAdminItems(),hasItem(new NavigationItem("User Switcher","/app/user_switcher")));
		assertThat("System Admin nav is wrong or missing",nav.getAdminItems(),hasItem(new NavigationItem("System Administration","/app/system_admin")));
		assertThat("Account Admin nav is wrong or missing",nav.getAdminItems(),hasItem(new NavigationItem("Account Admin","/app/account_admin")));
		
		assertThat("Wrong number of nav Client Items.",nav.getClientItems().size(),equalTo(5));

		assertThat("Home nav is wrong or missing",nav.getClientItems(),hasItem(new NavigationItem("Home","/app/home")));
		assertThat("Account nav is wrong or missing",nav.getClientItems(),hasItem(new NavigationItem("Account","/app/account")));
		assertThat("Coupons nav is wrong or missing",nav.getClientItems(),hasItem(new NavigationItem("Coupons","/app/coupons")));
		assertThat("Messaging nav is wrong or missing",nav.getClientItems(),hasItem(new NavigationItem("Messaging","/app/messaging")));
		assertThat("Reporting nav is wrong or missing",nav.getClientItems(),hasItem(new NavigationItem("Reporting","/app/reporting")));
	}

	@Test(dependsOnMethods="testInitNavOptions")
	public void testSetSelectedNavigation()
	{
		ModelAndView mv = controller.init();
		Navigation nav = (Navigation)mv.getModel().get("navigation");

		controller.setSelectedNavigation(nav.getAllNavigationItems());
		
		for(NavigationItem item : nav.getAdminItems())
		{
			assertThat("Nav item "+item.getDisplayName()+" was selected, but should not have been.",item.isSelected(),equalTo(false));
		}
		
		for(NavigationItem item : nav.getClientItems())
		{
			if(item.getDisplayName().equals(Navigation.NAV_ITEM_COUPONS))
				assertThat("Nav item Coupons was NOT selected, but SHOULD have been.",item.isSelected(),equalTo(true));
			else
				assertThat("Nav item "+item.getDisplayName()+" was selected, but should not have been.",item.isSelected(),equalTo(false));
		}
	}
}
