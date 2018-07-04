Ext.define('UserManager.model.Dictionary', {
	extend:'Ext.data.Model',
	fields:[
		{name:'id', type:'string', mapping:'dictionaryItemId'},
		{name:'text', type:'string'},
		{name:'icon'}
	]
})