<#include "/macros/common_macros.ftl" />
<#include "/macros/account_admin_macros.ftl" />

<div id="account-container">
	<div class="search-box">
		<span>Company:</span>
		<select id="Client">
			<#list clientList as client>
				<option>${client.name}</option>
			</#list>
		</select>
	</div>
	
	<@showClientFields />
	<div class="spacer"></div>
  <@button id="create-button" href="javascript:void(0)" text="Create" />
	
  <script src="<@spring.url '/js/account_admin.js' />"></script>
</div>