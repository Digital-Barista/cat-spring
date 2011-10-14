<#include "/macros/common_macros.ftl" />

<#macro writeJsonObjects>
  <script>
    <#if clientList??>
      dbi.client.clientList = ${clientList};
    </#if>
    
    <#if currentClientJson??>
      dbi.client.currentClient = ${currentClientJson};
    </#if>
  </script>
</#macro>

<#macro showClientFields>
	<div id="client-info">
     <table>
      <tr>
        <td class="label">Account Type</td>
        <td>
          <select>
            <option></option>
            <option>Broadcaster</option>
            <option>Reseller</option>
          </select>
        </td>
      </tr>
      <tr><td class="label">Name</td><td><input name="name" type="text" /></td></tr>
      <tr><td class="label">Address 1</td><td><input name="address1" type="text" /></td></tr>
      <tr><td class="label">Address 2</td><td><input name="address2" type="text" /></td></tr>
      <tr><td class="label">City</td><td><input name="city" type="text" /></td></tr>
      <tr><td class="label">State</td><td><input name="state" type="text" /></td></tr>
      <tr><td class="label">Zip</td><td><input name="zip" type="text" /></td></tr>
      <tr><td class="label">Contact Name</td><td><input name="contactName" type="text" /></td></tr>
      <tr><td class="label">Contact Phone</td><td><input name="contactPhone" type="text" /></td></tr>
      <tr><td class="label">Contact Email</td><td><input name="contactEmail" type="text" /></td></tr>
      <tr>
        <td></td>
        <td>
          <@button id="save-button" href="javascript:void(0)" text="Save" />
          <@button id="cancel-button" href="javascript:void(0)" text="Cancel" />
        </td>
      </tr>
    </table>
	</div>
</#macro>

