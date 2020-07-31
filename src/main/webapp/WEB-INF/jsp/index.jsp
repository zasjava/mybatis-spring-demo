<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
	<title>index</title>
</head>
<body>
	<p>
		Hello Spring MVC!
	</p>
	<p>
		服务器时间：<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
	</p>
	<a href="${pageContext.request.contextPath}/dict/list">访问字段页面</a>
</body>
</html>
