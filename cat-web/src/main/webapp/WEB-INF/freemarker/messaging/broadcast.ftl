<#include "/macros/common_macros.ftl" />
<#include "/macros/messaging_macros.ftl" />

<div id="broadcast">
  <@showMessageFields />
  <textarea class="tinymce"></textarea>
</div>

<#-- Script setup -->
<script src="<@spring.url '/js/messaging/broadcast.js' />"></script>
<script>
$(document).ready(function(){
  dbi.broadcast.setup();
});
</script>