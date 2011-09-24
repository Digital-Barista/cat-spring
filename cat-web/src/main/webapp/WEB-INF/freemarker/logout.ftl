<#import "spring.ftl" as spring/>

<#include "macros/common_macros.ftl" />

<html>
<head>
  <title>Login Page</title>
  <link type="text/css" href="<@spring.url '/css/main.css'/>" rel="stylesheet" />
  <link type="text/css" href="<@spring.url '/css/logout.css'/>" rel="stylesheet" />
</head>

<body> 

<div id="logout">
  <h1>You have successfully logged out</h1>
  <@button text="Login" href="${springMacroRequestContext.getContextPath()}/app/login" />
</div>

</body>

</html>