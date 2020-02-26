<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>字典管理</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
	<c:set var="path" value="${pageContext.request.contextPath}"/>
	<script src="${path}/js/jquery/jquery-3.4.1.min.js" ></script>
</head>
<body>
<table align="center">
	<tr>
		<th colspan="4">字典管理</th>
	</tr>
	<tr>
		<th>类别名</th>
		<th>字典名</th>
		<th>字典值</th>
		<th>操作[<a href="${path}/dict/goAdd">新增</a>]</th>
	</tr>
		<c:forEach items="${dicts}" var="dict">
			<tr id="dict-${dict.id}">
				<th>${dict.code}</th>
				<th>${dict.name}</th>
				<th>${dict.value}</th>
				<th>
					[<a href="${pah}/dict/goAdd?id=${dict.id}">编辑</a>]
					[<a href="javascript:;" onclick="deleteById(${dict.id},'${dict.name}')">删除</a>]
				</th>
			</tr>
		</c:forEach>
</table>
</body>
<script>
    function deleteById(id,label){
		debugger;
        var flag = confirm("确定要删除" + label + "吗？");
        if (flag) {
            $.ajax({
                url:'${path}/dict/delete',
                data:{
                    id:id
                },
                dataType:'json',
                type:'POST',
                success:function (data) {
                    debugger;
                    if (data.success) {
                        $("#dict-" + id).remove();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    }
</script>
</html>
