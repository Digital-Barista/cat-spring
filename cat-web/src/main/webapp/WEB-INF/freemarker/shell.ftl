<#import "spring.ftl" as spring/>
<#include "macros/nav_macros.ftl" />
<#include "macros/shell_macros.ftl" />
<!DOCTYPE HTML>
<html>
  <head>
    <link type="text/css" href="<@spring.url '/css/main.css'/>" rel="stylesheet" />
  	<!-- or, instead of using the spring macro, you can hard link to /cat/css/main.css'-->
	<!-- BUT . . . the macro will automatically cope with a different context root. -->
    <script src="<@spring.url '/js/jquery-1.6.4.min.js' />"></script>
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
    </diiv>
  </body>
</html>