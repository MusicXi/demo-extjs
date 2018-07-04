Ext.define('LogManager.model.Log', {
	extend:'Ext.data.Model',
	fields:[
	        {name:'logId'},	//日志主键
	        {name:'type'},	//日志类型
	        {name:'title'},	//日志标题
	        {name:'remoteAddr'},	//请求地址
	        {name:'requestUri'},	//URI
	        {name:'method'},	//请求方式
	        {name:'params'},	//提交参数
	        {name:'exception'},	//异常
	        {name:'operateDate',type:'date',convert:function(value){//开始时间
	        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}
	        },
	        {name:'timeout'},	//结束时间
	        {name:'userId'}	//用户ID
	]
})