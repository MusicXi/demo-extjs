Ext.define('manager.view.Query', {
	extend:'Ext.window.Window',
	alias:'widget.query',
	title:'查询条件',
	id:'queryID',
	icon : '../../images/base/browser_window.png',
	width:600, 	//不定义大小Window将会自适应
	//height:300,
	//modal:true, //为真 当显示时，制作窗口模板并且掩饰他背后的一切
	//plain:true,
	resizable:false, //不允许改变窗口大小
	items:[
		{
			xtype:'form',
			id:'queryformID',
			layout:'column',
			frame : true, 		//True 为 Panel 填充画面
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
				name:'timeBegin',
				fieldLabel: '开始日期',
				id:'imeBeginID'
			},
			{
				xtype:'datefield',
				name:'timeEnd',
				fieldLabel: '结束日期',
				id:'imeEndID'
			},
			{
				xtype:'datefield',
				fieldLabel:'日期',
				name:'inDutyDate'
			}],
			buttonAlign:'center',
			buttons : [{
				text : '查询',
				action : 'query'
			}, {
				text : '重置',
				action : 'reset'
			}, {
				text : '关闭',
				action : 'cancel'
			}]
			
			
		}
	]
})