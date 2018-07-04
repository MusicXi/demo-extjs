Ext.define('agencymonitor2.view.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.list',
	title: '列表面板',
	id:'agencymonitorid',
	store: 'AgencyMonitor',
	sortableColumns : false,
	enableColumnHide : false,
	emptyText : "<p style='font-weight:bold;color:red;' align='center'>没有查询到符合条件的数据！</p>",
	//允许多选
	multiSelect: true,
	columns: [
	    {
				header: '序号',
				xtype: 'rownumberer',
				flex:1,
				align:'center'
		},
		{
			header: 'id',
			dataIndex:'id',
			flex:1       
		},
		{
			header: '代理人姓名',
			dataIndex:'agencyname',
			flex:1   
		},
		{
			header: '开始时间',
			dataIndex:'agencydatebegin',
			renderer: Ext.util.Format.dateRenderer('Y-m-d'),
			flex:1
		
		},
		{
			header: '结束时间',
			dataIndex:'agencydateend',
			renderer: Ext.util.Format.dateRenderer('Y-m-d'),
			flex:1
		
		},
		
		{
			header: '是否被执行',
			dataIndex:'agencystatus',
			flex:1,
			renderer: function(value,metaData,record,colIndex,store,view) {
				if(value=='1'){		
					return "是";
				}else if(value=='-1'){
					return "否";
				}else{
					return "其他";
				}
			}
		},
		{
		    header: '编辑',
			dataIndex:'title',
			align:'center',
			tdCls:'tdValign',
			xtype : 'actioncolumn',
			hideable:false,
			items:[{
				icon : '../../images/base/icon_edit.png',
				tooltip : '查看',
				handler: function(_1,_2,_3,_4,_5,record,_7) {
					this.fireEvent('createEditWin',record);
				}
			}]
	}
	

	],
	//分页设置
	dockedItems: [
		{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: 'AgencyMonitor',
			displayInfo: false
		}
	],
	//顶部工具条
	tbar: [
		{
			xtype: 'button',
			text: '新增',
			icon : '../../images/base/icon_add.png',
			action:'new',
			disabled: false
		},
		'-',
		{
			xtype: 'button',
			text: '删除',
			action:'delete',
			disabled: true
		}
	]

});
