Ext.define('UserManager.view.RoleSelectPanel', {
    extend: 'Ext.container.Container',
    alias : 'widget.roleSelectPanel',
    requires: [
        'Ext.grid.*',
        'Ext.layout.container.HBox'
        //'KitchenSink.model.dd.Simple'

    ],    
    xtype: 'dd-grid-to-grid',
    width: 650,
    height: 300,
    layout: {
        type: 'hbox',
        align: 'stretch',
        padding: 5
    },
    
    myData: [
        { roleId : 'Rec 0', name : '0', description : '0' },
        { roleId : 'Rec 1', name : '1', description : '1' },
        { roleId : 'Rec 2', name : '2', description : '2' }
    ],
    
    initComponent: function(){
        var group1 = this.id + 'group1',
            group2 = this.id + 'group2',
            columns = [{
                text: '角色ID', 
                flex: 1, 
                sortable: true, 
                dataIndex: 'roleId'
            }, {
                text: '角色名称', 
                flex: 2,
                sortable: true, 
                dataIndex: 'name'
            }, {
                text: '描述', 
                flex: 2,
                sortable: true, 
                dataIndex: 'description'
            }];
            
        this.items = [{
            itemId: 'grid1',
            flex: 1,
            xtype: 'grid',
            multiSelect: true,
                viewConfig: {
                plugins: {
                    ptype: 'gridviewdragdrop',
                    dragGroup: group1,
                    dropGroup: group2
                },
                listeners: {
                    drop: function(node, data, dropRec, dropPosition) {
                        var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('name') : ' on empty view';
//                        Ext.example.msg('Drag from right to left', 'Dropped ' + data.records[0].get('name') + dropOn);
                    }
                }
            },
        /*    store: new Ext.data.Store({
                model: RoleManager.model.Role,
                data: this.myData
            }),*/
            //store:Ext.create('RoleManager.store.Role'),
            store:new Ext.data.Store({
                model: RoleManager.model.Role,
                proxy: {
			         type: 'ajax',
			         url: webRoot+'/product/system/userManager/findUnselectedRole.do',
			         reader: {
			             type: 'json',
			             root: 'records'
			         }
			     }
            }),
            columns: columns,
            stripeRows: true,
            title: '可选角色',
            tools: [{
                type: 'refresh',
                tooltip: '重置选择',
                scope: this,
                handler: this.onResetClick
            }],
            margins: '0 5 0 0'
        }, {
            itemId: 'grid2',
            flex: 1,
            xtype: 'grid',
            viewConfig: {
                plugins: {
                    ptype: 'gridviewdragdrop',
                    dragGroup: group2,
                    dropGroup: group1
                },
                listeners: {       
                   drop: function(node, data, dropRec, dropPosition) {   
                        var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('name') : ' on empty view';
	                    // Ext.example.msg('Drag from left to right', 'Dropped ' + data.records[0].get('name') + dropOn);
                    }
                }
            },
            store: new Ext.data.Store({
                model: RoleManager.model.Role,
               /* proxy: {
					type: 'ajax',
					actionMethods:{
						read: "POST"  //修改read提交方式可以解决,提交的中文乱码问题,通过过滤器
					},
					api:{
						read:webRoot+'/product/system/userManager/findRoleByUserId.do',
						create:webRoot+'/product/system/roleManager/create.do',
						//update:webRoot+'/product/system/userManager/update.do',
						destroy:webRoot+'/product/system/roleManager/destroy.do'
					},
					reader:{//用来对服务器端响应数据进行解码，或从客户端读取数据
						type:'json',
						root:'records',
						totalProperty:'total',  //Defaults to: "total" 检索数据集中记录总数的属性名称. 只有在所有的数据集没有一次得到，而是由服务端分页得到时，该属性才需要用。
						successProperty: 'success',//Defaults to: "success" 检索'success'标识的属性名称，该标识属性的值标示指定请求是否成功
					},
					writer:{
						type:'json',
						allowSingle:false //设为false表示确定记录集要被组装成数组，即使发送的记录只有一条
					}
				},*/
                proxy: {
			         type: 'ajax',
			         url: webRoot+'/product/system/userManager/findRoleByUserId.do',
			         reader: {
			             type: 'json',
			             root: 'records'
			         }
			    }
            }),
            columns: columns,
            stripeRows: true,
            title: '已选角色'
        }];

        this.callParent();
    },
    
    onResetClick: function(){
        //refresh source grid
       // this.down('#grid1').getStore().loadData(this.myData);
        this.down('#grid1').getStore().reload();

        //purge destination grid
        //this.down('#grid2').getStore().removeAll();
        this.down('#grid2').getStore().reload();
    }
});