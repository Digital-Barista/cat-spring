dbi.User = Class.extend({
	
	/**
	 * Call user service
	 */
	getUsers: function(params){
		var props = $.extend({}, {
      type:'GET',
      dataType:'json',
      url: dbi.getServiceUrl('client'),
		}, 
		params);
		
		$.ajax(props);
	},
	
	/**
   * Call user service
   */
  saveUser: function(params){
    var props = $.extend({}, {
      data:{},
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('client'),
      success:function(){
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'Client successfully saved'});
      },
      error:function(){
        dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
            {message:'An error occurred. Please try again.'});
      },
      statusCode:{
        403:function(){
          dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
              {message:'You do not have access to modify this client'});
        }
      }
    }, 
    params);
    
    props.data = JSON.stringify(props.data);
    $.ajax(props);
  }
});

dbi.user = new dbi.User();