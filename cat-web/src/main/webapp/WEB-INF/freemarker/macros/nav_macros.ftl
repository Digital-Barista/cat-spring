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
    <a href="<@spring.url item.url/>" class="main-menu-item ${selectedClass}">
      <span>${item.displayName}</span>
    </a>
  </#list>
</#macro>

<#macro listLeftNavItems items>
  <ul>
    <#list items as item>
      <#local selectedClass = "">
      <#if item.selected>
        <#local selectedClass = "selected">
      </#if>
      <li>
        <a href="<@spring.url item.url/>" class="main-menu-item ${selectedClass}">
          <span>${item.displayName}</span>
        </a>
      </li>
    </#list>
  </ul>
</#macro>

<#macro leftNavigation navigation>
  <div id="left-nav">
    <div class="dynamic-content">
      <#list navigation.clientItems as item>
        <#if item.selected && item.navigationItems??>
          <@listLeftNavItems item.navigationItems />
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