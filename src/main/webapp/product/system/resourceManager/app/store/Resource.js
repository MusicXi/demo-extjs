Ext.define('ResourceManager.store.Resource', {
	extend:'Ext.data.TreeStore',
	model:'ResourceManager.model.Resource',//store的配置model必须要是类完整的名称,否则store对象无法引用到
	autoLoad: true,
/*	constructor: function (config) {
		debugger;
        this.proxy.extraParams.filter = config.filter;
        //this.autoLoad=config.autoLoad || true;
        this.callParent();
    },*/
	initComponent : function() {
		var me=this;
		this.callParent([this]);
	},
	proxy: {
		type: 'ajax',
		//the store will get the content from the .json file
		//url: 'treegrid.json'
		//url: 'getResourceGrid.do',
		api:{
			read:webRoot+'/product/system/resourceManager/getResourceGrid.do',
			create:webRoot+'/product/system/resourceManager/create.do',
			update:webRoot+'/product/system/resourceManager/update.do',
			destroy:webRoot+'/product/system/resourceManager/delete.do'
		},
		reader:{
			type:'json',
			root:'data' //读取data属性
		},
		writer:{
			type:'json',
			allowSingle:false //设为false表示确定记录集要被组装成数组，即使发送的记录只有一条
		}
		/*extraParams: {
                   filter: 2
        }*/
	},
	//folderSort: true
	root: {
		id:1, //节点id值通过通过request node属性上传而不是id属性,根节点无id时node=root
		//text:'root节点名称',//默认为Root;
		expanded: true
  
    }
	
})