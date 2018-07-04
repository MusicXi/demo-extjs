Ext.define('agencymonitor2.view.NewAgencyMonitor', {
	extend : 'Ext.form.Panel',
	alias : 'widget.agencyadd',
	frame : true,
	split : true,
	autoShow : false,
	id : 'agencyadd',
	layout : 'column',
	fieldDefaults : {
		labelAlign : 'left',
		labelWidth : 90
		/*margin : '3 5 3 10'*/
	},
	items : [ {
		xtype : 'textfield',
		name : 'id',
		//hidden : true,
		fieldLabel : '主键'
	},  {
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
	} ],
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		action : 'cancel'
	} ]
});
