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
      success:undefined,
      error:undefined
    }, 
    params);
    
    $.ajax({
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('client'),
      data: JSON.stringify(props.data),
      success:props.success,
      error:props.error
    });
  }
});

dbi.client = new dbi.Client();