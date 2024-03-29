/**
 * The main object all functions/objects in this application
 * should be namespaced under
 */
var dbi = $.extend({}, dbi, {
	
  global: window,
  
  events: {
    SUCCESS_MESSAGE:'dbi.events.SUCCESS_MESSAGE',
    FAIL_MESSAGE:'dbi.events.FAIL_MESSAGE'
  },
  
  /**
   * Dispatch an event that can be listened to
   * globally
   */
  dispatchEvent: function(eventName, data){
    $(dbi.global).trigger(eventName, data);
  },
  
  /**
   * Listen to a global event
   */
  listen: function(eventName, callback){
    var events = typeof eventName == 'string' ? [eventName] : eventName;
    for (var i = 0, L = events.length; i < L; i++) {
      $(this.global).bind(events[i], callback);
    }
  },
  
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
		var ret = '/' + dbi.getContext();
		
		switch(service){
  		case 'client':
  			ret += '/api/clients';
  			break;
      case 'networkAccount':
        ret += '/api/network-accounts';
        break;
      case 'user':
        ret += '/api/users';
        break;
      case 'switchUser':
        ret += '/app/j_spring_security_switch_user';
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
	},
	
	setupEditLines: function(element){
		$('.edit-link', element).click(function(e){
			$(this).closest('.edit-line')
			  .toggleClass('editing')
			  .find('.edit-line')
			  .removeClass('editing');
		});
	},
	
	clearUserMessages: function(){
	  $('#UserMessages').empty();
	},
	
	setButtonsLoading: function(element, isLoading){
	  var buttons = element.find('.button');
	  if (isLoading){
	    buttons.hide();
	    if (buttons.length > 0){
	      $(buttons[0]).before($('<div class="button-loading"></div>'));
	    }
	  }
	  else{
	    buttons.show();
	    element.find('.button-loading').remove();
	  }
	}
});

// Listen to global events
dbi.listen(dbi.events.FAIL_MESSAGE, function(event, data){
  $('#UserMessages')
    .removeClass('fail')
    .addClass('success')
    .html(data.message);
});

dbi.listen(dbi.events.FAIL_MESSAGE, function(event, data){
  $('#UserMessages')
    .removeClass('success')
    .addClass('fail')
    .html(data.message);
});