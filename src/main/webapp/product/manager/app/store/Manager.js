Ext.define('manager.store.Manager', {
	extend:'Ext.data.Store',
	//Store类封装了一个客户端缓存,用于存储Model对象(由Controller实例化Model对象)
	//store的配置model必须要是类完整的名称,否则store对象无法引用到
	model:'manager.model.Manager',
	autoLoad:true,
	remoteFilter:true,//服务器端过滤查询 如何接受提交filter参数暂未实现
	pageSize:20,	//设置分页大小
/*	data:{'records':[
        { 'name': 'Lisa',  "email":"lisa@simpsons.com",  "phone":"555-111-1224"  },
        { 'name': 'Bart',  "email":"bart@simpsons.com",  "phone":"555-222-1234" },
        { 'name': 'Homer', "email":"home@simpsons.com",  "phone":"555-222-1244"  },
        { 'name': 'Marge', "email":"marge@simpsons.com", "phone":"555-222-1254"  }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'records'
        }
    }*/
	proxy:{
		type:'ajax',
		actionMethods:{
			read: "POST"  //修改read提交方式可以解决,提交的中文乱码问题,通过过滤器
		},
		api:{
			read:'../../toManager/read.do',
			create:'../../toManager/create.do',
			update:'../../toManager/update.do',
			destroy:'../../toManager/destroy.do'
		},
		reader:{
			type:'json',
			root:'records',
			totalProperty:'total'  //Defaults to: "total"检索数据集中记录总数的属性名称. 只有在所有的数据集没有一次得到，而是由服务端分页得到时，该属性才需要用。
		},
		writer:{
			type:'json',
			allowSingle:false //设为false表示确定记录集要被组装成数组，即使发送的记录只有一条
		},
		
		/**
		 * 将Ext.util.Filter对象列表数组编码为一个字符串，在所请求的url中传递并发送。 默认情况下，该方法只是对filter参数数据进行JSON-编码转换。
		 * @param {} filters
		 * @return {}
		 */
		//自定义过滤器参数格式
		encodeFilters:function(filters){
			var length=filters.length,
			filtersStrs=[],
			filter,i;
			for(i=0; i<length; i++){
				filter=filters[i];
				filtersStrs.push('"'+filter.property+'":"'+filter.value+'"');
			}
			filtersStrs.join(",")
			filtersStrs='{'+filtersStrs+'}'
			return filtersStrs;
		}
	}
})