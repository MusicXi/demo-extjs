Ext.define('RoleManager.store.Organization', {
	extend: 'Ext.data.TreeStore',
	/*root: {
		text:'组织机构',
        expanded: true,
        children: [
            { text: "总经理", leaf: true },
            { text: "财务部", expanded: true, children: [
                { text: "会计组", leaf: true },
                { text: "出纳组", leaf: true}
            ] },
            { text: "信息部", leaf: true }
        ]
    },*/
	//autoLoad:true,//加不加似乎没什么关系
	model:'RoleManager.model.Organization',//store的配置model必须要是类完整的名称,否则store对象无法引用到
	proxy: {
		type: 'ajax',
		url: '../../../system/orgManager/getOrgTree.do',
		reader:{
			type:'json',
			//root:'data' //读取data属性
		}
	},
	root: {
		id:'#', //节点id值通过通过request node属性上传而不是id属性,根节点无id时node=root
		//text:'root节点名称',//默认为Root;
		expanded: true
  
    }
});	