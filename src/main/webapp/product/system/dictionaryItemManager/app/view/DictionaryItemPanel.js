Ext.define("DictionaryItemManager.view.DictionaryItemPanel",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.dictionaryItemPanel',
	layout : 'fit',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'dictionaryItemGridPanel',
		extend: 'Ext.grid.Panel',
		title: 'DictionaryItem列表',
		store: 'DictionaryItem',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
		
	    columns: [
	        { header: '字典项ID',  dataIndex: 'dictionaryItemId' },
	        { header: '字典类型ID',  dataIndex: 'dictionaryId' },
	        { header: '字典值',  dataIndex: 'value' },
	        { header: '显示名称',  dataIndex: 'text' },
	        { header: '父级ID',  dataIndex: 'parentId' },
	        { header: '层级',  dataIndex: 'grade' },
	        { header: '排序',  dataIndex: 'sort' }
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
				store: 'DictionaryItem',
				displayInfo: true
			}
		]
	  
	}
})

