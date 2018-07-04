Ext.define('manager.view.Edit', {
	extend:'Ext.window.Window',
	alias:'widget.edit',
	title:'预览',
	width:600, 	//不定义大小Window将会自适应
	//height:300,
	modal:true, //为真 当显示时，制作窗口模板并且掩饰他背后的一切
	items:[
		{
			xtype:'form',
			id:'newformID',
			layout:'column',
			frame : false, 		//True 为 Panel 填充画面,ps:为true会多出边框
			//autoShow:true,		//创建window自动展现
			//split : true,
			fieldDefaults : {
				xtype:'textfield',
				labelWidth: 60,
				margin : '3 5 3 10',  //上右下左
				labelPad:0, 		  //默认为5
				columnWidth : 1 / 3
			},
			items:[{
				xtype:'textfield',
				fieldLabel:'姓名',
				name:'name'
				//regex:/[A-Za-z\s]/,
				//regexText:'ddfdfd'
			},
			{
				xtype:'textfield',
				fieldLabel:'邮箱',
				name:'email',
				vtype:'email'
			},
			{
				xtype:'textfield',
				fieldLabel:'手机',
				name:'phone'	
			},
			{
				xtype:'numberfield',
				fieldLabel:'薪资',
				name:'salary'
			},
			{
				xtype:'datefield',
				fieldLabel:'入职日期',
				name:'inDutyDate'
			}],
			buttonAlign:'center',
			buttons : [{
				text : '保存',
				action : 'update'
			}, {
				text : '取消',
				action : 'cancel'
			}]
			
			
		}
	]
})