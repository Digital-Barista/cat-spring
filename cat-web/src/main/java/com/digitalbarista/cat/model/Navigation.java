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
	public static final String NAV_ITEM_MESSAGING_TAG = "messaging/tag";
	public static final String NAV_ITEM_MESSAGING_TEMPLATE = "messaging/template";
	
	public static final String NAV_ITEM_COUPON = "coupon";
	public static final String NAV_ITEM_REPORTING = "reporting";
	public static final String NAV_ITEM_USER_SWITCHER = "user_switcher";
	public static final String NAV_ITEM_SYSTEM_ADMIN = "system_admin";

	public static final String NAV_ITEM_ACCOUNT = "account";
	public static final String NAV_ITEM_ACCOUNT_CONTACT = "account/contact";
	public static final String NAV_ITEM_ACCOUNT_FACEBOOK = "account/facebook";
	public static final String NAV_ITEM_ACCOUNT_TWITTER = "account/twitter";
	public static final String NAV_ITEM_ACCOUNT_BILLING = "account/billing";
	
	public static final String NAV_ITEM_ACCOUNT_ADMIN = "account_admin";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_GENERAL = "account_admin/general";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_BILLING = "account_admin/billing";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_USER = "account_admin/user";
	public static final String NAV_ITEM_ACCOUNT_ADMIN_SOCIAL = "account_admin/social_media_account";

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
