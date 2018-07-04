Ext.application({
	name:'Framework', //创建一个叫Framework的全局变量-你所有的应用类（例如Models、View和Controllers）将属于单独的命名空间namespace
	controllers: ['Framework'],
	appFolder:'product/platform/app',
	autoCreateViewport: true, //自动将Viewport渲染到页面document body区域,一个页面中只能创建一个Viewport		
	launch: function() {
		Framework.app=this;
	}
});

		