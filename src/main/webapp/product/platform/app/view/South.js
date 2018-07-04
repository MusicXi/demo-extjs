Ext.define('Framework.view.South', {
	extend : 'Ext.toolbar.Toolbar',
	region : 'south',
	alias : 'widget.south',
	cls:'platform_banner_south',
	items : [ {
		xtype : 'button',
		text : '注销',
		action : 'logout',
		handler: function(btn) {
		/*	btn.up('south').fireEvent('logout');*/
		}
	},{
		xtype : 'button',
		text : '修改密码',
		action : 'logout',
		handler: function(btn) {
			changePass();
		}
	}, '->', {
		xtype : 'label',
		name:'currentUser',
		tpl:'当前登录人: {currentUser}'
	} ],
	
	html:'南区域'
});