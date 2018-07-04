Ext.define('DictionaryItemManager.view.DictionaryItemWin', {
	extend:'Ext.window.Window',
	alias:'widget.dictionaryItemWin',
	id:'dictionaryItemWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'DictionaryItem编辑窗口',
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
		        fieldLabel: '字典项ID',
		        name:'dictionaryItemId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '类型ID',
		        name:'dictionaryId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '字典值',
		        name:'value'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '显示名称',
		        name:'text'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '父级ID',
		        name:'parentId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '层级',
		        name:'grade'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '排序',
		        name:'sort'
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