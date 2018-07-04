Ext.define('GoodsManager.view.GoodsWin', {
	extend:'Ext.window.Window',
	alias:'widget.goodsWin',
	id:'goodsWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'Goods编辑窗口',
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
		        fieldLabel: '商品ID',
		        name:'goodsId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '商品名称',
		        name:'name'
		    },
			{
				xtype:'datefield',
				fieldLabel:'销售开始时间',
				name:'saleStartDate',
				format: 'Y-m-d H:i:s' 
			},
			{
				xtype:'datefield',
				fieldLabel:'销售截止时间',
				name:'saleEndDate',
				format: 'Y-m-d H:i:s' 
			},
			{
				xtype:'datefield',
				fieldLabel:'创建时间',
				name:'createTime',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '创建人',
		        name:'createBy'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '状态',
		        name:'status'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '商品说明',
		        name:'comment'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '流量',
		        name:'data'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'textfield',
		        fieldLabel: '流量类型',
		        name:'flowType'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '商品数量',
		        name:'number'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '有效月',
		        name:'effectiveMonth'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '单价',
		        name:'price'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
				xtype:'datefield',
				fieldLabel:'修改时间',
				name:'updateTime',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '修改人',
		        name:'updateBy'
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