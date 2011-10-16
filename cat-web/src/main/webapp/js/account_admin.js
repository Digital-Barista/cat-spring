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
		 * Save a client
		 */
		saveClient: function(){
		  var data = dbi.getInputValues($('#client-info'));
		  dbi.clearUserMessages();
			dbi.client.saveClient({
			  data:data
			});
		},

    /**
     * Save a network account
     */
    saveNetworkAccount: function(element){
      var self = this;
      var data = dbi.getInputValues(element);
      dbi.clearUserMessages();
      dbi.network.saveAccount({
        data:data,
        success:function(response){
          dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
              {message:'Network account successfully saved'});
          if (!data.id){
            self.appendCreatedAccount(element, response.response.respObj.NetworkAccount);
          }
          element.removeClass('editing');
        }
      });
    },
    
    /**
     * Add the newly created account to the list
     */
    appendCreatedAccount: function(element, account){
      var newLine = element.clone();
      
      // Clear the new account fields
      this.populateNetworkAccount(element, {clientId:account.clientId});
      newLine.find('.edit-link .name').html(element.find('.edit-link .name').html());
      newLine.find('.edit-link .edit-button').html('Edit');
      
      // Replace all classes with 'edit-line'
      newLine.attr('class', 'edit-line');
      element.before(newLine);
      this.populateNetworkAccount(newLine, account);
      dbi.setupEditLines(newLine);
    },
    
    /**
     * Populate fields of a network account
     */
    populateNetworkAccount: function(element, account){
      element.find('.edit-link .name').html(account.accountName);
      element.find('.edit-link .value').html(account.description);
      element.find('input[name="id"]').val(account.id);
      element.find('input[name="clientId"]').val(account.clientId);
      element.find('input[name="accountName"]').val(account.accountName);
      element.find('input[name="description"]').val(account.description);
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
      dbi.setupEditLines();
      
			if (client){
			  var info = $('#client-info');
        info.find('input[name="id"]').val(client.id);
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
		  $('.save-button').click($.proxy(this.handleSaveClient, this));
		  $('.cancel-button').click($.proxy(this.handleCancelEdit, this));
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
     * Initialize network account page
     */
    setupNetworkAccounts: function(){
      dbi.setupEditLines();
      $('.cancel-button').click($.proxy(this.handleCancelEdit, this));
      $('.save-button').click($.proxy(this.handleSaveNetwork, this));
    },
		
		/**
		 * Save client click
		 */
		handleSaveClient: function(event){
			event.preventDefault();
			this.saveClient();
		},
		
		/**
		 * Cancel client edit
		 */
		handleCancelEdit: function(event){
			event.preventDefault();
      dbi.clearUserMessages();
			$(event.target).closest('.edit-line').removeClass('editing');
		},
		
		/**
		 * Save network account click
		 */
		handleSaveNetwork: function(event){
      event.preventDefault();
      dbi.clearUserMessages();
      this.saveNetworkAccount($(event.target).closest('.edit-line'));
		}
});
dbi.accountAdmin = new dbi.AccountAdmin();