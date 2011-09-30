(function(){
	
	/**
	 * Create a new client
	 */
	function createClient(){
	  var data = dbi.getInputValues($('#client-info'));
		dbi.client.saveClient({
		  data:data,
			success:function(response){
				console.log(response);
			}
		});
	}
	
	/**
	 * Setup page events
	 */
	function setup(){
		// Setup search buttons
		$('#create-button').click(function(e){
			e.preventDefault();
			createClient();
		});
	}
	
	setup();
})();