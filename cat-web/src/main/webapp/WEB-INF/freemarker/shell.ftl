<#import "spring.ftl" as spring/>
<#include "macros/nav_macros.ftl" />
<#include "macros/shell_macros.ftl" />
<!DOCTYPE HTML>
<html>
  <head>
    <link type="text/css" href="<@spring.url '/css/main.css'/>" rel="stylesheet" />
  	<!-- or, instead of using the spring macro, you can hard link to /cat/css/main.css'-->
	<!-- BUT . . . the macro will automatically cope with a different context root. -->
	
    <#-- Shared scripts -->
    <script src="<@spring.url '/js/lib/jquery-1.6.4.min.js' />"></script>
    <script src="<@spring.url '/js/lib/jquery.event.drag-2.0.min.js' />"></script>
    <script src="<@spring.url '/js/lib/slick.core.js' />"></script>
    <script src="<@spring.url '/js/lib/slick.grid.js' />"></script>
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