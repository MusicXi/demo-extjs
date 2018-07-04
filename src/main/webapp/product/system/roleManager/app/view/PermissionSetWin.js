Ext.define('RoleManager.view.PermissionSetWin',{
	extend:'Ext.window.Window',
	alias:'widget.permissionSetWin',
	id:'permissionSetWinID',
	closeAction:'hide',
	modal:true,
	//width:600, 	//不定义大小Window将会自适应
	//height:300,
	
	title:'权限资源分配',
	initComponent : function() {
		var me=this;
		this.callParent([this]);
	},
/*	items:[{
		xtype:'treepanel',
		autoScroll:true,
		width:400, 	//不定义大小Window将会自适应
		height:200,
		//id:'orgTree',
		//expanded: true,
		rootVisible:false,		//定义根节点是否见
		border: false,
		store:Ext.create('ResourceManager.store.Resource'),
		listeners:{
			//itemclick: this.itemclickTree,
			beforeitemclick :function(view, record, item, index, e, eOpts) {
				var el = e.getTarget("input");
				if(el){//点击复选框,勾选当前节点及所有子节点
					record = view.getRecord(item);
					var checked = !record.get('checked');
					checkChild(record, checked);					
				}
		
			}
		}
	}],*/
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
});

function checkChild(record, checked) {
	record.set('checked', checked);
	var childNodes=record.childNodes;
	if(childNodes.length !=0){
		record.eachChild(function(child) {
			checkChild(child,checked);
		})
	}
}