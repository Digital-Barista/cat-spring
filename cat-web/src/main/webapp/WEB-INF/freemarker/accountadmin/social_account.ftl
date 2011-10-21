<#include "/macros/account_admin_macros.ftl" />
<#include "/macros/network_account_macros.ftl" />
<#include "/macros/json_macros.ftl" />

<div id="network-account">
  <ul id="accounts">
    <@showEditLine "Facebook" "">
      <@displayFacebookEditLines currentClient />
    </@showEditLine>
    <@showEditLine "Twitter" "">
      <@displayTwitterEditLines currentClient />
    </@showEditLine>
  </ul>
</div>

<script src="<@spring.url '/js/account_admin.js' />"></script>
<@writeJsonObjects />
<script>
$(document).ready(function(){
  dbi.accountAdmin.setupNetworkAccounts();
});
</script>