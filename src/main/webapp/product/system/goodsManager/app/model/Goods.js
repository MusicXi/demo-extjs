Ext.define('GoodsManager.model.Goods', {
	extend:'Ext.data.Model',
	idProperty:'goodsId',
	fields:[
	        {name:'goodsId'},//商品ID
	        {name:'name'},//商品名称
	        {name:'saleStartDate',type:'date',convert:function(value){//销售开始时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'saleEndDate',type:'date',convert:function(value){//销售截止时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'createTime',type:'date',convert:function(value){//创建时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'createBy'},//创建人
	        {name:'status'},//状态
	        {name:'comment'},//商品说明
	        {name:'data'},//流量
	        {name:'flowType'},//流量类型
	        {name:'number'},//商品数量
	        {name:'effectiveMonth'},//有效月
	        {name:'price'},//单价
	        {name:'updateTime',type:'date',convert:function(value){//修改时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'updateBy'}//修改人
	]
})