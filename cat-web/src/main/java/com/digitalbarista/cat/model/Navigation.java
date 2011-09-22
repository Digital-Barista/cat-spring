package com.digitalbarista.cat.model;

import java.util.ArrayList;
import java.util.List;

public class Navigation
{
	public static final String NAV_ITEM_HOME = "home";
	
	public static final String NAV_ITEM_MESSAGING = "messaging";
	public static final String NAV_ITEM_MESSAGING_CAMPAIGN_LIST = "messaging/campaign_list";
	public static final String NAV_ITEM_MESSAGING_BUILD_CAMPAIGN = "messaging/build_campaign";
	public static final String NAV_ITEM_MESSAGING_BROADCAST = "messaging/broadcast";
	public static final String NAV_ITEM_MESSAGING_TAGS = "messaging/tags";
	public static final String NAV_ITEM_MESSAGING_TEMPLATES = "messaging/templates";
	
	public static final String NAV_ITEM_COUPONS = "coupons";
	public static final String NAV_ITEM_REPORTING = "reporting";
	public static final String NAV_ITEM_ACCOUNT = "account";
	public static final String NAV_ITEM_USER_SWITCHER = "user_switcher";
	public static final String NAV_ITEM_SYSTEM_ADMIN = "system_admin";
	
	public static final String NAV_ITEM_ACCOUNT_ADMIN = "account_admin";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_GENERAL = "account_admin/general";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_BILLING = "account_admin/billing";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_USERS = "account_admin/users";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_SOCIAL = "account_admin/social_media_accounts";

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
	
	public List<NavigationItem> getAllNavigationItems()
	{
		List<NavigationItem> ret = new ArrayList<NavigationItem>();
		ret.addAll(clientItems);
		ret.addAll(adminItems);
		return ret;
	}
}
