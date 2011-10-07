

<div id="search-client">
  <div id="client-grid">
  
  </div>
</div>

<script src="<@spring.url '/js/account_admin.js' />"></script>
<script>
$(document).ready(function(){
  dbi.client.clientList = ${clientList};
  dbi.accountAdmin.setupClientListGrid();
});
</script>