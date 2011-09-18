package com.digitalbarista.cat.model;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class NavigationItemTest {

	@Test
	public void testNavItemEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display","Url");
		assertThat(item1,equalTo(item2));
	}
	
	@Test
	public void testNavItemEqualsNotAffectedBySelectedState()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display","Url");
		item1.setSelected(false);
		item2.setSelected(true);
		assertThat(item1,equalTo(item2));
	}
	
	@Test
	public void testNavItemNameNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("x","Display","Url");
		assertThat(item1,not(equalTo(item2)));
	}
	@Test
	public void testNavItemDisplayNameNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","x","Url");
		assertThat(item1,not(equalTo(item2)));
	}
	@Test
	public void testNavItemUrlNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display","x");
		assertThat(item1,not(equalTo(item2)));
	}
}
