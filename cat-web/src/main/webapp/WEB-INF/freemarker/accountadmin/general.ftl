<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />

<div id="account-container">
	
<div id="UserMessages"></div>

	<ul class="edit-options">
	 <@showEditLine name="Company" value="${currentClient.name!}" contentMacro="showClientFields"  />
	</ul>
  
</div>

<script src="<@spring.url '/js/account_admin.js' />"></script>
<@writeJsonObjects />
<script>
$(document).ready(function(){
  dbi.setupEditLines();
  dbi.accountAdmin.setupEditClient();
});
</script>