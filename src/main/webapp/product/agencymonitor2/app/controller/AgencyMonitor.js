Ext.define('agencymonitor2.controller.AgencyMonitor', {
	extend : 'Ext.app.Controller',
	views : [ 'Query', 'List', 'Edit', 'NewAgencyMonitor',  'EditAgencyMonitor' ],// 声明该控制层要用到的view
	models : [ 'AgencyMonitor'],// 声明该控制层要用到的model
	stores : [ 'AgencyMonitor'],
	refs : [ // 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
			{
				ref : 'query',
				selector : 'query'
			},
			{ref:'pageToolBar', selector:'list pagingtoolbar'}
	],
	init : function() {
		var app = this;
		this.control({ // 这里的this相当于这个控制层
			'query button[action=reset]' : {  //query 是view中Query.js 的alias即别名
				click : this.resetQuery
			},
			'query button[action=query]' : {
				click : this.queryAlarmHistory

			},
			'list' : {
				cellclick : this.cellclick
			},
			'list  button[action=new]' : {
				click : this.createAgencyMonitor
			},
			'list button[action=delete]' : {
				click : this.deleteResourceType
			},
			/*'list actioncolumn' : {
				createEditWin : this.createEditWin
			},*/
			'agencyadd button[action=save]' : {
				click : this.saveNewAgencyMonitor
			},
			'agencyadd button[action=cancel]' : {
				click : this.closeWindow
			},
		/*	'agencyedit button[action=save]' : {
				click : this.saveNewAgencyMonitor
			},
			'agencyedit button[action=cancel]' : {
				click : this.closeWindow
			},*/
			// 列表单击事件
			'query datefield[action=aiStarttime_from]' : {
				select : this.queryStartDate
			},
			'query datefield[action=aiStarttime_to]' : {
				select : this.queryEndDate
			}

		});

	},
	// 重置查询面板的查询条件
	resetQuery : function(button) {
		// 重置查询表单
		button.up('form').getForm().reset();
		Ext.getCmp('aiStarttime_to').setMinValue(new Date());
	},
	// 查询按钮
	queryAlarmHistory : function(button) {
		// 获取查询值
		var form = button.up('form');
		var values = form.getValues();

		var store = this.getAgencyMonitorStore();
		store.clearFilter(true);
		var filters = [];
		// 填充filter
		for (p in values) {
			/*
			 * if(p=='sysname'&&values[p]=='全部'){ continue; }
			 */
			if (values[p] !== null && values[p] !== '') {
				filters.push({
					property : p,
					value : values[p]
				});
			}
		}
		// 使用filter进行过滤查询
		store.filter(filters);

	},

	queryStartDate : function(field, records, eOpts) {
		// 传入后台查询
		var form = field.up('form');
		var values = form.getValues();

		var start = new Date(values['aiStarttime_from']);
		var end = new Date(values['aiStarttime_to']);
		Ext.getCmp('aiStarttime_to').setMinValue(start);
		if (start > end) {
			Ext.getCmp('aiStarttime_to').setValue(start);
		}
	},
	queryEndDate : function(field, records, eOpts) {

		// 传入后台查询
		var form = field.up('form');
		var values = form.getValues();

		var start = new Date(values['aiStarttime_from']);
		var end = new Date(values['aiStarttime_to']);

		if (start > end) {
			Ext.getCmp('aiStarttime_from').setValue(endDate);
		}

	},

	// 新增按钮
	createAgencyMonitor : function(button) {
		var win = Ext.create('agencymonitor2.view.Edit');
		var form = win.down('form'); // 找到窗口所包含的表单
		var record = Ext.create('agencymonitor2.model.AgencyMonitor',{agencyname:'scott',agencyname:'12132131',orgcode:'123',sysname:'21',agencyid:'222'});
		form.loadRecord(record);
		win.show();

	},
	//创建编辑修改窗口
/*	createEditWin: function(record){
		var win = Ext.create('agencymonitor2.view.Edit');
		var form = win.down('form'); // 找到窗口所包含的表单
		form.loadRecord(record);
		win.show();
	},*/

	// 代理新增页-'保存'
	saveNewAgencyMonitor : function(button) {
		
		var me = this; // 获得当前controller对象的引用
		var win = button.up('window'); // 找到按钮所属的窗口
		var form = win.down('form'); // 找到窗口所包含的表单

		var store = this.getAgencyMonitorStore() // 当在store:[]中注册了store后，会自动创建getter方法

/*		if (!form.getForm().isValid()) {// 判断表单中的数据是否合法
			Ext.Msg.alert('tip', '数据不合法,请重新输入');
			return;
		}*/

		
		// 获得表单中的数据、
		//传入的Ext.data.Model对象内保存表单中的值。 如果记录未指定，将会尝试修改提供给loadRecord的记录
		var record = form.getRecord();
		form.getForm().updateRecord(record);
	

		//store.add(record);
	    //store.sync();
		this.getPageToolBar().doRefresh();
		win.close();

	},
	// 代理新增页-'取消'
	closeWindow : function(button) {
		if (button.up('window').close) {
			button.up('window').close();
		}
	}
});
