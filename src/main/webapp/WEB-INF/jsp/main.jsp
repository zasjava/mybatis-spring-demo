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
    <link rel="stylesheet" href="${path}/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${path}/plugins/jquery-3.4.1.min.js"></script>
    <script src="${path}/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="page-header" >
                <h1>四光年科技<small>有价值的、有趣的信息服务</small></h1>
                <p class="text-center" style="text-align: center;">
                    <img src="${path}/images/1.jpg">
                </p>
                <form action="" role="form" class="form-horizontal">
                    <div class="form-group">
                        <label for="">姓名</label>
                        <input  class="form-control" id="truename" type="text" />
                    </div>
                    <div class="form-group">
                        <label for="">称呼</label>
                        <div class="radio">
                            <label>
                                <input type="radio" checked />先生
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" />女士
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="">你最喜欢”公众号管家“的哪个功能</label>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" checked>文章高级排版
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">多公众号切换
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">素材集中管理
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">其他
                            </label>
                        </div>
                    </div>

                    <div>
                        <label for="">你最喜欢”公众号管家“的哪个功能</label>
                        <input type="checkbox" checked>文章高级排版
                        <input type="checkbox">多公众号切换
                        <input type="checkbox">素材集中管理
                        <input type="checkbox">其他
                    </div>
                    <div>
                        <label for="">更多建议</label>
                        <textarea rows="3" cols=""></textarea>
                    </div>
                    <p>请不要超过500个字</p>
                    <div>
                        <button class="btn btn-primary">提交</button>
                    </div>
                </form>

            </div>
        </div>
        <div class="col-md-3">
            <blockquote>
                <p>交给你全栈开发的一招一式，我们不会装作什么都懂，也不会教你花架子，只是能保证你学完后，拥有自己的一套内功心法，能举一反三，轻松写代码</p>
            </blockquote>
            <p style="text-align: center">
                <button class="btn btn-primary">顶</button>
                <button class="btn btn-danger">踩</button>
            </p>
            <h3>相关内容</h3>
            <ul class="list-unstyled">
                <li><a href="">深圳市四光年信息技术有限公司</a></li>
                <li><a href="">深圳市腾讯计算机系统</a></li>
                <li><a href="">北京市百度互联网计算有限公司</a></li>
                <li><a href="">杭州市阿里巴巴集团</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <h2>联系方式</h2>
    <address>
        <strong>深圳市四光年信息技术有限公司.</strong><br> 深圳市南山区深南大道100号<br>
        <abbr title="Phone">联系电话:</abbr> 400-123-123
    </address>
</div>









<%--
<table align="center">
	<tr>
		<th colspan="4">字典管理</th>
	</tr>
	<tr>
		<th>类别名</th>
		<th>字典名</th>
		<th>字典值</th>
		<th>操作[<a href="${path}/dict/goEdit">新增</a>]</th>
	</tr>
		<c:forEach items="${dicts}" var="dict">
			<tr id="dict-${dict.id}">
				<th>${dict.code}</th>
				<th>${dict.name}</th>
				<th>${dict.value}</th>
				<th>
					[<a href="${path}/dict/goEdit?id=${dict.id}">编辑</a>]
					[<a href="javascript:;" onclick="deleteById(${dict.id},'${dict.name}')">删除</a>]
				</th>
			</tr>
		</c:forEach>
</table>--%>






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
