<#macro mainNavigation navigation>
  <div id="main-nav">
    <div id="client-nav">
    	<@listNavItems navigation.clientItems />
    </div>
    <div id="admin-nav">
    	<@listNavItems navigation.adminItems />
    </div>
  </div>
</#macro>

<#macro listNavItems items>
  <#list items as item>
    <#local selectedClass = "">
    <#if item.selected>
      <#local selectedClass = "selected">
    </#if>
    <a href="${item.url}" class="main-menu-item ${selectedClass}">
      <span>${item.displayName}</span>
    </a>
  </#list>
</#macro>

<#macro leftNavigation navigation>
  <div id="left-nav">
    <div class="dynamic-content">
      <#if navigation.selectedNavigationItem?? && navigation.selectedNavigationItem.navigationItems??>
        <@listNavItems navigation.selectedNavigationItem.navigationItems />
      </#if>
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