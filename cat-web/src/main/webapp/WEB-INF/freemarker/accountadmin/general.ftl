<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />

<div id="account-container">
	
	<ul class="edit-options">
	 <@showEditLine name="Company" value="${currentClient.name!}" contentMacro="showClientFields"  />
	</ul>
  
</div>

<@writeJsonObjects />
<script>
$(document).ready(function(){
  dbi.setupEditLines();
});
</script>