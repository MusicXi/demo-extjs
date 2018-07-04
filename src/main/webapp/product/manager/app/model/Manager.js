Ext.define('manager.model.Manager', {
	extend:'Ext.data.Model',
	fields:['id','name', 'email', 'phone', 'salary', {name:'inDutyDate',type:'date'}]
})