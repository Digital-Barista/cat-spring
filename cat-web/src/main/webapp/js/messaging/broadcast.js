dbi.Broadcast = Class.extend({
  init: function(){
    
  },

  setup: function(){
    this.messageTypeChanged();
    this.setupHtmlEditor();
    $('input[name="messageType"]').click($.proxy(this.handleMessageTypeChanged, this));
  },
  
  setupHtmlEditor: function(){
    $('textarea.tinymce').tinymce({
      // Location of TinyMCE script
      script_url : '/' + dbi.getContext() + '/js/lib/tiny_mce/tiny_mce.js',

      // General options
      theme : "advanced",
      mode: "textareas",
      plugins : "fullscreen,autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

      // Theme options
      theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,formatselect,fontselect,fontsizeselect",
      theme_advanced_buttons2 : "cut,copy,paste,pasteword,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,image,help,code,|,forecolor,backcolor,|,media",
      theme_advanced_buttons3 : "",
      theme_advanced_toolbar_location : "top",
      theme_advanced_toolbar_align : "left",
      theme_advanced_statusbar_location : "bottom",
    });
  },
  
  
  handleMessageTypeChanged: function(){
    this.messageTypeChanged();
  },
  
  messageTypeChanged: function(){
    var couponFields = $('.coupon-field');
    
    if ($('input[name="messageType"]:checked').val() === 'Coupon'){
      couponFields.show();
    }
    else{
      couponFields.hide();
    }
  }
});

dbi.broadcast = new dbi.Broadcast();