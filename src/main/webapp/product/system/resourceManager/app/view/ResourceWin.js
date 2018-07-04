Ext.define('ResourceManager.view.ResourceWin', {
	extend:'Ext.window.Window',
	alias:'widget.resourceWin',
	id:'resourceWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'新增',
	width:600, 	//不定义大小Window将会自适应
	maxWidth:600, 
	maxHeight:150,
	items:[{
		xtype:'form',
		layout:'column',
		frame : false, 		//True 为 Panel 填充画面//True 为 Panel 填充画面,ps:为true会多出边框
		autoShow:true,		//创建window自动展现
		fieldDefaults : {
			xtype:'textfield',
			//labelAlign:'right',
			labelWidth: 60,
			margin : '3 5 3 10',  //上右下左
			columnWidth : 1 / 3
		},
		items:[/*{
	        xtype: 'textfield',
	        fieldLabel: '上级资源',
	        name:'parentName',
	        disabled:true,//禁用的表单项将不会被submitted.
	    },*/{
			xtype:'combo',
			fieldLabel:'上级资源',
			name:'parentId',
			readOnly:true,
			//hidden:true
			/*store: new Ext.data.ArrayStore({//由外部动态传入
	 			fields: ['value', 'label'],
				data : [['100','测试']]
	 			}),*/
	 		valueField:'value',
	 		displayField:'label',
		},{
			xtype:'textfield',
			fieldLabel:'资源名称',
			name:'name'
		},{
			xtype : 'combo',
			emptyText : "请选择类型",
			fieldLabel : '类型',
			name : 'type',
			//queryMode : 'local',
			//readOnly:true,
			store:Ext.create('Ext.data.Store', {
				fields:[{name:'value',type:'string'}, 'label'],
				data:[
				      {value:'menu', label:'菜单'},
				      {value:'button', label:'按钮'}
				]
			}),
			//value : [ 'AL', 'AZ' ],//默认值
			valueField : 'value',
			displayField : 'label'
		},
		{
			xtype:'textfield',
			fieldLabel:'URL',
			name:'url',
		},
		{
			xtype:'textfield',
			fieldLabel:'图标',
			name:'icon'
		},{
			xtype:'textfield',
			fieldLabel:'权限标识',
			name:'permission'
		}],
		buttonAlign:'center',
		buttons : [{
			text : '保存',
			action : 'save'
		}, {
			text : '取消',
			action : 'cancel',
			handler: function(button) {
				button.up('window').hide();
			}
		}]
		
		
	}]
	
})