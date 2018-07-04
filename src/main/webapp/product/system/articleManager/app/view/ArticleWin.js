Ext.define('ArticleManager.view.ArticleWin', {
	extend:'Ext.window.Window',
	alias:'widget.articleWin',
	id:'articleWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'Article编辑窗口',
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
		        fieldLabel: '编号',
		        name:'articleId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '栏目编号',
		        name:'categoryId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '标题',
		        name:'title'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '文章链接',
		        name:'link'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '标题颜色',
		        name:'color'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '文章图片',
		        name:'image'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '关键字',
		        name:'keywords'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '摘要',
		        name:'description'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '权重',
		        name:'weight'
		    },
			{
				xtype:'datefield',
				fieldLabel:'权重期限',
				name:'weightDate',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '点击数',
		        name:'hits'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '推荐位',
		        name:'posid'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '创建者',
		        name:'createBy'
		    },
			{
				xtype:'datefield',
				fieldLabel:'创建时间',
				name:'createDate',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '更新者',
		        name:'updateBy'
		    },
			{
				xtype:'datefield',
				fieldLabel:'更新时间',
				name:'updateDate',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '备注信息',
		        name:'remarks'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '删除标记',
		        name:'delFlag'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '文章内容',
		        name:'content'
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