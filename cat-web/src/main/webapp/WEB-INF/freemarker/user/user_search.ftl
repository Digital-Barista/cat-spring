<#include "/macros/json_macros.ftl" />

<div id="user-switcher">
  <form id="switch-user-form" action="<@spring.url '/app/j_spring_security_switch_user' />">
    <input name="j_username" type="hidden" />
  </form>
  
  <div id="user-grid">
  
  </div>
</div>

<script src="<@spring.url '/js/user.js' />"></script>
<@writeJsonObjects />
<script>
  $(document).ready(function(){
    dbi.user.setupUserList();
  });
</script>