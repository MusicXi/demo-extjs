Ext.define('agencymonitor2.model.AgencyMonitor', {
	extend: 'Ext.data.Model',
	fields: [						
	           {name:'id',type:'string'},
	           {name:'agencyname',type:'string', mapping:"name"},//代理人名称中文
	           {name:'agencydatebegin',type:'date', mapping:"begindate"},//代理开始时间
	           {name:'agencydateend',type:'date', mapping:"enddate"},//代理结束时间
	           {name:'agencystatus',type:'string', mapping:"status"},//是否被执行
	           {name:'happendate',type:'date'}
	           
           
	]
});
