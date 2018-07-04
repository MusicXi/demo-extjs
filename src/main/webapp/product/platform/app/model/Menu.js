Ext.define('Framework.model.Menu', {
	extend: 'Ext.data.Model',
	
	fields:[
		{name:'id', type:'string'},
		{name:'text', type:'string', mapping:"name"},
		{name:'url'},
		{name:'icon'},
		{name:'app'},
		{name:'data'}
	]
});