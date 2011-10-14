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
		 * Setup the create client buttons
		 */
		setupCreateClient: function(){
		  $('#save-button').click($.proxy(this.handleSaveClient, this));
		},
		
		/**
		 * Setup the edit client buttons
		 */
		setupEditClient: function(){
      var client = dbi.client.currentClient;
			if (client){
			  var info = $('#client-info');
			  info.find('input[name="name"]').val(client.name);
        info.find('input[name="address1"]').val(client.address1);
        info.find('input[name="address2"]').val(client.address2);
        info.find('input[name="city"]').val(client.city);
        info.find('input[name="state"]').val(client.state);
        info.find('input[name="zip"]').val(client.zip);
        info.find('input[name="contactName"]').val(client.contactName);
        info.find('input[name="contactPhone"]').val(client.contactPhone);
        info.find('input[name="contactEmail"]').val(client.contactEmail);
			}
		  $('#save-button').click($.proxy(this.handleSaveClient, this));
		  $('#cancel-button').click($.proxy(this.handleCancelEdit, this));
		},
		
		/**
		 * Initialize client slick grid
		 */
		setupClientSearch: function(){
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
		 * Save client click
		 */
		handleSaveClient: function(event){
			event.preventDefault();
			this.saveClient();
		},
		
		handleCancelEdit: function(event){
			event.preventDefault();
			$(event.target).closest('.edit-line').removeClass('editing');
		}
});
dbi.accountAdmin = new dbi.AccountAdmin();