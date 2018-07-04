Ext.define("UserManager.view.West",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.west',
	cls : 'platform_banner_west',
	//layout : 'accordion',
	width : 203,
	maxWidth : 203,
	split : true,
	collapsible : true,
	title : '组织机构',

	initComponent : function() {
		this.callParent([this]);
	},

	/*items : [{
		title : '折叠面板二',
		html : 'hello2'
	}, {
		title : '折叠面板三',
		html : 'hello3'
	}]*/
	items:{
		xtype:'treepanel',
		id:'orgTree',
		name:'orgTreePanel',
		rootVisible:false,		//定义根节点是否见
		//expanded: true,
		border: false,
		store:'Organization',
		/*listeners:{
			itemclick:function(view, record, item, index, e, obj) {
				debugger;
				var orgId=record.data.id;
				//store.load({ params: { Id: 123} });
				Ext.Msg.alert("提示","hahh");
	      
	        }
		        
		}*/
	},
	/*tools: [{
        type: 'expand',
        handler: function () {
            Ext.getCmp("orgTree").expandAll();
        }
    }, {
        type: 'collapse',
        handler: function () {
            Ext.getCmp("orgTree").collapseAll();
        }
    }],*/
});
