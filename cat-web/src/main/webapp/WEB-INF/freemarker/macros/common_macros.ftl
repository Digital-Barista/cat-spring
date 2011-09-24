<#macro button text id="" href="" class="">
	<#local buttonClass = "" />
	<#if class?has_content>
		<#local buttonClass = class />
	</#if>
	
	<a <#if id?has_content>id="${id}"</#if> <#if href?has_content>href="${href}"</#if> class="button ${buttonClass}">
		<span class="button-content">
			<span class="button-text">${text}</span>
		</span>
	</a>
</#macro>