<#include "macros/nav_macros.ftl" />
<#include "macros/shell_macros.ftl" />
<!DOCTYPE HTML>
<html>
  <head>
    <link type="text/css" href="/cat/css/main.css" rel="stylesheet">
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