<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Mybatis分页插件 - 测试页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%--    <script src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/> --%>
    <style type="text/css">
        .pageDetail {
            display: none;
        }

        .show {
            display: table-row;
        }
    </style>
    <script>
/*         $(function () {
            $('#list').click(function () {
                $('.pageDetail').toggleClass('show');
            });
        }); */

    </script>
</head>
<body>
<div class="wrapper">
    <div class="middle">
        <h1 style="padding: 50px 0 20px;">国家(地区)列表</h1>

        
        <table class="gridtable" style="width:100%;" border=1>
            <thead>
            <tr>
                <th colspan="4">查询结果 - [<a href="${request.contextPath}/countries/add">新增国家(地区)</a>]</th>
            </tr>
            <tr>
                <th>日志主键</th>
                <th>日志类型</th>
                <th>标题</th>
                <th>请求地址</th> 
            </tr>
            </thead>
            <tbody>
            <c:forEach var="goods" items="${pageInfo.data}"> 
				<tr>
				<td>${goods.logId}</td>
				<td>${goods.type}</td>
				<td>${goods.title}</td>
				<td>${goods.remoteAddr}</td>
				</tr>
			</c:forEach> 
            
              <%--   <#list pageInfo.list as country>
                <tr>
                    <td>${country.id}</td>
                    <td>${country.countryname}</td>
                    <td>${country.countrycode}</td>
                    <td style="text-align:center;">[<a
                            href="${request.contextPath}/countries/view/${country.id}">修改</a>] -
                        [<a href="${request.contextPath}/countries/delete/${country.id}">删除</a>]
                    </td>
                </tr>
                </#list> --%>
            </tbody>
        </table>
     <table class="gridtable" style="width:100%;text-align: center;">
			<tr>
			<c:if test="${pageInfo.hasPreviousPage}">
			    <td>
                    <a href="${request.contextPath}/countries?page=1&rows=${pageInfo.length}">首页</a>
                </td>
                <td>
                    <a href="${request.contextPath}/countries?page=${pageInfo.prePage}&rows=${pageInfo.length">前一页</a>
                </td>
			</c:if>
			</tr>
           <%--  <tr>
                <#if pageInfo.hasPreviousPage>
                    <td>
                        <a href="${request.contextPath}/countries?page=1&rows=${pageInfo.pageSize}&countryname=${queryParam.countryname}&countrycode=${queryParam.countrycode}">首页</a>
                    </td>
                    <td>
                        <a href="${request.contextPath}/countries?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}&countryname=${queryParam.countryname}&countrycode=${queryParam.countrycode}">前一页</a>
                    </td>
                </#if>
                <#list pageInfo.navigatepageNums as nav>
                    <#if nav == pageInfo.pageNum>
                        <td style="font-weight: bold;">${nav}</td>
                    </#if>
                    <#if nav != pageInfo.pageNum>
                        <td>
                            <a href="${request.contextPath}/countries?page=${nav}&rows=${pageInfo.pageSize}&countryname=<#if queryParam.countryname??>${queryParam.countryname}</#if>&countrycode=<#if queryParam.countrycode??>${queryParam.countrycode}</#if>">${nav}</a>
                        </td>
                    </#if>
                </#list>
                <#if pageInfo.hasNextPage>
                    <td>
                        <a href="${request.contextPath}/countries?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}&countryname=<#if queryParam.countryname??>${queryParam.countryname}</#if>&countrycode=<#if queryParam.countrycode??>${queryParam.countrycode}</#if>">下一页</a>
                    </td>
                    <td>
                        <a href="${request.contextPath}/countries?page=${pageInfo.pages}&rows=${pageInfo.pageSize}&countryname=<#if queryParam.countryname??>${queryParam.countryname}</#if>&countrycode=<#if queryParam.countrycode??>${queryParam.countrycode}</#if>">尾页</a>
                    </td>
                </#if>
            </tr> --%>
        </table> 

    </div>
    <div class="push"></div>
</div>
</body>
</html>