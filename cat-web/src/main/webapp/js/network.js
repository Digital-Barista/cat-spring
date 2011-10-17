dbi.Network = Class.extend({
	
	/**
	 * Call network account service
	 */
	getAccounts: function(params){
		var props = $.extend({}, {
		  type:'GET',
      dataType:'json',
      url: dbi.getServiceUrl('networkAccounts')
		}, 
		params);
		
		$.ajax(props);
	},
	
	/**
   * Call save account service
   */
  saveAccount: function(params){
    var props = $.extend({}, {
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('networkAccount'),
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
    
    props.data = JSON.stringify(props.data);
    $.ajax(props);
  }
});

dbi.network = new dbi.Network();