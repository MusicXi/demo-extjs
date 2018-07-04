Ext.define("MetaTagsDimManager.view.MetaTagsDimPanel",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.metaTagsDimPanel',
	layout : 'fit',

	initComponent : function() {
		this.callParent([this]);
	},
	items:{
		xtype:'gridpanel',
		name:'metaTagsDimGridPanel',
		extend: 'Ext.grid.Panel',
		title: 'MetaTagsDim列表',
		store: 'MetaTagsDim',
		selType : 'checkboxmodel',//选则类型为checkbox
		//selType: 'rowmodel',
		enableKeyNav:true,		  //鼠标控制列移动
		columnLines:true,		  //显示纵向表格线,默认false
		border:false,
		
	    columns: [
	        { header: '标签ID',  dataIndex: 'id', xtype: 'numbercolumn', format:'0.00' },
	        { header: '标签名称',  dataIndex: 'text' },
	        { header: '1:分类，2:标签组，3:标签',  dataIndex: 'type', xtype: 'numbercolumn', format:'0.00' },
	        { header: '父标签',  dataIndex: 'parentId', xtype: 'numbercolumn', format:'0.00' },
	        { header: '父标签集合',  dataIndex: 'arrparentId' },
	        { header: '标签值',  dataIndex: 'value' },
	        { header: '业务含义',  dataIndex: 'description' },
	        { header: '状态：-1:废止,0:创建,99上线',  dataIndex: 'status', xtype: 'numbercolumn', format:'0.00' },
	        { header: '上线时间',  dataIndex: 'releaseTime' },
	        { header: '废止时间',  dataIndex: 'trashTime' },
	        { header: '需求方',  dataIndex: 'demand' },
	        { header: '标签多值  ，1是，2否',  dataIndex: 'isValMulti', xtype: 'numbercolumn', format:'0.00' },
	        { header: '标签值枚举  ，1是，2否',  dataIndex: 'isValEnum', xtype: 'numbercolumn', format:'0.00' },
	        { header: '字段类型，DATE_INT支持yyyymmdd,yyyymm等格式',  dataIndex: 'valType' },
	        { header: '标签权重  1:0/1  2:(0,1]',  dataIndex: 'valWeightType', xtype: 'numbercolumn', format:'0.00' },
	        { header: '标签权重归一化  ，1是，2否',  dataIndex: 'valWeightNorm', xtype: 'numbercolumn', format:'0.00' },
	        { header: '更新频率  1:按天,2:按周,3:按月 4:实时',  dataIndex: 'freq', xtype: 'numbercolumn', format:'0.00' },
	        { header: '生命周期即有效期，单位为天   0表示长期有效',  dataIndex: 'lifecycle', xtype: 'numbercolumn', format:'0.00' },
	        { header: '标签规则描述',  dataIndex: 'ruleDesc' },
	        { header: '标签域，对应列簇',  dataIndex: 'domain' },
	        { header: '标签列，对应列',  dataIndex: 'field' },
	        { header: '数据流',  dataIndex: 'dataFlow' },
	        { header: '数据节点',  dataIndex: 'dataNode' },
	        { header: '开发负责人',  dataIndex: 'committer' },
	        { header: '是否建索引，指是否保存到solr中建立索引，1是，2否',  dataIndex: 'isIndex', xtype: 'numbercolumn', format:'0.00' },
	        { header: '创建时间',  dataIndex: 'createTime' },
	        { header: '创建人',  dataIndex: 'createBy', xtype: 'numbercolumn', format:'0.00' },
	        { header: '修改时间',  dataIndex: 'modifiedTime' },
	        { header: '修改人',  dataIndex: 'modifiedBy', xtype: 'numbercolumn', format:'0.00' }
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
				store: 'MetaTagsDim',
				displayInfo: true
			}
		]
	  
	}
})

