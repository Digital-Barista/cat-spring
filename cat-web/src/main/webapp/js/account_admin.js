(function(){
	var account = {
		/**
		 * Grid of clients
		 */
		clientGrid:undefined,
		
		/**
		 * Create a new client
		 */
		createClient: function(){
		  var data = dbi.getInputValues($('#client-info'));
			dbi.client.saveClient({
			  data:data,
				success:function(response){
					console.log(response);
				}
			});
		},
		
		/**
		 * Initialize client slick grid
		 */
		setupClientListGrid: function(){
			var columns = [
			   			{id:"name", name:"Client", field:"name", width:"250"},
			   			{id:"contactName", name:"Contact Name", field:"contactName", width:"450"}
			   		];
			var options = {
					enableCellNavigation: true,
		            enableColumnReorder: false
				};
			this.clientGrid = new Slick.Grid("#client-grid", dbi.client.clientList, columns, options);
			$("#client-grid").show();
		},
		
		/**
		 * Create client click
		 */
		handleCreate: function(event){
			event.preventDefault();
			this.createClient();
		},
		
		/**
		 * Setup page events
		 */
		setup: function(){
			$('#create-button').click($.proxy(this.handleCreate, this));
			this.setupClientListGrid();
		}
	};
	
	account.setup();
})();