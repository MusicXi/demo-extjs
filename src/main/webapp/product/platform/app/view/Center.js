Ext.define('Framework.view.Center', {
	extend: 'Ext.tab.Panel',
	alias:'widget.center',
	layout:'fit',		//子元素自动填满父容器,对子元素这只宽度是无效的
	activeTab:0, //显示默认页签,tab页签从左到有分别为0,1,2...
	//tpl:'<iframe id="waitPanel" width="100%" height="100%" style="border: 0"></iframe><iframe src="{url}" id="myFrame" frameborder="0" width="100%" height="100%" onLoad="top.hiddenPanel()"></iframe>',


	items:[{
			title:'主页'
			//html:'<iframe id="myFrame" style="width:100%;height:100%;border:none;"></iframe>'
			//closable: true
		},{
			title:'第2页',
			xtype:'form',
			width:300,
			closable: true,
			items:[{
				xtype:'textfield',		//字段类型 'textfield'相当于Ext.form.TextField
				fieldLabel:'Title',
				name:'title',
				emptyText:'请输入电影名称',
				allowBlank:false		//必输项

			},{
				xtype:'textfield',
				fieldLabel:'Director',
				name:'director',
				emptyText:'如:John Jack',
				//vtype:'alpha'			//校验输入项，必须为字符
				vtype:'name'
			},{
				xtype:'datefield',
				fieldLabel:'Released',
				name:'released',
				disabledDays:[0, 6]		//非可选日期
			},{
				xtype:'timefield',
				fieldLabel:'timefield',
				name:'timefieldtest'
			},{
				xtype:'numberfield',
				fieldLabel:'numberfield',
				name:'numberfieldtest'
			},{
				xtype:'combo',
				name:'combotest',
				fieldLabel:'Choose State',
				//store:states,
				queryMode:'local',		//描述下拉数据来源
				displayField:'name',	//下拉显示
				valueField:'abbr',
				listeners:{
					select:function(f, r, i){
						
					}
				}
			},{
				xtype:'radio',
				fieldLabel:'Film In',
				name:'filmed_in',
				boxLabel:'Color'
			},{
				xtype:'radio',
				hideLabel:true,
				labelSeparator:'',
				name:'filmed_in',
				boxLabel:'Black&White'
			},{
				xtype:'checkbox',
				fieldLabel:'Bad Movie',
				name:'bad_movie'
			},
			{
		    	   xtype: 'filefield',
		    	   name : 'excelFile',
		    	   buttonText : '导入Excel',
		    	   action : 'upload'
			},
			{
				//xtype:'textarea', 
				xtype:'htmleditor', //需要初始化	Ext.tip.QuickTipManager.init();
				name:'decription',
				hideLabel:true,
				labelSeparator:'',
				height:100,
				anchor:'100%'
			}],
			html:'<a>第二页啦</a>'
		}]

});