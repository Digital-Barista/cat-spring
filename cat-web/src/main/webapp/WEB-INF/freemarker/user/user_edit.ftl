<#include "/macros/json_macros.ftl" />
<#include "/macros/user_macros.ftl" />

<div id="user-edit" class="edit-line">
  <@displayUserFields />
</div>

<script src="<@spring.url '/js/user.js' />"></script>
<script src="<@spring.url '/js/user_switcher.js' />"></script>
<@writeJsonObjects />
<script>
  $(document).ready(function(){
  });
</script>