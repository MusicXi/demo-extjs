Ext.define('agencymonitor2.view.EditAgencyMonitor', {
	extend : 'Ext.form.Panel',
	alias : 'widget.agencyedit',
	frame : true,
	split : true,
	autoShow : false,
	id : 'agencyedit',
	layout : 'column',
	fieldDefaults : {
		labelAlign : 'left',
		labelWidth : 90,
		margin : '3 5 3 10'
	},
	items : [ {
		xtype : 'textfield',
		name : 'id',
		//hidden : true,
		fieldLabel : '主键'
	}, {
		xtype : 'textfield',
		name : 'agencyname',
		allowBlank : false,
		columnWidth : 1 / 3,
		fieldLabel : '代理人姓名'
	},  {
		xtype : 'datefield',
		// hideTrigger : true,
		// readOnly : true,
		name : 'agencydatebegin',
		format : 'Y-m-d H:i:s',
		submitFormat : 'c',
		columnWidth : 1 / 3,
		fieldLabel : '代理开始时间'
	}, {
		xtype : 'datefield',
		// hideTrigger : true,
		// readOnly : true,
		name : 'agencydateend',
		format : 'Y-m-d H:i:s',
		submitFormat : 'c',
		columnWidth : 1 / 3,
		fieldLabel : '代理结束时间'
	}, {
		xtype : 'textfield',
		name : 'agencystatus',
		hidden : true,
		fieldLabel : '是否被执行'
	}, {
		xtype : 'datefield',
		// hideTrigger : true,
		// readOnly : true,
		name : 'happendate',
		format : 'Y-m-d H:i:s',
		submitFormat : 'c',
		columnWidth : 1 / 3,
		hidden : true,
		fieldLabel : '启用时间'
	} ],
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		action : 'cancel'
	} ]
});
