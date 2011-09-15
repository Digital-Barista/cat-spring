<#import "spring.ftl" as spring/>
<#include "macros/nav_macros.ftl" />
<#include "macros/shell_macros.ftl" />
<!DOCTYPE HTML>
<html>
  <head>
    <link type="text/css" href="<@spring.url '/css/main.css'/>" rel="stylesheet">
  	<!-- or, instead of using the spring macro, you can hard link to /cat/css/main.css'-->
	<!-- BUT . . . the macro will automatically cope with a different context root. -->
  </head>
  <body>
    <#-- Client specific banner -->
    <@clientBanner />
    
    <#-- Top navigation links -->
    <@mainNavigation />
    
    <#-- Navigation for specific page -->
    <@leftNavigation />
    
    <#-- Template for main body content -->
    <div class="main-content">
      <#include mainContent />
    </div>
  </body>
</html>