<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<%@ include file="/common/common.jsp"%>
		<%-- <script type="text/javascript" src="${base}/common/js/extjs/permission/permission.js"></script> --%>
		<script type="text/javascript" src="Main.js"></script>
		<script type="text/javascript">
		Ext.Loader.setConfig({
			enabled : true,
			disableCaching:true,
			paths : {
				'RoleManager' : webRoot+'/product/system/roleManager/app',
				'ZTEsoft' : webRoot+'/common/js/extjs/public',
				'Ext.ux':webRoot+'/common/js/extjs/examples/ux',
				//'workflow':ctx+'/workflow'
			}
		});
		</script>
	</head>
	<script>
		var userOperation={
			read:false,
			create:false,
			copy:false,
			update:false,
			remove :false,
			refresh:false,
			setRole:false,
			exportExcel:false,
			importExcel:false,
			download:false
		};
	
		
		<shiro:hasPermission name="user:read">
			userOperation.read=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:create">
			userOperation.create=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:copy">
			userOperation.copy=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:update">
			userOperation.update=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:delete">
			userOperation.remove=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:refresh">
			userOperation.refresh=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:setRole">
			userOperation.setRole=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:exportExcel">
			userOperation.exportExcel=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:importExcel">
			userOperation.importExcel=true;
		</shiro:hasPermission>	
		<shiro:hasPermission name="user:download">
			userOperation.download=true;
		</shiro:hasPermission>	
	</script>
		
</html>