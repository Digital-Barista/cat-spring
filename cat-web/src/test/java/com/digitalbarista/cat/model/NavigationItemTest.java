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
		assertThat("Hashcode is not equal when item is.",item1.hashCode(),equalTo(item2.hashCode()));
	}
	
	@Test
	public void testNavItemSameObjEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		assertThat(item1,equalTo(item1));
	}
	
	@Test
	public void testNavItemDifferentClassNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		assert !item1.equals("String") : "NavigationItem should not be equal to an object of a different class.";
	}
	
	@Test
	public void testNavItemVsNullNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		assertThat(item1,not(equalTo(null)));
	}
	
	@Test
	public void testNavItemEqualsNotAffectedBySelectedState()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display","Url");
		item1.setSelected(false);
		item2.setSelected(true);
		assertThat(item1,equalTo(item2));
		assertThat(item2,equalTo(item1));
		assertThat("Hash code is affected by selected state, even though equals isn't.",item1.hashCode(),equalTo(item2.hashCode()));
	}
	
	@Test
	public void testNavItemNameNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("x","Display","Url");
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
	@Test
	public void testNavItemDisplayNameNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","x","Url");
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
	@Test
	public void testNavItemUrlNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display","x");
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
	@Test
	public void testNavItemNameNullNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem(null,"Display","Url");
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
	@Test
	public void testNavItemDisplayNameNullNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name",null,"Url");
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
	@Test
	public void testNavItemUrlNullNotEquals()
	{
		NavigationItem item1 = new NavigationItem("Name","Display","Url");
		NavigationItem item2 = new NavigationItem("Name","Display",null);
		assertThat(item1,not(equalTo(item2)));
		assertThat(item2,not(equalTo(item1)));
	}
}
