<#include "/macros/json_macros.ftl" />
<#include "/macros/user_macros.ftl" />

<div id="user-edit" class="user-info edit-line">
  <@displayUserFields />
</div>

<script src="<@spring.url '/js/user.js' />"></script>
<@writeJsonObjects />
<script>
  $(document).ready(function(){
    dbi.user.setupEditUser();
  });
</script>