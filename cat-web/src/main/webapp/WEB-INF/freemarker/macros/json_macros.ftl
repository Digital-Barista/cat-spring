<#macro writeJsonObjects>
  <script>
    <#if clientListJson??>
      dbi.client.clientList = ${clientListJson};
    </#if>
    
    <#if currentClientJson??>
      dbi.client.currentClient = ${currentClientJson};
    </#if>
    
    <#if userListJson??>
      dbi.user.userList = ${userListJson};
    </#if>
  </script>
</#macro>