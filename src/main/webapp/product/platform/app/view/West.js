Ext.define('Framework.view.West', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.west',
			cls : 'platform_banner_west',
			layout : 'accordion',
			width : 203,
			maxWidth : 203,
			split : true,
			collapsible : true,
			title : '功能模块',

			initComponent : function() {
				this.callParent([this]);
			},

			items : [{
				xtype :'tree',
				title : '系统管理'

				}, {
				title : '报表分析',
				html : '未开发'
			}, {
				title : '数据挖掘',
				html : '未开发'
			},{
				title : '测试模块',
				html : '未开发'
			}]

		});