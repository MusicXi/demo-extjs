Ext.define('Framework.view.North', {
	extend:'Ext.panel.Panel',
	/*requires: ['Mainmenu'],*/
	region:'north',
	layout:'border',
	height:70,
	maxHeight:70,
	collapsible:true,
	split:true,
	baseCls:'platform_banner',
	alias:'widget.north',
	layout:'fit',
	
	/*activeMenuId:'',*/
	/*items:[{
		   xtype:'mainmenu',
	   }
	        
	]*/
	html:"<a href='logout.do' title='安全退出系统'>退出</a>" 
		+"<marquee id='marqueeMsg' scrollAmount=2 height='20' width=280 style='border:0px solid;color:green'>欢迎使用Extjs测试功能系统</marquee>"

	
});




