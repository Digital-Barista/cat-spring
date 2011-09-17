package com.digitalbarista.cat.model;

import java.util.List;

public class Navigation
{
	public static final String NAV_ITEM_HOME = "home";
	public static final String NAV_ITEM_MESSAGING = "messaging";
	public static final String NAV_ITEM_COUPONS = "coupons";
	public static final String NAV_ITEM_REPORTING = "reporting";
	public static final String NAV_ITEM_ACCOUNT = "account";
	public static final String NAV_ITEM_USER_SWITCHER = "user_switcher";
	public static final String NAV_ITEM_SYSTEM_ADMIN = "system_admin";
	public static final String NAV_ITEM_ACCOUNT_ADMIN = "account_admin";

	private List<NavigationItem> clientItems;
	private List<NavigationItem> adminItems;

	public List<NavigationItem> getClientItems()
	{
		return clientItems;
	}

	public void setClientItems(List<NavigationItem> clientItems)
	{
		this.clientItems = clientItems;
	}

	public List<NavigationItem> getAdminItems()
	{
		return adminItems;
	}

	public void setAdminItems(List<NavigationItem> adminItems)
	{
		this.adminItems = adminItems;
	}
}
