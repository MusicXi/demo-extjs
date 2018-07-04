Ext.define('agencymonitor2.view.Query', {
			extend : 'Ext.form.Panel',
			alias : 'widget.query',
			collapsible : true,
			frame : true,
			split : true,
			id : 'queryForm',
			title : '查询面板',
			buttonAlign : 'center',
			height : 125,
			fieldDefaults : {
				labelAlign : 'right',
				labelWidth : 90
			},
			items : [{
					xtype : 'container',
					layout : 'column',
					items : [{
						xtype : 'fieldcontainer',
						layout : 'column',
						columnWidth : 1 / 3,
						fieldLabel : '代理结束时间',
						items : [{
							xtype : 'datefield',
							name : 'aiStarttime_from',
							id : 'aiStarttime_from',
							format : 'Y-m-d',
							value : new Date(),
							maxValue : new Date(),
							editable : false,
							columnWidth : 0.5,
							emptyText : '代理时间',
							action : 'aiStarttime_from'

						}, {
							xtype : 'datefield',
							name : 'aiStarttime_to',
							id : 'aiStarttime_to',
							format : 'Y-m-d',
							value : new Date(),
							editable : false,
							minValue : new Date(),
							maxValue : new Date(),
							columnWidth : 0.5,
							emptyText : '代理时间',
							action : 'aiStarttime_to'
						}]
				}, {
						xtype : 'textfield',
						fieldLabel : '代理人姓名',
						name : 'agencyname',
						columnWidth : 1 / 3
				
				},  {
						xtype : 'combo',
						fieldLabel : '是否被执行',
						editable : false,
						emptyText : '---请选择---',
						name : 'agencystatus',
						displayField : 'name',
						valueField : 'value',
						flex : 1,
						queryMode : 'local',
						store : new Ext.data.Store({
								fields : ['name', 'value'],
								data : [{
								name : '---请选择---',
								value : ''
							}, {
								name : '是',
								value : '1'
							}, {
								name : '否',
								value : '-1'
							}]
						}),
						columnWidth : 1 / 3
				}
					]
			}],
			buttons : [{
				text : '查询',
				/*icon : '../../framework/image/icon_view.png',*/
				action : 'query'
			}, {
				text : '重置',
				/*icon : '../../framework/image/reload.png',*/
				action : 'reset'
			}]
		});
