package com.digitalbarista.cat.model;

import java.util.List;

public class NavigationItem
{
	private boolean selected;
	private String name;
	private String displayName;
	private String url;
	private List<NavigationItem> navigationItems;

	public NavigationItem()
	{
	}

	public NavigationItem(String name, String displayName, String url)
	{
		this.name = name;
		this.displayName = displayName;
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
		    + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NavigationItem other = (NavigationItem) obj;
		if (displayName == null)
		{
			if (other.displayName != null)
				return false;
		}
		else if (!displayName.equals(other.displayName))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (url == null)
		{
			if (other.url != null)
				return false;
		}
		else if (!url.equals(other.url))
			return false;
		return true;
	}

	public List<NavigationItem> getNavigationItems()
  {
  	return navigationItems;
  }

	public void setNavigationItems(List<NavigationItem> navigationItems)
  {
  	this.navigationItems = navigationItems;
  }

}
