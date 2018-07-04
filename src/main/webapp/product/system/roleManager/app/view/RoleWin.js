Ext.define('RoleManager.view.RoleWin', {
	extend:'Ext.window.Window',
	alias:'widget.roleWin',
	id:'roleWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'Role编辑窗口',
	width:600, 	//不定义大小Window将会自适应
	maxWidth:600, 
	maxHeight:500,
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
		items:[
			{
		        xtype: 'textfield',
		        fieldLabel: '角色ID',
		        name:'roleId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '机构ID',
		        name:'organizationId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '角色名称',
		        name:'name'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '权限标识',
		        name:'role'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '描述',
		        name:'description'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '状态',
		        name:'available'
		    }
		],
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
		
		
	}],
	listeners:{
		hide:function(editorWin){
			editorWin.down('form').getForm().reset();
		}
	}
	
})