Ext.define("RoleManager.controller.RoleController",{
	extend:"Ext.app.Controller",
	models:['Role','Organization'], 
	stores:['Role','Organization'],
	views:['West','RolePanel','RoleWin','ImportWin', 'PermissionSetWin'], //每一个控制器需要列举它使用的Views并且这些Views会自动被加载
	
	requires: [
        'Ext.grid.*',
        'ResourceManager.model.Resource',
        'ResourceManager.store.Resource'
	],  
	refs: [{
		ref		  	: 'roleGridPanel',
	   	selector  	: 'rolePanel gridpanel[name=roleGridPanel]'
	}],
	init:function(){
		this.control({
			'rolePanel gridpanel[name=roleGridPanel] button[action=query]' :{//查询
				click:this.queryRole
			},		
			
			'rolePanel gridpanel[name=roleGridPanel] button[action=add]' :{//添加
				click:this.addRole
			},			
			'rolePanel gridpanel[name=roleGridPanel] button[action=copy]' :{//复制
				click:this.copyRole
			},
			'rolePanel gridpanel[name=roleGridPanel] button[action=delete]' :{  //删除
				click:this.deleteRole
			},
			'rolePanel gridpanel[name=roleGridPanel] button[action=setPermissions]' :{  //删除
				click:this.setPermissions
			},
			'rolePanel gridpanel[name=roleGridPanel] button[action=exportExcel]' :{//导出Excel
				click:this.exportExcel
			},
			'rolePanel gridpanel[name=roleGridPanel] button[action=importExcel]':{ //导入Excel
				click:this.importExcel
			},
			'rolePanel gridpanel[name=roleGridPanel]' :{ //编辑
				itemdblclick:this.editRole
			}	
		})
	},
	
	addRole:function(button){		
		var me=this;
		var win=me.getRoleWin("Role添加");     //创建组件
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			var record=Ext.create('RoleManager.model.Role');
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getRoleStore();	 
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
	
	copyRole:function(button){
		var me=this;
		var records=button.up('grid').getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		}
		records[0].data.id='';//复制选中记录除ID以外的数据,
		var model=Ext.create('RoleManager.model.Role',records[0].data);
		var win=me.getRoleWin("Role复制"); 
		var formPanel=win.down('form');	
		formPanel.loadRecord(model);	//载入一个 Ext.data.Model 到表单中,用于保存表单数据
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 	//返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record);   //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getRoleStore();	 
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
	
	editRole:function(view, record, item, index, e, obj){
		var me=this;
		var win=me.getRoleWin("Role修改"); 
		var formPanel=win.down('form');
		formPanel.loadRecord(record);		//传入选中records	
		formPanel.down('button[action=save]').setHandler(function() {
			if (formPanel.getForm().isValid()) {
				var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
				formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
				console.log(record.data);			 //测试数据是否更新
				var store=me.getRoleStore();	 //var store=Ext.getCmp('listID').getStore();
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
	queryRole: function(button){
		var me=this;
		var win=me.getRoleWin("Role查询");
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			var values=formPanel.getForm().getValues();
			var store=me.getRoleStore();	
			store.load({
				params: { "filter": JSON.stringify(values)} 
			});
	
		});
		win.show();
	},

	
	deleteRole:function(btn){
		var me=this;
		var records=me.getRoleGridPanel().getSelectionModel().getSelection();
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
					var store=me.getRoleStore();
					store.remove(records);
					store.sync({
						success:function(batch, options){
							
							var data=batch.proxy.reader.jsonData;
							Ext.Msg.alert('提示','成功删除'+data.count+'条记录');
							var currentPage = store.currentPage; // 当前页码  
							if(store.count()==0 && currentPage !=1){
					            store.currentPage=currentPage-1;
								me.getRoleGridPanel().down('pagingtoolbar').doRefresh();

							}

						}
					});
				}
			});
			
		}
	},
	
	setPermissions : function(button) {
		var me = this;		
		var records = button.up('grid').getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录');
			return;
		}
		//当前记录的角色id
		var roleId=records[0].data.roleId;
	
		var win=me.getPermissionSetWin("权限资源分配");
		win.removeAll();
		//var tree=win.down('treepanel');
	  	var treeStore = Ext.create('Ext.data.TreeStore', {
            proxy: {
                type: 'ajax',
                url: webRoot+'/product/system/resourceManager/getResourceGrid.do',
                extraParams: {
	               filter: roleId
	 		    },
	 		    reader:{
					type:'json',
					root:'data' //读取data属性
				}
            },
          	model:ResourceManager.model.Resource,
 		    autoLoad: true
 		/*    root: {
				id:1, //节点id值通过通过request node属性上传而不是id属性,根节点无id时node=root
				//text:'root节点名称',//默认为Root;
				expanded: true
    		}*/
        });
		var tree=Ext.create('Ext.tree.Panel', {
			autoScroll:true,
			width:400, 	//不定义大小Window将会自适应
			height:200,
			rootVisible:false,		//定义根节点是否见,如果定义为true treestorey要配置root:{expanded:true}
			border: false,
			store:treeStore,
			listeners:{
				beforeitemclick :function(view, record, item, index, e, eOpts) {
					var el = e.getTarget("input");
					if(el){//点击复选框,勾选当前节点及所有子节点
						record = view.getRecord(item);
						var checked = !record.get('checked');
						checkChild(record, checked);					
					}
			
				}
			}
		});
		tree.expandAll(); 
		win.add(tree);
		win.down('button[action=save]').setHandler(function() {
			var list=tree.getChecked();
			var checkedIds=[]
			Ext.Array.each(list, function(item, index, countriesItSelf) {
				checkedIds.push(item.data.id);
			});
			Ext.Ajax.request({
			    url: webRoot+'/product/system/resourceManager/updatePermission.do',
			    params: {
			        'checkedIds': checkedIds,
			        'roleId':roleId
			    },
			    success: function(response){
			    	debugger;
			    	//treeStore.reload();
			        var data = Ext.decode(response.responseText);
					Ext.Msg.alert('提示',data.tip);
			        // process server response here
		    	}
			});
		
		});
		win.show();
		//win.remove(tree);
		

	},
	/**
	 * 导出excel
	 */
	exportExcel:function(){
		//导出全部
    	var form = Ext.create('Ext.form.Panel', {
			standardSubmit : true,
			url :webRoot+'/product/system/roleManager/exportExcel.do'
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
			var fields=me.getRoleWin('Role修改').down('form').getForm().getFields();
			
			var obj={};
			fields.each(function(field){
				//console.log(field);
				obj[field.name]=field.fieldLabel;
			})
			var json=JSON.stringify(obj);  //var json=Ext.encode(obj);会有中文乱码问题
			
			var form = Ext.create('Ext.form.Panel', {
				standardSubmit : true,
				url :webRoot+'/product/system/roleManager/downloadTemplate.do'
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
				url:webRoot+'/product/system/roleManager/importExcel.do',
				success:function(form, action){
					Ext.Msg.alert('提示',  '导入成功');
					var data=action.result;
					var store=me.getRoleStore();
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
	getRoleWin:function(title){
		if(title==null || title==''){
			title='窗口标题未定义';
		}
		var win=Ext.getCmp('roleWinID');
     	if(!win){
     		win=Ext.widget("roleWin",{title:title});     //创建组件
     	}else{
     		win.setTitle(title);
     	}
     	return win;
	},
	
	getPermissionSetWin:function(title){
		if(title==null || title==''){
			title='窗口标题未定义';
		}
		var win=Ext.getCmp('permissionSetWinID');
     	if(!win){
     		win=Ext.widget("permissionSetWin",{title:title});     //创建组件
     	}else{
     		win.setTitle(title);
     	}
     	return win;
	}

})