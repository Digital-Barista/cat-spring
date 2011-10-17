<#include "/macros/common_macros.ftl" />

<#macro displayFacebookEditLines currentClient>
  <ul class="facebook-accounts">
    <#list accountList as account>
      <#if account.type == "Facebook">
        <@showEditLine account.accountName account.description>
          <@displayFacebookFields
            id=account.id
            clientId=currentClient.id
            accountName=account.accountName
            description=account.description />
        </@showEditLine>
      </#if>
    </#list>
    <@showEditLine "Add New Facebook App" "" "" "add-new-account">
      <@displayFacebookFields
            clientId=currentClient.id />
    </@showEditLine>
  </ul>
</#macro>

<#macro displayTwitterEditLines currentClient>
  <ul class="twitter-accounts">
    <#list accountList as account>
      <#if account.type == "Twitter">
      
        <@showEditLine account.accountName account.description>
          <@displayTwitterFields 
            id=account.id
            clientId=currentClient.id 
            accountName=account.accountName 
            description=account.description
            authorized=account.authorized />
        </@showEditLine>
      </#if>
    </#list>
    <@showEditLine "Add Twitter Account" "" "" "add-new-account">
      <@displayTwitterFields 
            clientId=currentClient.id />
    </@showEditLine>
  </ul>
</#macro>

<#macro displayFacebookFields id="" clientId="" accountName="" description="" appId="" apiKey="" secret="">
  <table class="facebook-fields" >
    <input name="id" type="hidden" value="${id}" />
    <input name="clientId" type="hidden" value="${clientId}" />
    <input name="type" type="hidden" value="Facebook" />
    <tr><td>Name</td><td><input name="accountName" type="text" value="${accountName}" /></td></tr>
    <tr><td>Description</td><td><input name="description" type="text" value="${description}" /></td></tr>
    <tr><td>Application ID</td><td><input name="appId" type="text" value="${appId}"/></td></tr>
    <tr><td>API Key</td><td><input name="apiKey" type="text" value="${apiKey}"/></td></tr>
    <tr><td>Application Secret</td><td><input name="secret" type="text" value="${secret}" /></td></tr>
    <tr>
      <td></td>
      <td>
        <@button class="save-button" href="javascript:void(0)" text="Save" />
        <@button class="cancel-button" href="javascript:void(0)" text="Cancel" />
      </td>
    </tr>
  </table>
</#macro>

<#macro displayTwitterFields id="" clientId="" accountName="" description="" authorized=false>
  <#local active = 'Inactive <a class="activate" href="javascript:void(0)">(Activate)</a>' />
  <#if authorized == true>
    <#local active = "Active" />
  </#if>
  
  <table class="twitter-fields">
    <input name="id" type="hidden" value="${id}" />
    <input name="clientId" type="hidden" value="${clientId}" />
    <input name="type" type="hidden" value="Twitter" />
    <tr><td>Twitter Account</td><td><input name="accountName" type="text" value="${accountName}" /></td></tr>
    <tr><td>Description</td><td><input name="description" type="text" value="${description}" /></td></tr>
    
    <#if id?has_content>
      <tr><td>Status</td><td>${active}</td></tr>
    </#if>
    
    <tr>
      <td></td>
      <td>
        <@button class="save-button" href="javascript:void(0)" text="Save" />
        <@button class="cancel-button" href="javascript:void(0)" text="Cancel" />
      </td>
    </tr>
  </table>
</#macro>