Ext.define('manager.controller.Manager', {
	extend:'Ext.app.Controller',
	views:['List','Edit', 'New', 'Query','ImportWin'],		// 声明该控制层要用到的view
	models:['Manager'], 
	stores:['Manager'],
	refs:[{			// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
		ref:'list',	//获取的方式 ref:userlist -> getUserlist()
		selector:'list' //view组件的List别名 alias
	},
	{	ref:'pagingtoolbar', 
		selector:'list pagingtoolbar'	
	}],
	init:function(){
		this.control({
			'list':{
				itemclick: this.test
			},
			'list':{
				itemdblclick:this.edit
			},
			'list button[action=openQuery]':{
				click:this.openQuery
			},
			'list button[action=new]' :{
				click:this.createManager
			},
			'list button[action=delete]':{
				click:this.deleteManager
			},
			'list button[action=refresh]':{
				click:this.refreshPage
			},
			'list button[action=openImportWin]':{
				click:this.openImportWin
			},
			'list button[action=exportExcel]':{
				click:this.exportExcel
			},
			'list button[action=download]':{
				click:this.downloadTemplate
			},
			'query button[action=query]':{
				click:this.queryManager
			},
			'query button[action=cancel]':{
				click:this.closeWindow
			},
			'query button[action=reset]':{
				click:this.resetQuery
			},
			'new button[action=save]' :{
				click:this.saveManager
			},
			'new button[action=cancel]' :{
				click:this.closeWindow
			},
			'edit button[action=update]' :{
				click:this.updateManager
			},
			'edit button[action=cancel]' :{
				click:this.closeWindow
			},
			'importWin filefield[action=import]':{
				change:this.importExcel
			}
		})
	
	},
	
	test:function(view, record, item, index, e, obj){
		//this.getList().setTitle('test refs');
		//console.log(this.getList().getStore().data.items[index].data);
		console.log(record.data);
	},

	//list dblclick
	edit:function(view, record, item, index, e, obj){
		var win=Ext.create('manager.view.Edit');
		var form=win.down('form');
		form.loadRecord(record);		//传入选中records	
		win.show();
	},
	
	//list button openQuery
	openQuery: function(){
		if(!Ext.getCmp('queryID')){//避免打开多个相同的窗口
			var win=Ext.create('manager.view.Query');
			win.show();			
		}
	},
	
	//list button new
	createManager:function(){
		var win=Ext.create('manager.view.New');     //创建组件
		//var view=Ext.widget('new');			    //获取组件,要先创建组件才可以获取
		//var form1=Ext.getCmp('newformID');		//通过id获取表单
		var form=win.down('form');				    //检索容器的第一层子组件. 匹配选项必须是一个符合Ext.ComponentQuery的选择器
	 
		var model=Ext.create('manager.model.Manager');
		form.loadRecord(model);	//载入一个 Ext.data.Model 到表单中,用于保存表单数据
		
		console.log(model.data);
		win.show();
	},
	
	//list button delete
	deleteManager:function(button){
		//debugger;
		var me=this;
		//获取列表被选中的数据
		//getSelectionModel( ) : Ext.selection.Model
		//getSelection( ) : Ext.data.Model[] 返回一个当前被选择的记录的数组
		var records=button.up('list').getSelectionModel().getSelection();
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
					var store=me.getManagerStore();
					store.remove(records);
					store.sync({
						success:function(){
							Ext.Msg.alert('提示','删除成功！');							
							me.getPagingtoolbar().doRefresh();
						}
					});
				}
			});
			
		}
	},
	
	//list button refresh
	refreshPage:function(){
		var me=this;
		var store=this.getManagerStore();
		store.clearFilter(true);//清空过滤器,将 Record cache 恢复到没有过滤的情况.
		me.getPagingtoolbar().doRefresh();
	},
	
	/**
	 * list button openImportExcel
	 */
	openImportWin:function(){
		if(!Ext.getCmp('importWinID')){//避免打开多个相同的窗口
			var win=Ext.create('manager.view.ImportWin');
			win.show();			
		}
	},
	
	/**
	 * list button[action=exportExcel]
	 */
	exportExcel:function(){
		//Ext.data.Connection的一个单例,用来和你的服务端代码进行交互的. 无法弹出下载！！！！！！！！！！！
		/*Ext.Ajax.request({ 
			url:'../../toManager/download.do',
			method:'GET',
			params: {
		        //id: 1
		    },
		    success:function(){
		    	Ext.Msg.alert('提示','导出成功')
		    }
		});*/
		//导出全部
    	var form = Ext.create('Ext.form.Panel', {
			standardSubmit : true,
			url :'../../toManager/download.do'
		});
    	var params = {};
    	form.submit({
			params : params
		});
	},
	
	/**
	 * list button[action=download]
	 */
	downloadTemplate:function(){
		window.location.href="/myron/product/manager/manager.xlsx";
	},
	
	/**
	 * query button query
	 * @param {} button
	 */
	queryManager:function(button){
		debugger;
		var form=button.up('form');
		var values=form.getValues(); 	////获取form中所有表单域当前值得 快捷函数. 和调用 this.getForm().getValues() 返回的结果是一样的.
		
		if(!form.getForm().isValid()){	//查询条件无效,直接返回
			return;
		}
		
		if(values !=null){			
			var store=this.getManagerStore();
			// 清除过滤器而不更新界面(UI)
			store.clearFilter(true);//清空过滤器,将 Record cache 恢复到没有过滤的情况.
			
			var filters=[];//填充filter
			//枚举对象内置属性的循环
			for(var p in values){
				if(values[p] != null && values[p] !=''){
					filters.push({property:p, value:values[p]})
				}
			}
			store.filter(filters);//根据给定的过滤器集合,进行过滤查询 store须开启remoteFilter:true,
		}
	},
	
	/**
	 * query button reset  查询重置
	 * @param {} button
	 */
	resetQuery: function(button) {
		button.up('form').getForm().reset();//重置查询表单
	},
	
	/**
	 * new button save
	 * @param {} button
	 */
	saveManager:function(button){
		//debugger;
		var win=button.up('window');
		var form=win.down('form');
		
		var record=form.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
		form.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
		//console.log(record.data);			 //测试数据是否更新
	
		var store=this.getManagerStore();	 //var store=Ext.getCmp('listID').getStore();
		store.add(record);					 //添加 Model 实例到 Store
		console.log(store.data);
		store.sync({
			success:function(){
				//Ext.Msg.alert('提示','添加成功！')
				store.load();
			}
		});						 //后台实现数据库同步
		win.close();
		
	},
	
	/**
	 * edit button update
	 * @param {} button
	 */
	updateManager:function(button){
		debugger;
		var win=button.up('window');
		var form=win.down('form');
		
		var record=form.getRecord();		 //返回当前通过 loadRecord 加载的 Ext.data.Model 实例.
		form.getForm().updateRecord(record); //传入的Ext.data.Model对象内保存表单写入的值
		console.log(record.data);			 //测试数据是否更新
		
		var store=this.getManagerStore();	 //var store=Ext.getCmp('listID').getStore();
		store.sync({
			success:function(){
				Ext.Msg.alert('提示','更新成功！')
			}
		});						 //后台实现数据库同步
		win.close();
	},
	
	/**
	 * importWin filefield[action=import]
	 */
	importExcel:function(button){
		var me=this;
		var win=button.up('window');
		var form=button.up('form');
		
		var filePath = form.getForm().getFields().items[0].lastValue;
		var fileType = filePath.substring(filePath.lastIndexOf('.'));
		if (fileType != '.xlsx' && fileType != '.xls') {
			Ext.Msg.alert('提示：', '导入文件格式错误，请重新选择！');
			return;
		}
	
		form.submit({
			//async:true,
			waitMsg:'正在导入数据，请稍候...',
			url:'../../toManager/upload.do',
			success:function(){
				debugger;
				var store=me.getManagerStore();
				store.load();
				win.close();				
			}
		});
		
	},
	
	/**
	 * button cancel
	 * @param {} button
	 */
	closeWindow : function(button) {
		if (button.up('window').close) {
			button.up('window').close();
		}
	}
})
































