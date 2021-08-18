<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   
<%@ include file="/WEB-INF/views/include/style.jsp" %>
<%@ include file="/WEB-INF/views/include/script.jsp" %>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<style>
	.content {padding-left:250px;}
</style>
<script>
<%-- tiles 타지 않는 페이지로 이동 --%>
function moveContentPage(url) {
	$('.main-content').load('<%=request.getContextPath()%>' + url);
}
</script>
</head>
<body>
	<div id="app">
		<div class="main-wrapper-1">
			
			<tiles:insertAttribute name="header" />
			
			<tiles:insertAttribute name="left" />
			
			<div class="main-content" style="min-height: 646px;"> 
			<tiles:insertAttribute name="content" />
			</div>
			
			<div class="main-footer">
			<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>
</body>
</html>