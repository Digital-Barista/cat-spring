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

<#macro showEditLine name value contentMacro>
  <li class="edit-line">
    <a href="javascript:void(0)">
      <span class="name">${name}</span>
      <span class="value">${value}</span>
      <span class="edit-button">Edit</span>
    </a>
    <div class="editable">
      <@.vars[contentMacro] />
    </div>
  </li>
</#macro>