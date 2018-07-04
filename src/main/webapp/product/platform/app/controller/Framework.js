Ext.define('Framework.controller.Framework', {
	extend: 'Ext.app.Controller',
	models:['Menu'],
	stores: ['Menu'],
	//views中类Center.js与内部定义的要一致,否则无法加载视图也不会报错
	//引用未定义的类,将出现异常
	views : ['Center', 'North', 'South',  'West', 'Tree' ],


	init: function() {
		this.control({
		'tree':{  //tree 是view中Tree.js 的alias即别名
				drop: this.dropMenu
			}
		});
	},
	onLaunch: function(applition) {
	
	},
	
	
	dropMenu: function(){
		alert("ddfddf");
	}
});