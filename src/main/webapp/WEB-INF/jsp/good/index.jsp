<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>宜立方商城后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/e3.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script type="text/javascript" src="../../js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <style type="text/css">
        .content {
            padding: 10px 10px 10px 10px;
        }
    </style>

</head>
<body class="easyui-layout">
<!-- 头部标题 -->
<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3">
    <span class="northTitle">宜立方商城后台管理系统</span>
    <span class="loginInfo">登录用户：${activeUser.usercode}&nbsp;&nbsp;姓名：${activeUser.usercode}&nbsp;&nbsp;角色：超级管理员</span>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    <ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
        <c:if test="${activeUser.menus!=null}">
        <c:forEach items="${activeUser.menus}" var="menusItem">
        <li>
            <span>${menusItem.name}</span>
            <ul>
                <c:if test="${activeUser.permissions!=null}">
                    <c:forEach items="${activeUser.permissions}" var="permissionItem">
                        <c:if test="${menusItem.id==permissionItem.parentid}">
                            <li data-options="attributes:{'url':'${permissionItem.url}'}">${permissionItem.name}</li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </ul>
        </li>
        </c:forEach>
        </c:if>
</div>
<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs">
        <div title="首页" style="padding:20px;">

        </div>
    </div>
</div>
<!-- 页脚信息 -->
<div data-options="region:'south',border:false"
     style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
    <span id="sysVersion">系统版本：V1.0</span>
    <span id="nowTime"></span>
</div>
<script type="text/javascript">
    $(function () {
        $('#menu').tree({
            onClick: function (node) {
                if ($('#menu').tree("isLeaf", node.target)) {
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab", node.text);
                    if (tab) {
                        tabs.tabs("select", node.text);
                    } else {
                        tabs.tabs('add', {
                            title: node.text,
                            href: node.attributes.url,
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });
    });
    setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
</script>
</body>
</html>