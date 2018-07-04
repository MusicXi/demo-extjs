Ext.define("LogManager.view.LogPanel",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.logPanel',
	layout : 'fit',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'logGridPanel',
		extend: 'Ext.grid.Panel',
		title: 'Log列表',
		store: 'Log',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
		
	    columns: [
	        { header: '日志主键',  dataIndex: 'logId' },
	        { header: '日志类型',  dataIndex: 'type' },
	        { header: '日志标题',  dataIndex: 'title' },
	        { header: '请求地址',  dataIndex: 'remoteAddr' },
	        { header: 'URI',  dataIndex: 'requestUri' },
	        { header: '请求方式',  dataIndex: 'method' },
	        { header: '提交参数',  dataIndex: 'params' },
	        { header: '异常',  dataIndex: 'exception' },
	        { header: '操作时间',  dataIndex: 'operateDate', flex: 1},
	        { header: '请求超时',  dataIndex: 'timeout' },
	        { header: '用户ID',  dataIndex: 'userId' }
	    ],
	    //顶部工具条
		tbar: [
			{
				xtype: 'button',
				text: '查询',
				iconCls:'toolbar-search', 
				action:'query',
				disabled: false
			},
			'-',
			{
				xtype: 'button',
				text: '新增',
				iconCls:'toolbar-add', 
				action:'add',
				disabled: false
			},
			'-',
			{
				xtype: 'button',
				text: '复制',
				iconCls:'toolbar-add', 
				action:'copy',
				disabled: false
			},
			'-',
			{
				xtype: 'button',
				text: '删除',
				iconCls:'toolbar-delete', 
				action:'delete',
				disabled: false
			},
			'-',
			{
				xtype: 'button',
				text: '刷新',
				iconCls:'toolbar-refresh',
				action:'refresh',
				disabled: false
			},
			'-',
			{
	    	    xtype: 'button',
	    	    text: '导入Excel',
	    	    action : 'importExcel',
	    	    disabled: false
			},
			'-',
			{
	    	    xtype: 'button',
	    	    text: '导出Excel',
	    	    action : 'exportExcel',
	    	    disabled: false
			},
			'->',
			{
	    	    xtype: 'button',
	    	    text: '下载Excel模板',
	    	    action : 'download',
	    	    disabled: false
			}
		],
		//分页设置
		dockedItems: [
			{
				xtype: 'pagingtoolbar',
				dock: 'bottom',
				store: 'Log',
				displayInfo: true
			}
		]
	  
	}
})

