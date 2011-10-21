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

<#macro showEditLine name="" value="" editButtonText="Edit" editLineClass="">
  <li class="edit-line ${editLineClass}">
    <a class="edit-link" href="javascript:void(0)">
      <span class="name">${name}</span>
      <span class="value">${value}</span>
      <span class="edit-button">${editButtonText}</span>
    </a>
    <div class="editable">
      <#nested />
    </div>
  </li>
</#macro>

<#function getObject object="">
  <#if object?has_content>
    <#return object />
  <#else>
    <#return {} />
  </#if>
</#function>

<#function springUrl relativeUrl>
  <#return springMacroRequestContext.getContextPath() + relativeUrl />
</#function>