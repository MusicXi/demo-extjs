Ext.application({
	name: 'RoleManager',  //必须属性,定义其他应用例如Models、View和Controllers的使用。UserManager.controller.UserManager
	controllers:['RoleController'],
	launch: function() {
		Ext.create('Ext.container.Viewport', {
			layout: 'border',
			items:[
				{xtype: 'west', region: 'west'},
				{xtype: 'rolePanel',  region: 'center'}		  
			]		
			
		});
	}

});