Ext.define('Framework.store.Menu', {
	extend: 'Ext.data.TreeStore',
	autoLoad:true,
	model:Framework.model.Menu,
	proxy:{
		type:'ajax',
		/*url:'http://localhost:8081/springMVC_hibernate4_extjs/',*/
		api:{
			read:'menu/loadByJson.do',
			create:'',
			update:'menu/updateMenu.do',
			destroy:''
		},
		reader:{
			type:'json',
			root:'data' //读取data属性
		}
	},
	root: {
		id:1, //节点id值通过通过request node属性上传而不是id属性,根节点无id时node=root
		//text:'root节点名称',//默认为Root;
		expanded: true
  
    }
	
})