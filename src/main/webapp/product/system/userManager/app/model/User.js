Ext.define('UserManager.model.User', {
	extend:'Ext.data.Model',
	fields:[{name:'id',mapping:'userId'},
	        {name:'name', type:'string', defaultValue:'Unknown Name'}, 
	        'username', 
	        'sex', 
	        'email', 
	        'phone', 
	        'organizationId',
	        'department.name', 
	        'company.name']
})