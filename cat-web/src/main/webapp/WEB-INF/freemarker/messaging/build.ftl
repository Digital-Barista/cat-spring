<#include "/macros/common_macros.ftl" />
<#include "/macros/messaging_macros.ftl" />

<div id="build">
  <div id="workspace">
  </div>
</div>

<#-- Script setup -->
<script src="<@spring.url '/js/messaging/build.js' />"></script>
<script>
$(document).ready(function(){
  dbi.build.setup();
});
</script>