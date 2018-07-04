<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>

	<!-- ExtJS 4.2 start -->
	<%-- <link rel="stylesheet" type="text/css" href="${base}/common/js/extjs/resources/css/ext-all.css"/> --%>
	<link rel="stylesheet" type="text/css" href="${base}/common/js/extjs/resources/ext-theme-neptune/ext-theme-neptune-all.css"/>
	<script type="text/javascript" src="${base}/common/js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="${base}/common/js/extjs/ext-lang-zh_CN.js"></script>
	
	<!-- ExtJS 4.2 end -->
	
	<!-- json -->
	<script src="${base}/common/js/json/json2.js"></script>
	
	<!-- Application start -->
	<link rel="stylesheet" type="text/css" href="${base}/common/css/toolbarCss.css"/>
	<script>
		var webRoot="${base}";
	</script>
	<!-- Application end -->
