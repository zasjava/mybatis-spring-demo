<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>添加字典</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
	<c:set var="path" value="${pageContext.request.contextPath}"/>
</head>
<body>
<div style="width:100%;text-align:center">
	<form action="${path}/dict/doAdd" method="post" >
		<input type="hidden" name="id" value="${model.id}">
		<table>
			<c:if test="${msg!=null}">
				<tr><th colspan="2" style="color:red;max-width: 400px;">${msg}</th></tr>
			</c:if>
			<tr>
				<th>类别名</th>
				<td><input type="text" name="code" value="${model.code}"></td>
			</tr>
			<tr>
				<th>字典名</th>
				<td><input type="text" name="name" value="${model.name}"></td>
			</tr>
			<tr>
				<th>字典值</th>
				<td><input type="text" name="value" value="${model.value}"></td>
			</tr>

			<tr>
				<th colspan="2">
					<input type="submit" value="保存">
					<input type="button" onclick="backToList()" value="取消">
				</th>
			</tr>
		</table>
	</form>
</div>

</body>
<script>
	function backToList(){
	    location.href="${path}/dict/list";
	}
</script>
</html>
