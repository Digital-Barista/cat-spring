dbi.Client = Class.extend({
	
	/**
	 * Call client service
	 */
	getClients: function(params){
		var props = $.extend({}, {
			name:undefined,
			success:undefined,
			error:undefined
		}, 
		params);
		
		$.ajax({
			type:'GET',
			dataType:'json',
			url: dbi.getServiceUrl('client'),
			success:props.success,
			error:props.error
		});
	},
	
	/**
   * Call save client service
   */
  saveClient: function(params){
    var props = $.extend({}, {
      data:{},
      success:function(){
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'Client successfully saved'});
      },
      error:function(){
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'An error occurred. Please try again.'});
      },
      statusCode:{
        403:function(){
          dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
              {message:'You do not have access to modify this client'});
        }
      }
    }, 
    params);
    
    $.ajax({
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('client'),
      data: JSON.stringify(props.data),
      success:props.success,
      error:props.error,
      statusCode:props.statusCode
    });
  }
});

dbi.client = new dbi.Client();