Ext.define('Framework.view.Tree', {
	extend: 'Ext.tree.Panel',
	alias:'widget.tree',
	id:'modalTree',
	cls:'platform_banner_west',
	layout:'fit',
	width:203,
	maxWidth:203,
	split:true,
	collapsible:true,		//是否可折叠
	store:'Menu',			//为树提供数据
	rootVisible:false,		//定义根节点是否见
	useArrows:true,			//箭头作为展开收起按钮
	viewConfig:{
		plugins:{			//为数组件增加拖放功能
			ptype:'treeviewdragdrop'
		}
	},
	title:'导航树',
	

/*	dockedItems : [ {
		xtype : 'toolbar',
		items : [ {
			text : '全部展开',
			id : 'expandTree'
		}, {
			text : '全部收起',
			id : 'collapseTree'
		} ]
	} ],*/
	initComponent:function(){
		this.callParent([this]);
	},
	listeners:{
		itemclick:function(view, record, item, index, e, obj) {
			 if (record.data.url) {
			 	//var tabs=view.ownerCt.ownerCt.ownerCt.down("center");
			 	var tabs=view.up('west').ownerCt.down('center');
		
			 	var tab = tabs.getComponent(record.data.id); 
			 	if(!tab){
				 	tabs.add({// 用点击树的节点的ID、text新建一个tab
						//xtype:id　　// 将tree设置好的id对应的TabPanel中去，相当与把对应的view填充到TabPanel中
						id:record.data.id,
						title:record.data.text,
						closable: true, 
						html:'<iframe id="myFrame'+record.data.id+'" style="width:100%;height:100%;border:none;"></iframe>'
					}).show();
	                //获取节点的url的值
	                document.getElementById('myFrame'+record.data.id).src = record.data.url;
			 	}else{
			 		tab.show();
			 	}
             }
        },
        
        itemcontextmenu : function(menutree, record, items, index, e) {  
    		//禁用浏览器的右键相应事件
   	 		e.preventDefault();
   	 		e.stopEvent();
   	 		//禁用浏览器的右键相应事件
   	 		var link = record.data.url;
   	 		var menus = Ext.create('Ext.menu.Menu');
 			menus.add({
 				text : "在新窗口中打开",
 				iconCls : 'toolbar-view',
	  	    	handler: function() {
	  	    		window.open(webRoot +"/"+ link);
	  	    	}
 	        });
   	 	    menus.showAt(e.getXY());
    	}
		
	}
	

});