Ext.define('agencymonitor2.store.AgencyMonitor', {
	extend: 'Ext.data.Store',
	model:'agencymonitor2.model.AgencyMonitor',
	autoLoad:false,//每当对一条Record记录完成修改后, 都将对Store与Proxy进行同步
	pageSize:20,
	remoteSort:true,
	remoteFilter:true,
	filters: [
	      	 {
	              property: 'aiStarttime_from',
	              value: Ext.Date.format(new Date(), 'Y-m-d')
	          },{
	              property: 'aiStarttime_to',
	              value: Ext.Date.format(new Date(), 'Y-m-d')
	          },{
	              property: 'depetCode',
	              value: '全部'
	          }]
	,
	proxy: {
		type:'ajax',
		api: {
			/*定义CRUD API*/
			//store有四种操作,分别为增(create),删(destroy), 改(update), 查(read)   
            //这里分别对应服务器端的四个方法   
            //其中SERVER为服务器端对象的命名空间   
            //***Action为服务器端action类的springBean名称   
            //create/read/update/destroy为方法名
			read: '../../toAgency/hello.do',
			create: '../../toAgency/test.do'
			 /* read	:	SERVER['eism.companalysis.agencymonitor.AgencyMonitorAction'].read*/
		/*	  create:	SERVER['eism.companalysis.agencymonitor.AgencyMonitorAction'].create*/
		},
		reader: {
			type:'json',
			root:'records'
		}
	}
});
