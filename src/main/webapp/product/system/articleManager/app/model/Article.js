Ext.define('ArticleManager.model.Article', {
	extend:'Ext.data.Model',
	fields:[
	        {name:'articleId'},	//编号
	        {name:'categoryId'},	//栏目编号
	        {name:'title'},	//标题
	        {name:'link'},	//文章链接
	        {name:'color'},	//标题颜色
	        {name:'image'},	//文章图片
	        {name:'keywords'},	//关键字
	        {name:'description'},	//摘要
	        {name:'weight'},	//权重
	        {name:'weightDate',type:'date',convert:function(value){//权重期限
	        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}
	        },
	        {name:'hits'},	//点击数
	        {name:'posid'},	//推荐位
	        {name:'createBy'},	//创建者
	        {name:'createDate',type:'date',convert:function(value){//创建时间
	        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}
	        },
	        {name:'updateBy'},	//更新者
	        {name:'updateDate',type:'date',convert:function(value){//更新时间
	        	return  Ext.Date.format(new Date(value),"Y-m-d H:i:s")}
	        },
	        {name:'remarks'},	//备注信息
	        {name:'delFlag'},	//删除标记
	        {name:'content'}	//文章内容
	]
})