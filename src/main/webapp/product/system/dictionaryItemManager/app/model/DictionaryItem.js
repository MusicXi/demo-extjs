Ext.define('DictionaryItemManager.model.DictionaryItem', {
	extend:'Ext.data.Model',
	fields:[
	        {name:'dictionaryItemId'},	//字典项ID
	        {name:'dictionaryId'},	//字典类型ID
	        {name:'value'},	//字典值
	        {name:'text'},	//显示名称
	        {name:'parentId'},	//父级ID
	        {name:'grade'},	//层级
	        {name:'sort'}	//排序
	]
})