Ext.define('RoleManager.model.Organization', {
	extend:'Ext.data.Model',
	fields:[
		{name:'id', type:'string'},
		{name:'text', type:'string', mapping:"name"},
		{name:'icon'}
	]
})