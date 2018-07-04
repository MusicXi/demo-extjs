Ext.define('LogManager.view.LogWin', {
	extend:'Ext.window.Window',
	alias:'widget.logWin',
	id:'logWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'Log编辑窗口',
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
		        fieldLabel: '日志主键',
		        name:'logId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '日志类型',
		        name:'type'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '日志标题',
		        name:'title'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '请求地址',
		        name:'remoteAddr'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: 'URI',
		        name:'requestUri'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '请求方式',
		        name:'method'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '提交参数',
		        name:'params'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '异常',
		        name:'exception'
		    },
			{
				xtype:'datefield',
				fieldLabel:'开始时间',
				name:'operateDate',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '结束时间',
		        name:'timeout'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '用户ID',
		        name:'userId'
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