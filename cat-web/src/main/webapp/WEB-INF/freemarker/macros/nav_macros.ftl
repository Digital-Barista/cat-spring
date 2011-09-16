<#macro mainNavigation>
  <div id="main-nav">
    <div id="client-nav">
      <#list navigation.clientItems as item>
        <#local selectedClass = "">
        <#if item.selected>
          <#local selectedClass = "selected">
        </#if>
        <a href="${item.url}" class="main-menu-item ${selectedClass}">
          <span>${item.displayName}</span>
        </a>
      </#list>
    </div>
    <div id="admin-nav">
      <a href="user-switcher" class="main-menu-item">
        <span>User Switcher</span>
      </a>
      <a href="system-admin" class="main-menu-item">
        <span>System Administration</span>
      </a>
      <a href="account-admin" class="main-menu-item">
        <span>Account Administration</span>
      </a>
    </div>
  </div>
</#macro>

<#macro leftNavigation>
  <div id="left-nav">
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
</#macro>