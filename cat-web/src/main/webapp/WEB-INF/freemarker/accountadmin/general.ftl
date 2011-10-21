<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />
<#include "/macros/json_macros.ftl" />

<div id="account-container">
	

	<ul class="edit-options">
	 <@showEditLine name="Company" value="${currentClient.name!}">
	   <@showClientFields />
	 </@showEditLine>
	</ul>
  
</div>

<script src="<@spring.url '/js/account_admin.js' />"></script>
<@writeJsonObjects />
<script>
$(document).ready(function(){
  dbi.accountAdmin.setupEditClient();
});
</script>