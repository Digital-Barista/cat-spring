<#include "/macros/common_macros.ftl" />

<#macro displayUserFields>
  <#local user = getObject(currentUser) />
  
   <table>
    <tr>
      <td class="label">Enabled</td>
      <td>
        <input class="enabled" type="checkbox" <#if user.enabled?? && user.enabled>checked</#if> />
      </td>
    </tr>
    <tr><td class="label">Username</td><td><input name="name" type="text" value="${user.username!}" /></td></tr>
    <tr><td class="label">Password</td><td><input name="password" type="password" /></td></tr>
    <tr><td class="label">Confirm Password</td><td><input name="confirm" type="password" /></td></tr>
    <tr>
      <td></td>
      <td>
        <@button class="save-button" href="javascript:void(0)" text="Save" />
        <@button class="cancel-button" href="javascript:void(0)" text="Cancel" />
      </td>
    </tr>
  </table>
</#macro>