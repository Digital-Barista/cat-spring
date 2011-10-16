dbi.Network = Class.extend({
	
	/**
	 * Call network account service
	 */
	getAccounts: function(params){
		var props = $.extend({}, {
			name:undefined,
			success:undefined,
			error:undefined
		}, 
		params);
		
		$.ajax({
			type:'GET',
			dataType:'json',
			url: dbi.getServiceUrl('networkAccounts'),
			success:props.success,
			error:props.error
		});
	},
	
	/**
   * Call save account service
   */
  saveAccount: function(params){
    var props = $.extend({}, {
      data:{},
      success:function(){
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'Network account successfully saved'});
      },
      error:function(){
        dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
            {message:'An error occurred. Please try again.'});
      },
      statusCode:{
        403:function(){
          dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
              {message:'You do not have access to modify this network account'});
        }
      }
    }, 
    params);
    
    $.ajax({
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('networkAccount'),
      data: JSON.stringify(props.data),
      success:props.success,
      error:props.error,
      statusCode:props.statusCode
    });
  }
});

dbi.network = new dbi.Network();