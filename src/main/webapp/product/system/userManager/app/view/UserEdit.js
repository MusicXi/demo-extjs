Ext.define('UserManager.view.UserEdit', {
	extend:'Ext.window.Window',
	alias:'widget.userEdit',
	title:'修改',
	width:600, 	//不定义大小Window将会自适应
	layout: 'fit',
	border:false,
	//height:300,
	modal:true, //为真 当显示时，制作窗口模板并且掩饰他背后的一切
	items:[
		{
			xtype:'form',
			id:'form_userEdit_id',
			layout:'column',
			frame : false, 		//True 为 Panel 填充画面,ps:为true会多出边框
			//autoShow:true,		//创建window自动展现
			//split : true,
			/*fieldDefaults : {
				xtype:'textfield',
				labelWidth: 60,
				margin : '3 5 3 10',  //上右下左
				labelPad:0, 		  //默认为5
				columnWidth : 1 / 3
			},*/
			defaults: {				//统一设置表单默认属性 
				labelWidth: 60,		//标签宽度
				width:100,			//字段宽度
//		   	 	labelAlign: 'right',//标签对齐方式
				allowBlank:false,	//是否允许为空
				blankText:'不允许为空',
				msgTarget:'qtip',	//错误消息文本应该显示的位置:under/side/..
	   	 		padding: '5px 10px 5px 10px',
	   	 		columnWidth: 1/3
	   	 	},
	   	 	defaultType: 'textfield',
			items:[{
				xtype:'textfield',
				fieldLabel:'姓名',	
				name:'name',
				selectOnFocus:true
				//regex:/[A-Za-z\s]/,
				//regexText:'ddfdfd'
			},{
				xtype:'textfield',
				fieldLabel:'用户名',
				name:'username',
				selectOnFocus:true,
				maxLength:5
			},{
				xtype:'textfield',
				fieldLabel:'邮箱',
				name:'email',
				selectOnFocus:true,
				vtype:'email'
			},
			{
				xtype:'textfield',
				fieldLabel:'手机',
				name:'phone'	
			}/*,{
				xtype : 'combo',
				emptyText : "请选择",
				fieldLabel : '部门',
				name : 'organizationId',
				editable : false,
				multiSelect : false,
				queryMode : 'local',
				store:Ext.create('Ext.data.Store', {
					autoLoad: true,
					model:'UserManager.model.Dictionary',						
					proxy: {
				         type: 'ajax',
				         url: webRoot+'/product/system/userManager/getDictItem.do',
				         reader: {
				             type: 'json',
				             root: 'records'
				         },
				         extraParams: {
				        	 'dictionaryId':'organization', 
			         	}
				     }
				}),
				//value : [ 'AL', 'AZ' ],
				valueField : 'id',
				displayField : 'text'
			}*/,{ 
				xtype: "treepicker", 
				fieldLabel: "组织机构", 
				name:'organizationId',
				displayField:"text",
				value:'',
				rootVisible:false,
				store:Ext.create('UserManager.store.Organization')
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
			}],
			listeners : {
				afterrender : function(view) {//formPanel渲染后加载下拉列表数据
					var departmentCombo=view.down('combo');
				/*	Ext.Ajax.request({
						url: webRoot + 'KpiGroupController/updateKpiGroup.do',
			        	//params:submitValues,
			        	success: function(data){
			        		var store = Ext.create('Ext.data.Store',{
								fields: ['name', 'code'],
								data : data
							});
			        		departmentCombo.bindStore(store);
			        	},
			        	failure: function(data){
			            	Ext.Msg.alert('消息', '编辑指标组失败');
			            }
					});*/
					/*var store=Ext.create('Ext.data.Store', {
						fields: [{name:'name'}, {name:'value'}],						
					    data :[
			             {name: 'name1',  value: '11'},
			             {name: 'age1',   value: '22'},
			             {name: 'phone1', value: '33'},
			             {name: 'alive1', value: '33'}]
					});*/
					
					/*var store=Ext.create('Ext.data.Store', {
						autoLoad: true,
						model:'UserManager.model.Dictionary',						
						proxy: {
					         type: 'ajax',
					         url: webRoot+'product/system/userManager/getDictItem.do',
					         reader: {
					             type: 'json',
					             root: 'records'
					         }
					     }
					});
					departmentCombo.bindStore(store);*/
					
				}
			}
			
		}
	]
})