<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
	<c:set var="path" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet" href="plugins/bootstrap-3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css"/>
	<script src="plugins/jquery-3.4.1.min.js"></script>
	<script src="plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</head>
	<body>
	<!--导航-->
	<div class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a  class="navbar-brand" href="#">微信公众号管家</a>
				</div>
				<div class="nav navbar-nav">
					<ul>
						<li class="active"><a href="${pageContext.request.contextPath}/dict/index">首页</a></li>
						<li><a href="#">关于</a></li>
						<li><a href="#">登录</a></li>
					</ul>
				</div>
			</div>
	</div>
	<!--轮播图-->
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="images/1.jpg">
				<div class="carousel-caption">
					<h1>轮播1的标题</h1>
					<p>这里是轮播图1的说明</p>
				</div>
			</div>
			<div class="item">
				<img src="images/2.jpg">
				<div class="carousel-caption">
					<h1>轮播2的标题</h1>
					<p>这里是轮播图2的说明</p>
				</div>
			</div>
			<div class="item">
				<img src="images/3.jpg">
				<div class="carousel-caption">
					<h1>轮播3的标题</h1>
					<p>这里是轮播图3的说明</p>
				</div>
			</div>
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>



	<!--产品功能截图-->
	<div class=" case 	container screenshot">
		<div class="row item">
			<div class="col-md-7"><img src="images/1.jpg"></div>
			<div class="col-md-5">
				<h2>截图1</h2>
				<p>截图说明1，截图说明1，截图说明1，截图说明1，截图说明1，</p>
			</div>
		</div>
		<div class="row item">
			<div class="col-md-7">
				<h2>截图2</h2>
				<p>截图说明2，截图说明2，截图说明2，截图说明2，截图说明2，</p>
			</div>
			<div class="col-md-5"><img src="images/2.jpg"></div>
		</div>
		<div class="row item">
			<div class="col-md-7"><img src="images/3.jpg"></div>
			<div class="col-md-5">
				<h2>截图3</h2>
				<p>截图说明3，截图说明3，截图说明3，截图说明3，截图说明3，</p>
			</div>
		</div>
	</div>
	<!--底部-->
	<div class="footer">
		版权所有 2016
		<a href="">四光年科技</a>
	</div>


<%--<p>
    Hello Spring MVC!
</p>
<p>
    服务器时间：<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
</p>
<a href="${pageContext.request.contextPath}/dict/list">访问字段页面</a>--%>
</body>
</html>
