Ext.define('manager.view.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.list', //类名的别名,多用于微件定义,建议使用小写的类名
	//xtype 方式:     items:[{xtype:'list', region:'center'},{},{}]
	//Ext.create()方式   Ext.create('widget.coolpanel',{...});
	//
	id:'listID', //注意和alias使用的区别,一般Ext.getCmp('listID')用于对组件的操作,alias用于创建组件
	//title: '列表面板',
	store:'Manager',
	sortableColumns:false,	//禁止通过表格标题菜单项排序
	selType : 'checkboxmodel',//选则类型为checkbox
	simpleSelect:false,
	columnLines:true,		  //显示纵向表格线,默认false
	columns: {
		defaults:{
			align:'left',
			menuDisabled:true  //禁用标题菜单,默认false
		},
		items:[
			{	header: 'NO',    xtype: 'rownumberer'},
			{ 	header: 'id', 	 dataIndex:'id', flex:1, hidden:true},
	        { 	header: 'Name',  dataIndex: 'name',  flex:  1 }, //代表占列宽的比例1/3
	        { 	header: 'Email', dataIndex: 'email', flex: 1 },
	        { 	header: 'Phone', dataIndex: 'phone', flex: 1 },
	        { 	header: 'Salary', 
	        	xtype:'numbercolumn',
	        	dataIndex: 'salary', 
	        	//format:'0.00', //默认000,000.000
	        	align:'right',
	        	flex: 1 ,
	        	renderer : function(value, metaData, record, colIndex, store,
						view) {
					if(value != null){
						metaData.tdAttr = 'data-qtip="' + value + ' 美元"';						
						return value+" 美元";
					}
					return value;
				}
	       	 },
	        { 	
	        	header: 'Date', 
	        	xtype:'datecolumn',
	        	dataIndex: 'inDutyDate', 
	        	align:'center',
	        	renderer: Ext.util.Format.dateRenderer('Y-m-d'),
	        	flex: 1 
	        }]
	},	
    //顶部工具条
	tbar: [
		{
			xtype: 'button',
			text: '查询',
			icon : '../../images/base/zoom.png',
			action:'openQuery',
			disabled: false
		},
		'-',
		{
			xtype: 'button',
			text: '新增',
			icon : '../../images/base/add.png',
			action:'new',
			disabled: false
		},
		'-',
		{
			xtype: 'button',
			text: '删除',
			icon : '../../images/base/delete.png',
			action:'delete',
			disabled: false
		},
		'-',
		{
			xtype: 'button',
			text: '刷新',
			icon : '../../images/base/redo.png',
			action:'refresh',
			disabled: false
		},
		'-',
		{
    	    xtype: 'button',
    	    text: '导入Excel',
    	    action : 'openImportWin',
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
			store: 'Manager',
			displayInfo: true
		}
	]


});
