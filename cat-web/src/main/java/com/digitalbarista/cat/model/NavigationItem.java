package com.digitalbarista.cat.model;

public class NavigationItem
{
	private boolean selected;
	private String name;
	private String displayName;
	private String url;

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
	
	
}
