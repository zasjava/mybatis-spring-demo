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
    <link rel="stylesheet" href="${path}/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/plugins/bootstrap-table-1.17.1/bootstrap-table.min.css"/>
    <script src="${path}/plugins/jquery-3.4.1.min.js"></script>
    <script src="${path}/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${path}/plugins/bootstrap-table-1.17.1/bootstrap-table.min.js"></script>
    <script src="${path}/plugins/bootstrap-table-1.17.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${path}/plugins/layui-v2.5.6/lay/modules/layer.js"></script>
</head>