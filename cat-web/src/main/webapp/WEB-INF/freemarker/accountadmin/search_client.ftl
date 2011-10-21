<#include "/macros/account_admin_macros.ftl" />
<#include "/macros/json_macros.ftl" />

<div id="search-client">
  <div id="client-grid">
  
  </div>
</div>

<script src="<@spring.url '/js/account_admin.js' />"></script>
<@writeJsonObjects />
<script>
$(document).ready(function(){
  dbi.accountAdmin.setupClientSearch();
});
</script>