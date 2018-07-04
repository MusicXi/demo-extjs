Ext.define("UserManager.view.Center",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.center',
	layout : 'fit',

	initComponent : function() {
		/*this.plugins=[
			Ext.create('Ext.grid.plugin.CellEditing')
		];*/
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'userGridPanel',
		extend: 'Ext.grid.Panel',
		title: '用户列表',
		store: 'User',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
	/*	plugins: [
		           Ext.create('Ext.grid.plugin.RowEditing', {
		               clicksToEdit: 1
		           })
		       ],*/
	    columns: [
	        { header: '用户ID',  dataIndex: 'id', sortable:false, editor: 'textfield' },
	        { header: '姓名',  dataIndex: 'name',editor: 'textfield' },
	        { header: '账号',  dataIndex: 'username',
	        	 editor: {
	                 xtype: 'textfield',
	                 allowBlank: false
	             }
	        },
	        { header: '性别',  
	          dataIndex: 'sex',
	          renderer : function(value, metaData, record, colIndex, store,view) {
					if(value == 0){
						//metaData.tdAttr = 'data-qtip="' + value + ' 美元"';						
						return "女";
					}else{
						return "男";
					}
					return value;
				}
	        },
	        { header: '邮箱', dataIndex: 'email', flex: 1 },
	        { header: '电话', dataIndex: 'phone' },
	        { header: '公司', dataIndex: 'company.name', sortable:false},
	        { header: '部门', dataIndex: 'department.name', sortable:false }
	    ],
	    //顶部工具条
		tbar: [
			{
				xtype: 'button',
				text: '查询',
				iconCls:'toolbar-search', 
				action:'query',
				disabled: !userOperation.read
			},
			'-',
			{
				xtype: 'button',
				text: '新增',
				iconCls:'toolbar-add', 
				action:'openAdd',
				disabled: !userOperation.create
			},
			'-',
			{
				xtype: 'button',
				text: '复制',
				iconCls:'toolbar-add', 
				action:'openCopy',
				disabled: !userOperation.copy
			},
			'-',
			{
				xtype: 'button',
				text: '删除',
				iconCls:'toolbar-delete', 
				action:'delete',
				disabled: !userOperation.remove
			},
			'-',
			{
				xtype: 'button',
				text: '刷新',
				iconCls:'toolbar-refresh',
				action:'refresh',
				disabled: !userOperation.refresh
			},
			'-',
			{
				xtype: 'button',
				text: '角色分配',
				iconCls:'toolbar-refresh',
				action:'setRole',
				disabled: !userOperation.setRole
			},
			'-',
			{
	    	    xtype: 'button',
	    	    text: '导入Excel',
	    	    action : 'importExcel',
	    	    disabled: !userOperation.importExcel
			},
			'-',
			{
	    	    xtype: 'button',
	    	    text: '导出Excel',
	    	    action : 'exportExcel',
	    	    disabled: !userOperation.exportExcel
			},
			'->',
			{
	    	    xtype: 'button',
	    	    text: '下载Excel模板',
	    	    action : 'download',
	    	    disabled: !userOperation.download
			}
		],
		//分页设置
		dockedItems: [
			{
				xtype: 'pagingtoolbar',
				dock: 'bottom',
				store: 'User',
				displayInfo: true
			}
		]
	  
	}
})

