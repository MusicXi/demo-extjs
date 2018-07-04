Ext.define('agencymonitor2.view.Edit', {
	extend : 'Ext.window.Window',
	alias : 'widget.edit',
	modal : true,
	width : 800,
	height : 300,
	autoShow : true,
	title : '修改',
	resizable : false,
	items : [ {
		xtype : 'agencyadd',
		height: 200,
		margin : 5
	} ]
});
