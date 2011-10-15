<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />

<div id="UserMessages"></div>

<@showClientFields />

<#-- Script setup -->
<script src="<@spring.url '/js/account_admin.js' />"></script>
<script>
$(document).ready(function(){
  dbi.accountAdmin.setupCreateClient();
});
</script>