Ext.define("GoodsManager.view.GoodsPanel",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.goodsPanel',
	layout : 'fit',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'goodsGridPanel',
		extend: 'Ext.grid.Panel',
		title: 'Goods列表',
		store: 'Goods',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
		
	    columns: [
	        { header: '商品ID',  dataIndex: 'goodsId' },
	        { header: '商品名称',  dataIndex: 'name' },
	        { header: '销售开始时间',  dataIndex: 'saleStartDate' },
	        { header: '销售截止时间',  dataIndex: 'saleEndDate' },
	        { header: '创建时间',  dataIndex: 'createTime' },
	        { header: '创建人',  dataIndex: 'createBy' },
	        { header: '状态',  dataIndex: 'status' },
	        { header: '商品说明',  dataIndex: 'comment' },
	        { header: '流量',  dataIndex: 'data', xtype: 'numbercolumn', format:'0.00' },
	        { header: '流量类型',  dataIndex: 'flowType' },
	        { header: '商品数量',  dataIndex: 'number', xtype: 'numbercolumn', format:'0.00' },
	        { header: '有效月',  dataIndex: 'effectiveMonth', xtype: 'numbercolumn', format:'0.00' },
	        { header: '单价',  dataIndex: 'price', xtype: 'numbercolumn', format:'0.00' },
	        { header: '修改时间',  dataIndex: 'updateTime' },
	        { header: '修改人',  dataIndex: 'updateBy' }
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
				store: 'Goods',
				displayInfo: true
			}
		]
	  
	}
})

