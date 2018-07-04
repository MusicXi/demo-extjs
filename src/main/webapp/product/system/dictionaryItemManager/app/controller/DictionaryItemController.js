Ext.define("DictionaryItemManager.controller.DictionaryItemController",{
	extend:"Ext.app.Controller",
	models:['DictionaryItem'], 
	stores:['DictionaryItem'],
	views:['DictionaryItemPanel','DictionaryItemWin','ImportWin'], //每一个控制器需要列举它使用的Views并且这些Views会自动被加载
	refs: [{
		ref		  	: 'dictionaryItemGridPanel',
	   	selector  	: 'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel]'
	}],
	init:function(){
		this.control({
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=query]' :{//查询
				click:this.queryDictionaryItem
			},		
			
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=add]' :{//添加
				click:this.addDictionaryItem
			},			
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=copy]' :{//复制
				click:this.copyDictionaryItem
			},
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=delete]' :{  //删除
				click:this.deleteDictionaryItem
			},
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=exportExcel]' :{//导出Excel
				click:this.exportExcel
			},
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel] button[action=importExcel]':{ //导入Excel
				click:this.importExcel
			},
			'dictionaryItemPanel gridpanel[name=dictionaryItemGridPanel]' :{ //编辑
				itemdblclick:this.editDictionaryItem
			}	
		})
	},
	
	addDictionaryItem:function(button){		
		var me=this;
		var win=me.getDictionaryItemWin("DictionaryItem添加");     //创建组件
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			var record=Ext.create('DictionaryItemManager.model.DictionaryItem');
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getDictionaryItemStore();	 
			store.add(record);					 //添加 Model 实例到 Store
			store.sync({
				success:function(){
					Ext.Msg.alert('提示','添加成功！')
					store.reload();
					win.hide();
				},
				failure:function(batch, options){			
					var data=batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.tip);
					store.reload();
					win.hide();
				},
				callback:function(batch, options){
					
				}
			});						 
		
		});

		win.show();
	},
	
	copyDictionaryItem:function(button){
		var me=this;
		var records=button.up('grid').getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		}
		records[0].data.id='';//复制选中记录除ID以外的数据,
		var model=Ext.create('DictionaryItemManager.model.DictionaryItem',records[0].data);
		var win=me.getDictionaryItemWin("DictionaryItem复制"); 
		var formPanel=win.down('form');	
		formPanel.loadRecord(model);	//载入一个 Ext.data.Model 到表单中,用于保存表单数据
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 	//返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record);   //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getDictionaryItemStore();	 
			store.add(record);					 		//添加 Model 实例到 Store
			store.sync({//后台实现数据库同步
				success:function(batch, options){	
					Ext.Msg.alert('提示','复制成功！')
					store.reload();
					win.hide();
				}
			});						 
			
		});
		
		win.show();
	},
	
	editDictionaryItem:function(view, record, item, index, e, obj){
		var me=this;
		var win=me.getDictionaryItemWin("DictionaryItem修改"); 
		var formPanel=win.down('form');
		formPanel.loadRecord(record);		//传入选中records	
		formPanel.down('button[action=save]').setHandler(function() {
			if (formPanel.getForm().isValid()) {
				var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
				formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
				console.log(record.data);			 //测试数据是否更新
				var store=me.getDictionaryItemStore();	 //var store=Ext.getCmp('listID').getStore();
				store.sync({
					success:function(){
						Ext.Msg.alert('提示','更新成功！');
						store.reload();
					}
				});						 //后台实现数据库同步
				win.close();
			}else{
				Ext.Msg.alert('提示','表单验证不通过!');
			}
		});
	
		win.show();
	},
	
	/**
	 * 查询用户
	 */
	queryDictionaryItem: function(button){
		var me=this;
		var win=me.getDictionaryItemWin("DictionaryItem查询");
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			var values=formPanel.getForm().getValues();
			var store=me.getDictionaryItemStore();	
			store.load({
				params: { "filter": JSON.stringify(values)} 
			});
	
		});
		win.show();
	},

	
	deleteDictionaryItem:function(btn){
		var me=this;
		var records=me.getDictionaryItemGridPanel().getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.show({
				title:'提示',
				msg:"请选择删除对象",
				icon:Ext.MessageBox.WARNING,
				buttons:Ext.MessageBox.OK
			});
		}else{
			Ext.MessageBox.confirm('提示','是否删除选中的 '+records.length+' 条数据', function(button){
				if(button=='yes'){
					var store=me.getDictionaryItemStore();
					store.remove(records);
					store.sync({
						success:function(batch, options){
							
							var data=batch.proxy.reader.jsonData;
							Ext.Msg.alert('提示','成功删除'+data.count+'条记录');
							var currentPage = store.currentPage; // 当前页码  
							if(store.count()==0 && currentPage !=1){
					            store.currentPage=currentPage-1;
								me.getDictionaryItemGridPanel().down('pagingtoolbar').doRefresh();

							}

						}
					});
				}
			});
			
		}
	},
	/**
	 * 导出excel
	 */
	exportExcel:function(){
		//导出全部
    	var form = Ext.create('Ext.form.Panel', {
			standardSubmit : true,
			url :webRoot+'/product/system/dictionaryItemManager/exportExcel.do'
		});
    	var params = {};
    	form.submit({
			params : params
		});
	},
	
	/**
	 * 导入excel
	 */
	importExcel:function(){
		var me=this;
		var win=Ext.getCmp('importWinID');
		if(!win){
			win=Ext.widget('importWin');
		}
		var formPanel=win.down('form');
		
		//下载模板
		formPanel.down('button[action=download]').setHandler(function(){				
			var fields=me.getDictionaryItemWin('DictionaryItem修改').down('form').getForm().getFields();
			
			var obj={};
			fields.each(function(field){
				//console.log(field);
				obj[field.name]=field.fieldLabel;
			})
			var json=JSON.stringify(obj);  //var json=Ext.encode(obj);会有中文乱码问题
			
			var form = Ext.create('Ext.form.Panel', {
				standardSubmit : true,
				url :webRoot+'/product/system/dictionaryItemManager/downloadTemplate.do'
			});
	    	var params = {'model':json};
	    	form.submit({
				params : params
			});
	    	
		})
		
		//执行导入
		formPanel.down('button[action=import]').setHandler(function(){
			if(!formPanel.getForm().isValid()){
				Ext.Msg.alert('提示','表单校验未通过');
				return;
			}
			//提交上传文件
			formPanel.submit({
				waitMsg:'正在导入数据，请稍候...',
				url:webRoot+'/product/system/dictionaryItemManager/importExcel.do',
				success:function(form, action){
					Ext.Msg.alert('提示',  '导入成功');
					var data=action.result;
					var store=me.getDictionaryItemStore();
					store.load();
					win.close();				
				},
				failure:function(form, action) {
					var data=action.result;

					Ext.Msg.alert('提示',  '导入失败');

				} 
			})
			
		});
		win.show();
	},
	
	/**
	 * 获取资源窗口
	 */
	getDictionaryItemWin:function(title){
		if(title==null || title==''){
			title='窗口标题未定义';
		}
		var win=Ext.getCmp('dictionaryItemWinID');
     	if(!win){
     		win=Ext.widget("dictionaryItemWin",{title:title});     //创建组件
     	}else{
     		win.setTitle(title);
     	}
     	return win;
	}

})