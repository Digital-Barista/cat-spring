/**
 * The main object all functions/objects in this application
 * should be namespaced under
 */
var dbi = $.extend({}, dbi, {
	
	/**
	 * Get the application context.
	 * TODO: Do this dynamically
	 */
	getContext: function(){
		return 'cat2';
	},
	
	/**
	 * Get a service URL by name of service
	 */
	getServiceUrl: function(service){
		var ret = '/' + dbi.getContext() + '/api/';
		
		switch(service){
		case 'client':
			ret += 'clients';
			break;
		}
		return ret;
	},
	
	/**
	 * Get an object with all input/select values in
	 * it that are not empty
	 */
	getInputValues: function(element){
	  var value, input, sel, data = {};
	  $(element).find(':input[name]').each(function(){
	     input = $(this);
	     if (input.is(':checkbox')){
	       data[input.attr('name')] = input.is(':checked');
	     } 
	     else if (input.is(':radio')){
	       if (input.is(":checked")) {
	         data[input.attr('name')] = input.val();
	       }
	     } 
	     else{
	       value = input.prop('value');
	       if (value && value.length > 0){
	         data[input.attr('name')] = input.prop('value');
	       }
	     }
	     
	  });
	  
	  $(element).find('select').each(function(){
	    sel = $(this);
	    value = sel.val();
	    
	    if (value && value.length > 0){
	      data[sel.attr('name')] = value;
	    }
	  });
	  
	  return data;
	}
});