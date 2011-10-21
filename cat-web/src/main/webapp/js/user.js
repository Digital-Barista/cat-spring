dbi.User = Class.extend({
  
  userGrid:undefined,
  
  init: function(){
    
  },
  
  /**
   * Setup the user search list
   */
  setupUserList: function(){
    var columns = [
            {id:"username", name:"User", field:"username", width:250},
            {id:"authorities", name:"Roles", field:"authorities", width:390},
            {id:"enabled", name:"Enabled", field:"enabled", width:80, cssClass:'enabled-cell', formatter:BoolCellFormatter},
            {id:"edit", field:"Edit", width:100, formatter:this.EditButtonCellFormatter},
            {id:"switch", field:"Switch", width:100, formatter:this.SwitchButtonCellFormatter},
          ];
    var options = {
        enableCellNavigation: true,
        enableColumnReorder: false
      };
    this.userGrid = new Slick.Grid("#user-grid", dbi.user.userList, columns, options);
    $("#user-grid").show();
    
    // Setup switch buttons
    $('#user-grid .switch-button').click($.proxy(this.handleSwitchClick, this));
  },
  

  /**
   * Setup the add user page
   */
  setupAddUser: function(){
    $('.save-button').click($.proxy(this.handleSaveUser, this));
  },

  /**
   * Setup the edit user page
   */
  setupEditUser: function(){
    $('.save-button').click($.proxy(this.handleSaveUser, this));
  },
  
  /**
   * Handle save user click event
   */
  handleSaveUser: function(event){
    event.preventDefault();
    var data = dbi.getInputValues($('.user-info'));
    dbi.clearUserMessages();
    this.saveUser({
      data:data,
      success:function(response){
        this.currentUser = response.response.respObj.User;
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'User successfully saved'});
      }
    });
  },
  
  handleSwitchClick: function(event){
    event.preventDefault();
    var username = $(event.currentTarget).data('username');
    var form = $('#switch-user-form');
    form.find('input[name="j_username"]').val(username);
    form.submit();
  },
  
  /**
   * Call user service
   */
  getUsers: function(params){
    var props = $.extend({}, {
      type:'GET',
      dataType:'json',
      url: dbi.getServiceUrl('user'),
    }, 
    params);
    
    $.ajax(props);
  },
  
  /**
   * Call user service
   */
  saveUser: function(params){
    var props = $.extend({}, {
      data:{},
      type:'POST',
      dataType:'json',
      contentType: 'application/json',
      url: dbi.getServiceUrl('user'),
      success:function(){
        dbi.dispatchEvent(dbi.events.SUCCESS_MESSAGE, 
            {message:'User successfully saved'});
      },
      error:function(){
        dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
            {message:'An error occurred. Please try again.'});
      },
      statusCode:{
        403:function(){
          dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
              {message:'You do not have access to modify this user'});
        },
        500:function(){
          dbi.dispatchEvent(dbi.events.FAIL_MESSAGE, 
              {message:'Some fields are invalid'});
        }
      }
    }, 
    params);
    
    props.data = JSON.stringify(props.data);
    $.ajax(props);
  },
  
  /**
   * Edit button cell formatter
   */
  EditButtonCellFormatter: function(row, cell, value, columnDef, dataContext){
      return '<a class="button" href="/' + dbi.getContext() + '/app/user/edit?username=' + dataContext.username + '">' +
        '<span class="button-content">' +
          '<span class="button-text">Edit</span>' +
        '</span>' +
        '</a>';
  },
  
  SwitchButtonCellFormatter: function(row, cell, value, columnDef, dataContext){
      return '<a class="button switch-button" data-username="' + dataContext.username + '" href="/' + dbi.getContext() + '/app/j_spring_security_switch_user/' + dataContext.username + '">' +
        '<span class="button-content">' +
          '<span class="button-text">Switch</span>' +
        '</span>' +
        '</a>';
  },
});
dbi.user = new dbi.User();