Ext.define("RoleManager.view.West",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.west',
	cls : 'platform_banner_west',
	//layout : 'accordion',
	width : 180,
	maxWidth : 180,
	split : true,
	collapsible : true,
	title : '组织机构',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'treepanel',
		//id:'orgTree',
		//title:'第2页',
		//expanded: true,
		rootVisible:false,		//定义根节点是否见
		border: false,
		store:'Organization'
		
	},

});
