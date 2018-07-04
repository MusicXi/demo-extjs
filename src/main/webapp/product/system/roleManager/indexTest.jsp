<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Role管理</title>
		<%@ include file="/common/common.jsp"%>
		<script type="text/javascript">
		Ext.Loader.setConfig({
			enabled : true,
			disableCaching:true,
			paths : {
				'ResourceManager' : webRoot+'/product/system/resourceManager/app',
				'ZTEsoft' : webRoot+'/common/js/extjs/public',
				'Ext.ux':webRoot+'/common/js/extjs/examples/ux',
				//'workflow':ctx+'/workflow'
			}
		});
		
		Ext.onReady(function () {
            //型录树Store
            var treeStore = Ext.create('Ext.data.TreeStore', {
                proxy: {
                    type: 'ajax',
                    url: webRoot+'/product/system/resourceManager/getResourceGrid.do',
                    reader:{
    					type:'json',
    					root:'data' //读取data属性
    				}
                },
                fields: [{name:'text',mapping:'name'}, 'id', 'leaf' ],
            });
            //型录树
            var CatalogTtree = Ext.create('Ext.tree.Panel', {
                store: treeStore,
                border: false,  //边框
                renderTo: Ext.getBody(),
                enableDD: true,
                rootVisible: false,  //隐藏根节点
                useArrows:true, //树节点使用箭头
                containerScroll: true,
                collapsible: false,
                autoScroll: false,                
                //singleExpand:true   //展示单个子节点，其它的子节点合并。
            });         
           CatalogTtree.expandAll(); //展开所有节点
           //CatalogTtree.collapseAll(); //关闭所有节点
           
            
               
         });
		</script>
	</head>
	<body>
	
	</body>
</html>