<#include "/macros/json_macros.ftl" />

<div id="user-switcher">
  <div id="user-grid">
  
  </div>
</div>

<script src="<@spring.url '/js/user.js' />"></script>
<script src="<@spring.url '/js/user_switcher.js' />"></script>
<@writeJsonObjects />
<script>
  $(document).ready(function(){
    dbi.userSwitcher.setupUserList();
  });
</script>