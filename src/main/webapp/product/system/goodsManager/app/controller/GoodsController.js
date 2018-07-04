Ext.define("GoodsManager.controller.GoodsController",{
	extend:"Ext.app.Controller",
	models:['Goods'], 
	stores:['Goods'],
	views:['GoodsPanel','GoodsWin','ImportWin'], //每一个控制器需要列举它使用的Views并且这些Views会自动被加载
	refs: [{
		ref		  	: 'goodsGridPanel',
	   	selector  	: 'goodsPanel gridpanel[name=goodsGridPanel]'
	}],
	init:function(){
		this.control({
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=query]' :{//查询
				click:this.queryGoods
			},		
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=add]' :{//添加
				click:this.addGoods
			},			
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=copy]' :{//复制
				click:this.copyGoods
			},
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=delete]' :{//删除
				click:this.deleteGoods
			},
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=refresh]' :{//刷新
				click:this.refreshGoods
			},
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=exportExcel]' :{//导出Excel
				click:this.exportExcel
			},
			'goodsPanel gridpanel[name=goodsGridPanel] button[action=importExcel]':{ //导入Excel
				click:this.importExcel
			},
			'goodsPanel gridpanel[name=goodsGridPanel]' :{ //编辑
				itemdblclick:this.editGoods
			}	
		})
	},
	
	addGoods: function(button){		
		var me = this;
		var win = me.getGoodsWin("Goods添加");     //创建组件
		var formPanel = win.down('form');	
		//formPanel.form.findField("id").setDisabled(true);
		//formPanel.form.findField("id").setReadOnly(true);
		formPanel.down('button[action=save]').setHandler(function(){
			var record = Ext.create('GoodsManager.model.Goods');
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store = me.getGoodsStore();	 
			store.add(record);					 //添加 Model 实例到 Store
			store.sync({
				success: function(batch, options){
					var data = batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '添加成功!');
					win.hide();
				},
				failure: function(batch, options){			
					var data = batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '操作失败');
					store.reload();
					win.hide();
				},
				callback: function(batch, options){
					
				}
			});						 
		
		});

		win.show();
	},
	
	copyGoods: function(button){
		var me = this;
		
		var records = button.up('grid').getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		}
		
		records[0].data.id = '';						//复制选中记录除ID以外的数据,
		var model = Ext.create('GoodsManager.model.Goods',records[0].data);
		var win = me.getGoodsWin("Goods复制"); 
		var formPanel = win.down('form');	
		
		formPanel.loadRecord(model);					//载入一个 Ext.data.Model 到表单中,用于保存表单数据
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record = formPanel.getRecord();		 	//返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record);   //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store = me.getGoodsStore();	 
			store.add(record);					 		//添加 Model 实例到 Store
			store.sync({//后台实现数据库同步
				success: function(batch, options){
					var data = batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '复制成功！');
					store.reload();
					win.hide();
				},
				failure: function(batch, options){			
					var data =batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '操作失败');
					store.reload();
					win.hide();
				},
				callback: function(batch, options){
					
				}
			});						 
			
		});
		
		win.show();
	},
	
	editGoods: function(view, record, item, index, e, obj){
		var me = this;
		var win = me.getGoodsWin("Goods修改"); 
		var formPanel = win.down('form');
		
		formPanel.loadRecord(record);		//传入选中records	
		formPanel.down('button[action=save]').setHandler(function() {
			if (! formPanel.getForm().isValid()) {
				Ext.Msg.alert('提示','表单验证不通过!');
				return;								 
			}
			
			var record = formPanel.getRecord();				 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record);		 //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);			 			 //测试数据是否更新
			var store = me.getGoodsStore();	 //var store=Ext.getCmp('listID').getStore();
			store.sync({//后台实现数据库同步
				success: function(batch, options){
					var data = batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '修改成功!');
					win.hide();
				},
				failure: function(batch, options){			
					var data = batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg || '操作失败');
					store.reload();
					win.hide();
				},
				callback: function(batch, options){
					
				}
			});	
		});
	
		win.show();
	},
	
	/**
	 * 查询用户
	 */
	queryGoods: function(button){
		var me = this;
		var win = me.getGoodsWin("Goods查询");
		var formPanel = win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			var values = formPanel.getForm().getValues();
			var store = me.getGoodsStore();	
			store.load({
				params: { "filter": JSON.stringify(values)} 
			});
	
		});
		win.show();
	},

	
	deleteGoods: function(btn){
		var me = this;
		var records = me.getGoodsGridPanel().getSelectionModel().getSelection();
		
		if (records.length == 0) {
			Ext.Msg.show({
				title:'提示',
				msg:"请选择删除对象",
				icon:Ext.MessageBox.WARNING,
				buttons:Ext.MessageBox.OK
			});
		}
		
		Ext.MessageBox.confirm('提示','是否删除选中的 '+records.length+' 条数据', function(button){
			if (button == 'yes') {
				var store = me.getGoodsStore();
				store.remove(records);
				store.sync({
					success: function(batch, options){
						var data = batch.proxy.reader.jsonData;
						Ext.Msg.alert('提示','成功删除'+data.count+'条记录');
						var currentPage = store.currentPage; // 当前页码  
						if (store.count() == 0 && currentPage != 1) {
				            store.currentPage = currentPage - 1;
							me.getGoodsGridPanel().down('pagingtoolbar').doRefresh();
							return;
						}
						store.reload();

					}
				});
			}
		});
			
		
	},
	
	refreshGoods: function(){
		var me = this;
		var store = me.getGoodsStore();	
		store.reload();
	},
	/**
	 * 导出excel
	 */
	exportExcel: function(){
		//导出全部
    	var form = Ext.create('Ext.form.Panel', {
			standardSubmit : true,
			url :webRoot+'/product/system/goodsManager/exportExcel.do'
		});
    	var params = {};
    	form.submit({
			params : params
		});
	},
	
	/**
	 * 导入excel
	 */
	importExcel: function(){
		var me = this;
		var win = Ext.getCmp('importWinID');
		if (! win) {
			win = Ext.widget('importWin');
		}
		var formPanel = win.down('form');
		
		//下载模板
		formPanel.down('button[action=download]').setHandler(function(){				
			var fields = me.getGoodsWin('Goods修改').down('form').getForm().getFields();
			
			var obj = {};
			fields.each(function(field){
				//console.log(field);
				obj[field.name]=field.fieldLabel;
			})
			var json = JSON.stringify(obj);  //var json=Ext.encode(obj);会有中文乱码问题
			
			var form = Ext.create('Ext.form.Panel', {
				standardSubmit : true,
				url :webRoot+'/product/system/goodsManager/downloadTemplate.do'
			});
	    	var params = {'model':json};
	    	form.submit({
				params : params
			});
	    	
		})
		
		//执行导入
		formPanel.down('button[action=import]').setHandler(function(){
			if (! formPanel.getForm().isValid()) {
				Ext.Msg.alert('提示','表单校验未通过');
				return;
			}
			//提交上传文件
			formPanel.submit({
				waitMsg: '正在导入数据，请稍候...',
				url: webRoot+'/product/system/goodsManager/importExcel.do',
				success: function(form, action){
					Ext.Msg.alert('提示',  '导入成功');
					var data = action.result;
					var store = me.getGoodsStore();
					store.load();
					win.close();				
				},
				failure: function(form, action) {
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
	getGoodsWin: function(title){
		if (title == null || title == '') {
			title ='窗口标题未定义';
		}
		var win = Ext.getCmp('goodsWinID');
		
     	if (! win) {
     		win = Ext.widget("goodsWin", {title:title});     //创建组件
     	} else {
     		win.setTitle(title);
     	}
     	return win;
	}

})