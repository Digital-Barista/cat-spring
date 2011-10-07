<#import "spring.ftl" as spring/>
<#include "macros/nav_macros.ftl" />
<#include "macros/shell_macros.ftl" />
<!DOCTYPE HTML>
<html>
  <head>
    <link type="text/css" href="<@spring.url '/css/main.css'/>" rel="stylesheet" />
    <link type="text/css" href="<@spring.url '/css/slick.grid.css'/>" rel="stylesheet" />
    <link type="text/css" href="<@spring.url '/css/jquery-ui-1.8.5.custom.css'/>" rel="stylesheet" />
    <link type="text/css" href="<@spring.url '/css/slick-default-theme.css'/>" rel="stylesheet" />
    <link type="text/css" href="<@spring.url '/css/account_admin.css'/>" rel="stylesheet" />
	
    <#-- Shared scripts -->
    <script src="<@spring.url '/js/lib/jquery-1.6.4.min.js' />"></script>
    <script src="<@spring.url '/js/lib/jquery.event.drag-2.0.min.js' />"></script>
    <script src="<@spring.url '/js/lib/slick.core.js' />"></script>
    <script src="<@spring.url '/js/lib/slick.grid.js' />"></script>
    <script src="<@spring.url '/js/lib/slick.editors.js' />"></script>
    <script src="<@spring.url '/js/lib/class.js' />"></script>
    <script src="<@spring.url '/js/shell.js' />"></script>
    <script src="<@spring.url '/js/client.js' />"></script>
    
  </head>
  <body>
    <div id="body-wrapper">
      <#-- Client specific banner -->
      <@clientBanner />
      
      <#-- Top navigation links -->
      <@mainNavigation navigation />
    
      <#-- Navigation for specific page -->
      <@leftNavigation navigation />
      
      <#-- Template for main body content -->
      <div id="main-content">
      	<#if mainContent??>
        	<#include mainContent />
        </#if>
      </div>
    </div>
    
  </body>
</html>