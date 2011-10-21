dbi.UserSwitcher = Class.extend({
  
  userGrid:undefined,
  
  init: function(){
    
  },
  
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
  },

  /**
   * Edit button cell formatter
   */
  EditButtonCellFormatter: function(row, cell, value, columnDef, dataContext){
      return '<a class="button " href="/' + dbi.getContext() + '/app/user/edit?username=' + dataContext.username + '">' +
        '<span class="button-content">' +
          '<span class="button-text">Edit</span>' +
        '</span>' +
        '</a>';
  },
  
  SwitchButtonCellFormatter: function(row, cell, value, columnDef, dataContext){
      return '<a class="button " href="javascript:void(0)" data-userId="' + dataContext.username + '">' +
        '<span class="button-content">' +
          '<span class="button-text">Switch</span>' +
        '</span>' +
        '</a>';
  },
});
dbi.userSwitcher = new dbi.UserSwitcher();