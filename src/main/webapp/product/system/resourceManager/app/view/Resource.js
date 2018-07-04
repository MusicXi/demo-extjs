Ext.define("ResourceManager.view.Resource",{
	extend : 'Ext.tree.Panel',
	alias : 'widget.resource',
	store:'Resource',
	layout : 'fit',
	//useArrows: true,
    rootVisible: false,
    selType: 'cellmodel',
    plugins:[
	Ext.create('Ext.grid.plugin.CellEditing',{
		clicksToEdit: 1 //1 是单击，  2 是双击
	})
	],
	columns: [{
         xtype: 'treecolumn', //this is so we know which column will show the tree
         text: 'ID',
         flex:1,
         sortable: true,
         dataIndex: 'id',
         //locked: true
         renderer : function(value, metaData, record, colIndex, store,view) {
			return record.data.name;//不显示ID用资源名称代替显示
		 },
     }, /*{
         text: '资源名称',
         flex:1,
         sortable: true,
         dataIndex: 'name',
         align: 'center',
         editor:{xtype:'textfield'}
     },*/ {
         text: '类型',
         flex:0.5,
         dataIndex: 'type',
         sortable: true,
         renderer : function(value, metaData, record, colIndex, store,view) {
				if(value == 'menu'){
					//metaData.tdAttr = 'data-qtip="' + value + ' 美元"';						
					return "菜单";
				}else if(value=='button'){
					return "按钮";
				}
				return value;
		 },
     /*    editor:{
        	xtype : 'combo',
			queryMode : 'local',
			store:Ext.create('Ext.data.Store', {
				fields: ['value', 'label'],
	       		data : [{value:'menu',label:'菜单'},
	       			    {value:'button',label:'按钮'}]					
				
			}),
			valueField : 'value',
			displayField : 'label'
		}*/
     }, {
         text: '图标',
         flex:0.5,
         dataIndex: 'icon',
         sortable: true
     },{
         text: 'URL',
         flex:2,
         dataIndex: 'url',
         sortable: true,
         editor:{xtype:'textfield'}
     },{
         text: '权限标识',
         flex:1,
         dataIndex: 'permission',
         sortable: true,
         editor:{xtype:'textfield'}
     },{
         xtype: 'checkcolumn',
         header: '是否可用',
         dataIndex: 'icon',
         flex:0.5,
         //stopSelection: false
     }, {
         text: 'Edit',
         width: 40,
         menuDisabled: true,
         xtype: 'actioncolumn',
         tooltip: 'Edit task',
         align: 'center',
   
         //icon: '/myron/images/edit_task.png',
         handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
             Ext.Msg.alert('Editing' + (record.get('done') ? ' completed task' : '') , record.get('task'));
         },
         // Only leaf level tasks may be edited
         isDisabled: function(view, rowIdx, colIdx, item, record) {
             return !record.data.leaf;
         }
     }],
     //顶部工具条
	tbar: [
		{
			xtype: 'button',
			text: '查询',
			iconCls:'toolbar-search', 
			action:'openQuery',
			disabled: false
		},
		'-',
		{
			xtype: 'button',
			text: '新增',
			iconCls:'toolbar-add', 
			action:'new',
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
			text: '保存',
			action:'save',
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
        
})