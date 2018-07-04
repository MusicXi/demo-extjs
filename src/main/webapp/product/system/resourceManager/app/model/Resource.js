Ext.define('ResourceManager.model.Resource', {
	extend:'Ext.data.Model',
	fields:[
		{name:'id'},
		{name:'name'},
		{name:'url'},
		{name:'icon'},
		{name:'type'},
		{name:'permission'},
		{name:'parentId'},
		{name:'checked'},
		{name:'expanded'},
		{name:'leaf'},
		{name:'text', type:'string', mapping:"name"}
	]
})