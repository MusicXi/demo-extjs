Ext.define("ResourceManager.controller.ResourceManager",{//属于application(name="UserManager")的应用
	extend:"Ext.app.Controller",
	models:['Resource'], 
	stores:['Resource'],
	views:['Resource','ResourceWin'], //每一个控制器需要列举它使用的Views并且这些Views会自动被加载
	init:function(){
		this.control({
			'resource treepanel[name=orgTreePanel]':{
				itemclick: this.test
			},
			
			'resource':{
				itemcontextmenu:this.setResourceRightMenu //列表右键显示
			},
			
			'resource button[action=delete]' :{
				click:this.deleteResource		//删除资源
			},
			'resource button[action=save]' :{
				click:this.saveResource			//保存资源
			},
			'resourceWin':{
				//beforeshow:this.beforeShowHandler, //编辑窗口-显示前-处理业务逻辑
            	hide:this.resetEditorWin		//重置编辑窗口
			}
		})
	},

	/**
	 * delete resource
	 */
	deleteResource:function(){
		Ext.Msg.alert('d','删除');
	},
	
	/**
	 * save resource
	 */
	saveResource:function(){
		debugger;
		var me=this;
		var store=me.getResourceStore();
		store.sync({
			success:function(batch, options){
				var data=batch.proxy.reader.jsonData;
				Ext.Msg.alert('提示',data.tip);
				store.reload();
			},
			failure:function(batch, options){
				var data=batch.proxy.reader.jsonData;
				Ext.Msg.alert('提示',data.tip!=null?data.tip:"添加失败");
				store.reload();
			}
			
		});					
	},
	
	/**
	 * 创建资源
	 */
	createResource:function(view,record,item,index,e,eOpts){
		var me=this;
		var win=me.getResourceWin("资源添加");     	
     	var formPanel=win.down('form');	
     	var modal=Ext.create('ResourceManager.model.Resource');
     	
    	//填充上级资源下拉列表数据
     	formPanel.form.findField('parentId').store=new Ext.data.ArrayStore({
			fields: ['value', 'label'],
			data : [[record.data.id,record.data.name]]
 		});
     	//获取父节点的id
     	modal.data.parentId=record.data.id;
     	
     	formPanel.loadRecord(modal);
		formPanel.down('button[action=save]').setHandler(function(){
			debugger;
			var newNode=formPanel.getRecord();
			formPanel.getForm().updateRecord(newNode); //传入的Ext.data.Model对象内保存表单写入的值
			console.log(newNode.data);
			var store=me.getResourceStore();	 
//			store.add(record);					 //添加 Model 实例到 Store
			record.appendChild(newNode)			 //添加当前节点的子节点
			store.sync({						 //与服务端同步
				success:function(batch, options){
					debugger;
					var data=batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.tip!=null?data.tip:'操作成功');
					store.reload();
					win.hide();
				},
				failure:function(batch, options){
					debugger;
					var data=batch.proxy.reader.jsonData;
					Ext.Msg.alert('提示',data.tip!=null?data.tip:"操作失败");
					store.reload();
					win.hide();
				},
				callback:function(batch, options){
					
				}
			});						 
		});
		win.show();
	},
	
	/**
	 * 修改资源
	 */
	updateResource:function(view,record,item,index,e,eOpts){
		var me=this;
		var win=me.getResourceWin("资源编辑");

     	var formPanel=win.down('form');	
     	//填充上级资源下拉列表数据,加载数据之前
     	var parentNode=record.parentNode.data;
     	formPanel.form.findField('parentId').store=new Ext.data.ArrayStore({
			fields: ['value', 'label'],
			data : [[parentNode.id,parentNode.name==''?'资源根节点':parentNode.name]]
 		});
     	formPanel.loadRecord(record);
    	formPanel.down('button[action=save]').setHandler(function(){
			debugger;
			if (formPanel.getForm().isValid()) {
				var node=formPanel.getRecord();
				formPanel.getForm().updateRecord(node); //传入的Ext.data.Model对象内保存表单写入的值
				console.log(node.data);
				var store=me.getResourceStore();	 
				store.sync({						 //与服务端同步
					success:function(batch, options){
						var data=batch.proxy.reader.jsonData;
						Ext.Msg.alert('提示',data.tip!=null?data.tip:"操作成功");
						store.reload();
						win.hide();
					},
					failure:function(batch, options){
						debugger;
						var data=batch.proxy.reader.jsonData;
						Ext.Msg.alert('提示',data.tip!=null?data.tip:"操作失败");
						store.reload();
						win.hide();
					}
				});	
			}else{
				Ext.Msg.alert('提示','表单验证不通过!');
			}
							 
		});
		win.show();
	},

	/**
	 * 资源列表右键菜单
	 */
	setResourceRightMenu:function(view,record,item,index,e,eOpts){
		
		var me=this;
		e.preventDefault();
 		e.stopEvent();   
 		var items=[]
 		//只有"菜单"类型节点才能增加子节点
 		if(record.data.type=='menu'){
 			items.push({
   	 			text: '新增',
   	 			iconCls : 'toolbar-add',
 				handler: function(){
 					me.createResource(view,record,item,index,e,eOpts);
 				}		   	 					
 			})
 		}
 		
 		items.push({
			text : "查看",
			iconCls : 'toolbar-view',
  	    	handler: function(){
  	    		//me.previewResource(view,record,item,index,e,eOpts);
			}
        },{
 			text: '编辑',
 			iconCls : 'toolbar-edit',
			handler: function(){
				me.updateResource(view,record,item,index,e,eOpts);
			}
		});
 		
 		//只有"按钮"类型资源才可以删除
 		if(record.data.type=='button'){
 			items.push({
   	 			text: '删除',
   	 			iconCls : 'toolbar-delete',
 				handler: function(){
 					//me.deleteTaskType(view);
 				}
 			});
 		}
 	    var menus = Ext.create('Ext.menu.Menu', {
 	        items: items
 	    });
 	    menus.showAt(e.getXY());
	},
	
	/**
	 * 重置编辑窗口状态
	 */
	resetEditorWin:function(win){
		win.down('form').getForm().reset();
	},
	
	/**
	 * 获取资源窗口
	 */
	getResourceWin:function(title){
		if(title==null || title==''){
			title='窗口标题未定义';
		}
		var win=Ext.getCmp('resourceWinID');
     	if(!win){
     		win=Ext.widget("resourceWin",{title:title});     //创建组件
     	}else{
     		win.setTitle(title);
     	}
     	return win;
	}
})