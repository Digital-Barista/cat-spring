dbi.AccountAdmin = Class.extend({
		/**
		 * Grid of clients
		 */
		clientGrid:undefined,
		
		/**
		 * Consturctor
		 */
		init: function(){
		  
		},
		
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
		 * Edit button cell formatter
		 */
		EditButtonCellFormatter: function(row, cell, value, columnDef, dataContext){
	      return '<a class="button " href="/' + dbi.getContext() + '/app/account_admin/general?client_id=' + dataContext.id + '">' +
	        '<span class="button-content">' +
	          '<span class="button-text">Edit</span>' +
	        '</span>' +
	        '</a>';
		},
		
		/**
		 * Setup the create client button
		 */
		setupCreateClient: function(){
		  $('#create-button').click($.proxy(this.handleCreate, this));
		},
		
		/**
		 * Initialize client slick grid
		 */
		setupClientListGrid: function(){
			var columns = [
			   			{id:"name", name:"Client", field:"name", width:150},
			   			{id:"contactName", name:"Contact Name", field:"contactName", width:450},
              {id:"edit", field:"Edit", width:80, formatter:this.EditButtonCellFormatter},
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
		}
});
dbi.accountAdmin = new dbi.AccountAdmin();