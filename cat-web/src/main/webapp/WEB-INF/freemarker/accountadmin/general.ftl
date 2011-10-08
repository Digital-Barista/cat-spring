<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />

<div id="account-container">
	
	<ul class="edit-options">
	 <@showEditLine name="Company" value="DBI" contentMacro="showClientFields"  />
   <@showEditLine name="Account Type" value="Broadcast" contentMacro="showClientFields" />
	</ul>
  
</div>

<script>
$(document).ready(function(){
  dbi.setupEditLines();
});
</script>