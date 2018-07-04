Ext.define('manager.view.ImportWin', {
	extend:'Ext.window.Window',
	alias:'widget.importWin',
	id:'importWinID',
	title:'导入Excel',
	//width:600, 	//不定义大小Window将会自适应
	//height:300,
	modal:true, //为真 当显示时，制作窗口模板并且掩饰他背后的一切
	items:[{
			xtype : 'displayfield',
			value : '<div style="color:#000000;text-align:left;">温馨提示<br/></div><div style="text-align:left;">1、请用户下载统一的台账导入模板(见系统界面)。<br/>2、请严格按照模板提示进行必填项、日期、数字等填写。<br/>3、导入成功失败日志可进行下载查看。</div>'
		},
		{
			xtype:'form',
			id:'newformID',
			layout:'column',
			frame : true, 		//True 为 Panel 填充画面
			//autoShow:true,		//创建window自动展现
			//split : true,
			/*fieldDefaults : {
				xtype:'textfield',
				labelWidth: 60,
				margin : '3 5 3 10',  //上右下左
				labelPad:0, 		  //默认为5
				columnWidth : 1 / 3
			}*/
			items:[{
	    	    xtype: 'filefield',
	    	    buttonText : '导入Excel',
	    	    name : 'excelFile',
	    	    action : 'import'
			}]
			
			
	
		}
	]
})