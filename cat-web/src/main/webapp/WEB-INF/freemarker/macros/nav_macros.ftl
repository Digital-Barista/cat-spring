<#macro mainNavigation navigation>
  <div id="main-nav">
    <div id="client-nav">
    	<@listNavItems navigation.clientItems />
    </div>
    <div id="admin-nav">
    	<@listNavItems navigation.adminItems />
	    <a id="logout" href="<@spring.url '/app/logout'/>" class="main-menu-item">
	      <span>Logout</span>
	    </a>
    </div>
  </div>
</#macro>

<#macro listNavItems items>
  <#list items as item>
    <#if item.displayName??>
      <#local selectedClass = "">
      <#if item.selected>
        <#local selectedClass = "selected">
      </#if>
      <a href="<@spring.url item.url/>" class="main-menu-item ${selectedClass}">
        <span>${item.displayName}</span>
      </a>
    </#if>
  </#list>
</#macro>

<#macro listLeftNavItems items>
  <#list items as item>
  
    <#if item.displayName??>
      <#local selectedClass = "">
      <#if item.selected>
        <#local selectedClass = "selected">
      </#if>
      <li class="left-menu-item ${selectedClass}">
        <a href="<@spring.url item.url/>">
          <div class="icon"></div>
          <span>${item.displayName}</span>
        </a>
      </li>
    </#if>
    
    <#-- List child items -->
    <#if item.selected && item.navigationItems??>
      <@listLeftNavItems item.navigationItems />
    </#if>
  </#list>
</#macro>

<#macro leftNavigation navigation>
  <div id="left-nav">
    <div class="dynamic-content">
      <#list navigation.allNavigationItems as item>
        <#if item.selected && item.navigationItems??>
          <ul>
            <@listLeftNavItems item.navigationItems />
          </ul>
        </#if>
      </#list>
    </div>
    <div class="persistent-content">
      <ul>
        <li class="left-menu-item">
          <a href="http://digitalbarista.com">
            <span>Digital Barista</span>
          </a>
        </li>
        <li class="left-menu-item">
          <a href="help">
            <span>Help</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</#macro>