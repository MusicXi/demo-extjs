Ext.define('MetaTagsDimManager.view.MetaTagsDimWin', {
	extend:'Ext.window.Window',
	alias:'widget.metaTagsDimWin',
	id:'metaTagsDimWinID',
	closeAction:'hide',
	border:false,
	modal:true,
	title:'MetaTagsDim编辑窗口',
	width:800, 	//不定义大小Window将会自适应
	maxWidth:800, 
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
		        xtype: 'numberfield',
		        fieldLabel: '标签ID',
		        name:'id'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'textfield',
		        fieldLabel: '标签名称',
		        name:'text'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '1:分类',
		        name:'type'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '父标签',
		        name:'parentId'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'textfield',
		        fieldLabel: '父标签集合',
		        name:'arrparentId'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '标签值',
		        name:'value'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '业务含义',
		        name:'description'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '状态',
		        name:'status'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
				xtype:'datefield',
				fieldLabel:'上线时间',
				name:'releaseTime',
				format: 'Y-m-d H:i:s' 
			},
			{
				xtype:'datefield',
				fieldLabel:'废止时间',
				name:'trashTime',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'textfield',
		        fieldLabel: '需求方',
		        name:'demand'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '标签多值',
		        name:'isValMulti'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '标签值枚举',
		        name:'isValEnum'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'textfield',
		        fieldLabel: '字段类型，DATE_INT支持yyyymmdd,yyyymm等格式',
		        name:'valType'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '标签权重  1:0/1  2:(0,1]',
		        name:'valWeightType'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '标签权重归一化  ，1是，2否',
		        name:'valWeightNorm'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '更新频率  1:按天,2:按周,3:按月 4:实时',
		        name:'freq'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'numberfield',
		        fieldLabel: '生命周期即有效期，单位为天   0表示长期有效',
		        name:'lifecycle'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
		        xtype: 'textfield',
		        fieldLabel: '标签规则描述',
		        name:'ruleDesc'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '标签域，对应列簇',
		        name:'domain'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '标签列，对应列',
		        name:'field'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '数据流',
		        name:'dataFlow'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '数据节点',
		        name:'dataNode'
		    },
			{
		        xtype: 'textfield',
		        fieldLabel: '开发负责人',
		        name:'committer'
		    },
			{
		        xtype: 'numberfield',
		        fieldLabel: '是否建索引，指是否保存到solr中建立索引，1是，2否',
		        name:'isIndex'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
				xtype:'datefield',
				fieldLabel:'创建时间',
				name:'createTime',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'numberfield',
		        fieldLabel: '创建人',
		        name:'createBy'
		        //maxValue: 99,
        		//minValue: 0
		    },	
			{
				xtype:'datefield',
				fieldLabel:'修改时间',
				name:'modifiedTime',
				format: 'Y-m-d H:i:s' 
			},
			{
		        xtype: 'numberfield',
		        fieldLabel: '修改人',
		        name:'modifiedBy'
		        //maxValue: 99,
        		//minValue: 0
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