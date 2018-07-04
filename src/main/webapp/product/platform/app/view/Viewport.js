Ext.define('Framework.view.Viewport', {
	extend: 'Ext.container.Viewport',
	//任何Container容器都可以作为一个Viewport的子组件,Viewport作为父容器配置layout,管理他们的大小和位置
	id:'viewportID', //这个id一定要配置,这样很容易获取该组件：Ext.getComp('viewportID');
	layout:'border', //Viewports一般使用border layout布局
	requires: [
	],
	//所有内部布局可以通过Panel添加到Viewport,或者配置items,或者通过添加add方法添加panels
	//移除：Ext.getCmp('viewportID').remove(Ext.getCmp('xxxID'), true);//移除组件并自动销毁
	//添加：Ext.getCmp('viewportID').add([{xtype:'container',id:'xxxID'},{},{}]);
	items:[
		{xtype:'center', region:'center'},
		{xtype:'west', region:'west'},  //这里通常使用TreePanel或者Accordion Layout布局的导航菜单
		{xtype:'north', region: 'north'}//子组件本身就存在自身的布局方式
	],

	listeners:{
		afterrender:function(me){
			refreshPage();
			setInterval('refreshPage()',60000); //指定60秒刷新一次
		}
	}
});

function refreshPage(){
/*	Ext.Ajax.request({
		 url: 'statisticsAlarmByLevel.action',
		 success: function(resp) {
			 var respText = Ext.JSON.decode(resp.responseText);
			 var obj  = respText.result;
			 var strs = new Array();
			 if(obj != null) {
				 strs = obj.split(",");
			 }
			 var one = document.getElementById('oneNum');
			 var two = document.getElementById('twoNum');
			 var three = document.getElementById('threeNum');
			 one.innerHTML = strs[0];
			 two.innerHTML = strs[1];
			 three.innerHTML = strs[2];
		 }
	 });*/
}
