Ext.application({
	name:'manager',
	controllers:['Manager'],
	launch:function(){
		Ext.create('Ext.container.Viewport', {
			layout:'border',
			id:'managerID',
			items:[
				/*{xtype:'query', region:'north'},*/
				{xtype:'list', region:'center'}
			]
		});
	}
})