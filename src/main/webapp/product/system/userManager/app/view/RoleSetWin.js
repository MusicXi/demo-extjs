Ext.define('UserManager.view.RoleSetWin',{
	extend:'Ext.window.Window',
	alias:'widget.roleSetWin',
	id:'roleSetWinID',
	closeAction:'hide',
	modal:true,
//	width:600, 	//不定义大小Window将会自适应
//	height:300,
	title:'角色分配',
	initComponent : function() {
		var me=this;
	

		this.callParent([this]);
	},
	items:[{xtype:'roleSelectPanel'}],
	buttonAlign:'center',
	buttons : [{
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		action : 'cancel',
		handler: function(button) {
			button.up('window').hide();
		}
	}],
});