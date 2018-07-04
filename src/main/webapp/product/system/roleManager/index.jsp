<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Role管理</title>
		<%@ include file="/common/common.jsp"%>
		<script type="text/javascript" src="Main.js"></script>
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
		</script>
	</head>
	<body>
	
	</body>
</html>