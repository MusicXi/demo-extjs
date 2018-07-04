Ext.define('MetaTagsDimManager.store.MetaTagsDimTreeStore', {
	extend: 'Ext.data.TreeStore',
	autoLoad:true,
	model:MetaTagsDimManager.model.MetaTagsDim,
	proxy:{
		type:'ajax',
		/*url:'http://localhost:8081/springMVC_hibernate4_extjs/',*/
		api:{
			read:webRoot+'/product/system/metaTagsDimManager/loadByJson.do',
			create:'',
			update:'',
			destroy:''
		},
		reader:{
			type:'json',
			root:'data' //读取data属性
		}
	},
	root: {
		id:0, //节点id值通过通过request node属性上传而不是id属性,根节点无id时node=root
		//text:'root节点名称',//默认为Root;
		expanded: true
  
    }
	
})