Ext.application({
	name: 'agencymonitor2',
	controllers:['AgencyMonitor'],
	launch: function() {
		Ext.create('Ext.container.Viewport', {
			layout: 'border',
			items:[
				{xtype: 'query', region: 'north'},
				{xtype: 'list',  region: 'center'}			  
			]		
		});
	}

});
