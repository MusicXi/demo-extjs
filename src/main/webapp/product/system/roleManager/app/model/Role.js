Ext.define('RoleManager.model.Role', {
	extend:'Ext.data.Model',
	fields:[
	        {name:'roleId'},	//角色ID
	        {name:'organizationId'},	//机构ID
	        {name:'name'},	//角色名称
	        {name:'role'},	//权限标识
	        {name:'description'},	//描述
	        {name:'available'}	//状态
	]
})