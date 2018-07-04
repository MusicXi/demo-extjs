Ext.define("UserManager.controller.UserManager",{//属于application(name="UserManager")的应用
	extend:"Ext.app.Controller",
	models:['User','Organization','Dictionary', 'Simple'], 
	stores:['User','Organization'],
	views:["West","Center","UserAdd","UserEdit",'ImportWin', 'RoleSetWin','RoleSelectPanel'], //每一个控制器需要列举它使用的Views并且这些Views会自动被加载
	mixins: {
		commonMethod 		: 'UserManager.common.CommonMethod'
	},
	requires: [
        'Ext.grid.*',
        'RoleManager.model.Role',
        'RoleManager.store.Role',
        'ZTEsoft.picker.TreePicker'
        //'Ext.ux.TreePicker'
	],  
	refs: [{
		ref		  	: 'userGridPanel',
	   	selector  	: 'center gridpanel[name=userGridPanel]'
	}],
	init:function(){
		this.control({
			'west treepanel[name=orgTreePanel]':{
				itemclick: this.itemclickTree,
				beforeitemclick :this.beforeitemclickTree
			},
			'center gridpanel[name=userGridPanel] button[action=query]' :{//用户查询
				click:this.queryUser
			},		
			
			'center gridpanel[name=userGridPanel] button[action=openAdd]' :{//用户添加
				click:this.openUserAdd
			},			
			'center gridpanel[name=userGridPanel] button[action=openCopy]' :{//用户复制
				click:this.openUserCopy
			},
			'center gridpanel[name=userGridPanel] button[action=delete]' :{  //用户删除
				click:this.deleteUser
			},
			'center gridpanel[name=userGridPanel] button[action=setRole]' :{  //角色分配
				click:this.setRole
			},
			'center gridpanel[name=userGridPanel] button[action=exportExcel]' :{//导出Excel
				click:this.exportExcel
			},
			'center gridpanel[name=userGridPanel] button[action=importExcel]':{ //导入Excel
				click:this.importExcel
			},
			'center gridpanel[name=userGridPanel]' :{
				itemdblclick:this.openUserEdit
			}
			/*'userEdit button[action=update]' :{
				click:this.executeUpdate
			},*/
			
		/*	'center button[action=openUserEdit]' :{
				click:this.openUserEdit
			},*/
			/*'importWin button[action=import]':{
				click:this.importExcel
			}*/
		})		
	},
	
	setRole:function(button){
		debugger;
		var me=this;
		var records=button.up('grid').getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		}
		var record=records[0].data;
	
		var win=me.getRoleSetWin("角色分配【用户:"+record.name+"】");
		var selectedPanel=win.down('roleSelectPanel').down('#grid2');
		var unselectedPanel=win.down('roleSelectPanel').down('#grid1');
		//加载可选角色信息
		unselectedPanel.store.load({
			params:{"userId":record.id}
		})
		
		//重新加载用户角色信息
	    selectedPanel.store.removeAll();
	    selectedPanel.store.load({
	    	params:{"userId":record.id},
	    	callback: function (record, options, success) {	
              	unselectedPanel.store.remove(record);
            }
	    });
	
	    //unselectedPanel.store.remove();

		win.down('button[action=save]').setHandler(function(){
			debugger;
			Ext.Msg.alert("dd");
			var userRole=[];
			var items=selectedPanel.store.data.items;
			for(var i=0;i<items.length;i++){
				userRole.push({
					"roleId":items[i].data.roleId,
					"userId":record.id
				})
				console.log(userRole);
			}
			Ext.Ajax.request({
				url: webRoot+'/product/system/userRoleManager/test.do',
				//params: JSON.stringify(userRole),
				jsonData:userRole,
				//params: userRole,
				success: function(response){
				    var text = response.responseText;
				    // process server response here
			    }
			});
			//selectedPanel.store.sync();
		});
		win.show();
	},

	itemclickTree:function(view, record, item, index, e, obj){
		var me=this;
		var store=this.getUserStore();
		var orgId=me.findChildNodesId(record);
		var filter={"organizationId":orgId};
		store.load({ 
			params: { "filter": JSON.stringify(filter)} 
		});
		
	},
	beforeitemclickTree : function(view, record, item, index, e, eOpts) {
		var me=this;
		var el = e.getTarget("input");
		if(el){//点击复选框,勾选当前节点及所有子节点
			record = view.getRecord(item);
			var checked = !record.get('checked');
			me.checkChild(record, checked);		
		}
	},
	
	

	openUserAdd:function(button){
		
		var me=this;
		var win=Ext.widget("userEdit",{title:'用户添加'});     //创建组件
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			var record=Ext.create('UserManager.model.User');
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getUserStore();	 
			store.add(record);					 //添加 Model 实例到 Store
			store.sync({
				success:function(){
					Ext.Msg.alert('提示','添加成功！')
					store.reload();
					win.hide();
				},
				failure:function(batch, options){
					
					var data=batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.msg);
					store.reload();
					win.hide();
				},
				callback:function(batch, options){
					
				}
			});						 //后台实现数据库同步
		
		});

		/*formPanel.getForm().load({
			url:webRoot+"/product/system/userManager/getUserById.do",
			params:{
				username:'ims'
			}
		});*/

		win.show();
	},
	
	openUserCopy:function(button){
		var me=this;
		var records=button.up('grid').getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		}
		records[0].data.id='';//复制选中记录除ID以外的数据,
		var model=Ext.create('UserManager.model.User',records[0].data);
		var win=Ext.widget("userEdit",{title:'用户复制'});     //创建组件
		var formPanel=win.down('form');	
		formPanel.loadRecord(model);	//载入一个 Ext.data.Model 到表单中,用于保存表单数据
		formPanel.down('button[action=save]').setHandler(function(){
			
			var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);
			var store=me.getUserStore();	 
			store.add(record);					 //添加 Model 实例到 Store
			store.sync({//后台实现数据库同步
				success:function(){
					
					Ext.Msg.alert('提示','复制成功！')
					store.reload();
					win.hide();
				}
			});						 
			
		});
		
		win.show();
	},
	
	openUserEdit:function(view, record, item, index, e, obj){
		var me=this;
		var win=Ext.create('UserManager.view.UserEdit');
		var formPanel=win.down('form');
		formPanel.loadRecord(record);		//传入选中records	
		formPanel.down('button[action=save]').setHandler(function() {
			if (formPanel.getForm().isValid()) {
				var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
				formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
				console.log(record.data);			 //测试数据是否更新
				var store=me.getUserStore();	 //var store=Ext.getCmp('listID').getStore();
				store.sync({
					success:function(batch, options){
						var data=batch.proxy.reader.jsonData;
						Ext.Msg.alert('提示',data.msg);
						store.reload();
					},
					failure:function(batch, options){
						var data=batch.proxy.reader.jsonData;
						Ext.Msg.alert('提示',data.status+":"+data.msg);
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
	queryUser: function(button){
		var me=this;
		var win=Ext.widget("userEdit",{title:'用户查询'});     //创建组件
		var formPanel=win.down('form');	
		formPanel.down('button[action=save]').setHandler(function(){
			var values=formPanel.getForm().getValues();
			var store=me.getUserStore();	
			store.load({
				params: { "filter": JSON.stringify(values)} 
			});
			// 清除过滤器而不更新界面(UI)
			//store.clearFilter(true);//清空过滤器,将 Record cache 恢复到没有过滤的情况.
			
			//var filters=[];//填充filter
			//枚举对象内置属性的循环
			/*for(var p in values){
				if(values[p] != null && values[p] !=''){
					filters.push({property:p, value:values[p]})
				}
			}*/
			//store.filter('username','dd');
			/*store.filter([
			              {property: "email", value: /\.com$/},
			              {property: "username", value: 'dd'},
			              {property: "name", value: '这'},
			 ]);*/
			//store.filter(filters);
	
		
		});

		/*formPanel.getForm().load({
			url:webRoot+"/product/system/userManager/getUserById.do",
			params:{
				username:'ims'
			}
		});*/

		win.show();
	},
	executeUpdate:function(button){
		var me=this;
		var win=button.up('window');
		var formPanel=win.down('form');
		if (formPanel.getForm().isValid()) {
			var record=formPanel.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
			formPanel.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(record.data);			 //测试数据是否更新
			var store=me.getUserStore();	 //var store=Ext.getCmp('listID').getStore();
			store.sync({
				success:function(){
					Ext.Msg.alert('提示','更新成功！');
					store.reload();
				}
			});						 //后台实现数据库同步
			win.close();
		}
		
	},
	
	deleteUser:function(btn){
		//
		var me=this;
		var records=me.getUserGridPanel().getSelectionModel().getSelection();
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
					var store=me.getUserStore();
					store.remove(records);
					store.sync({
						success:function(batch, options){
							
							var data=batch.proxy.reader.jsonData;
							Ext.Msg.alert('提示','成功删除'+data.count+'条记录');
							var currentPage = store.currentPage; // 当前页码  
							if(store.count()==0 && currentPage !=1){
					            store.currentPage=currentPage-1;
								me.getUserGridPanel().down('pagingtoolbar').doRefresh();

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
			url :webRoot+'/product/system/userManager/exportExcel.do'
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
		
		//校验导入的文件格式
		//upfileForm.query('filefield[name=nodefile]');
	/*	var excelFile=formPanel.form.findField('excelFile');
		excelFile.on('change',function(){
			Ext.Msg.alert('d','d');
		});*/
		
		//下载模板
		formPanel.down('button[action=download]').setHandler(function(){
			debugger;

		/*	var appWindow = window.open(webRoot+'/product/system/userManager/downloadTemplate.do?model='+Ext.encode(model.data));
			appWindow.focus();*/
			
			var fields=Ext.create('UserManager.view.UserEdit').down('form').getForm().getFields();
			
			
			//使用拼接字符串方式-不转换
			/*var data=[];
			fields.each(function(field){
				data.push('"'+field.name+'":"'+field.fieldLabel+'"');
			})
			var json="{"+data.join(',')+"}";*/
			
			//使用Json2 obj->json
			var obj={};
			fields.each(function(field){
				//console.log(field);
				obj[field.name]=field.fieldLabel;
			})
			var json=JSON.stringify(obj);  //var json=Ext.encode(obj);会有中文乱码问题
			
			var form = Ext.create('Ext.form.Panel', {
				standardSubmit : true,
				url :webRoot+'/product/system/userManager/downloadTemplate.do'
			});
	    	var params = {'model':json};
	    	form.submit({
				params : params
			});
	    	
	    	//无效待测试
			/*Ext.Ajax.request({
	        	url: webRoot+'/product/system/userManager/downloadTemplate.do',
	        	params: {
	        		model: Ext.encode(model.data)//对象转换为json
	        	},
	        	success: function(response,opts){
	        		var jsonData = Ext.decode(response.responseText);
	        		Ext.Msg.alert('提示','内容'+jsonData.msg);
	        		
	        	}
			});*/
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
				url:webRoot+'/product/system/userManager/importExcel.do',
				success:function(form, action){
					Ext.Msg.alert('提示',  '导入成功');
					var data=action.result;
					var store=me.getUserStore();
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
	 * 获取角色分配窗口
	 */
	getRoleSetWin:function(title){
		if(title==null || title==''){
			title='窗口标题未定义';
		}
		var win=Ext.getCmp('roleSetWinID');
     	if(!win){
     		win=Ext.widget("roleSetWin",{title:title});     //创建组件
     	}else{
     		win.setTitle(title);
     	}
     	return win;	 
	}
	
})