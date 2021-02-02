<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
    <jsp:include page="main.jsp"/>
</head>
<body>
<!--导航-->
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">微信公众号管家</a>
        </div>
        <div class="nav navbar-nav">
            <ul>
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">关于</a></li>
                <li><a href="#">登录</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-lg-10">
            <jsp:include page="./dict/dict.jsp"/>
        </div>
    </div>
</div>
<!--底部-->
<div class="footer">
    版权所有 2016
    <a href="">四光年科技</a>
</div>
</body>
</html>
