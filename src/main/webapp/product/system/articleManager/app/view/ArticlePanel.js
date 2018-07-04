Ext.define("ArticleManager.view.ArticlePanel",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.articlePanel',
	layout : 'fit',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'articleGridPanel',
		extend: 'Ext.grid.Panel',
		title: 'Article列表',
		store: 'Article',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
		
	    columns: [
	        { header: '编号',  dataIndex: 'articleId' },
	        { header: '栏目编号',  dataIndex: 'categoryId' },
	        { header: '标题',  dataIndex: 'title' },
	        { header: '文章链接',  dataIndex: 'link' },
	        { header: '标题颜色',  dataIndex: 'color' },
	        { header: '文章图片',  dataIndex: 'image' },
	        { header: '关键字',  dataIndex: 'keywords' },
	        { header: '摘要',  dataIndex: 'description' },
	        { header: '权重',  dataIndex: 'weight' },
	        { header: '权重期限',  dataIndex: 'weightDate' },
	        { header: '点击数',  dataIndex: 'hits' },
	        { header: '推荐位',  dataIndex: 'posid' },
	        { header: '创建者',  dataIndex: 'createBy' },
	        { header: '创建时间',  dataIndex: 'createDate' },
	        { header: '更新者',  dataIndex: 'updateBy' },
	        { header: '更新时间',  dataIndex: 'updateDate' },
	        { header: '备注信息',  dataIndex: 'remarks' },
	        { header: '删除标记',  dataIndex: 'delFlag' },
	        { header: '文章内容',  dataIndex: 'content' }
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
				store: 'Article',
				displayInfo: true
			}
		]
	  
	}
})

