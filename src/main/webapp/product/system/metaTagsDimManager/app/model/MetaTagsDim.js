Ext.define('MetaTagsDimManager.model.MetaTagsDim', {
	extend:'Ext.data.Model',
	idProperty:'id',
	fields:[
	        {name:'id'},//标签ID
	        {name:'text', type:'string', mapping:"name"},//标签名称
	        {name:'type'},//1:分类，2:标签组，3:标签
	        {name:'parentId'},//父标签
	        {name:'arrparentId'},//父标签集合
	        {name:'value'},//标签值
	        {name:'description'},//业务含义
	        {name:'status'},//状态：-1:废止,0:创建,99上线
	        {name:'releaseTime',type:'date',convert:function(value){//上线时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'trashTime',type:'date',convert:function(value){//废止时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'demand'},//需求方
	        {name:'isValMulti'},//标签多值  ，1是，2否
	        {name:'isValEnum'},//标签值枚举  ，1是，2否
	        {name:'valType'},//字段类型，DATE_INT支持yyyymmdd,yyyymm等格式
	        {name:'valWeightType'},//标签权重  1:0/1  2:(0,1]
	        {name:'valWeightNorm'},//标签权重归一化  ，1是，2否
	        {name:'freq'},//更新频率  1:按天,2:按周,3:按月 4:实时
	        {name:'lifecycle'},//生命周期即有效期，单位为天   0表示长期有效
	        {name:'ruleDesc'},//标签规则描述
	        {name:'domain'},//标签域，对应列簇
	        {name:'field'},//标签列，对应列
	        {name:'dataFlow'},//数据流
	        {name:'dataNode'},//数据节点
	        {name:'committer'},//开发负责人
	        {name:'isIndex'},//是否建索引，指是否保存到solr中建立索引，1是，2否
	        {name:'createTime',type:'date',convert:function(value){//创建时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'createBy'},//创建人
	        {name:'modifiedTime',type:'date',convert:function(value){//修改时间
	        	if(value!=""){
		        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}	        	
	        	}
	        },
	        {name:'modifiedBy'}//修改人
	]
})