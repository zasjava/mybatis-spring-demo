<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>商城后台登录</title>
    <!--设置过期时间设置0为直接过期并清除缓存-->
    <meta http-equiv="Expires" content="0">
    <!--设置不缓存页面-->
    <meta http-equiv="Pragma" content="no-cache">
    <!--设置不修改消息存储-->
    <meta http-equiv="Cache-control" content="no-cache">
    <!--同上-->
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../../js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../images/user/styles/style.css">
    <link rel="stylesheet" type="text/css" href="../../images/user/styles/login.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/icon.css">

    <style type="text/css">

        .btnalink {
            cursor: hand;
            display: block;
            width: 80px;
            height: 29px;
            float: left;
            margin: 12px 28px 12px auto;
            line-height: 30px;
            background: url('../../images/user/images/login/btnbg.jpg') no-repeat;
            font-size: 14px;
            color: #fff;
            font-weight: bold;
            text-decoration: none;
        }
    </style>

    <script type="text/javascript">
        //登录提示方法
        function loginsubmit() {
            $("#loginform").submit();
        }
    </script>
</head>
<body style="background: #f6fdff url(../../images/user/images/login/bg1.jpg) repeat-x;">


<form id="loginform" name="loginform" action="/user/login" method="post">

    <div class="logincon">
        <div class="title">
            <img alt="" src="../../images/user/images/login/logo.png">
        </div>
        <div class="cen_con">
            <img alt="" src="../../images/user/images/login/bg2.png">
        </div>
        <div class="tab_con">
            <input type="password" style="display:none;"/>
            <table class="tab" border="0" cellSpacing="6" cellPadding="8">

                <tbody>
                <tr>
                    <td>用户名：</td>
                    <td colSpan="2">
                        <input type="text" id="usercode" name="usercode" style="width: 130px"/>
                    </td>
                </tr>
                <tr>
                    <td>密 码：</td>
                    <td><input type="password" id="pwd" name="password" style="width: 130px"/></td>
                </tr>
                <tr>
                    <td>验证码：</td>
                    <td><input id="randomcode" name="randomcode" size="8"/>
                        <img id="randomcode_img" src="validatecode" alt=""
                             width="56" height="20" align='absMiddle'
                             onclick="this.src='validatecode?d='+Math.random();"/>
                        <a href="" onclick="this.src='validatecode?d='+Math.random();">刷新</a></td>
                </tr>

                <tr>
                    <td colSpan="2" align="center">
                        <input type="button" class="btnalink" onclick="loginsubmit()" value="登&nbsp;&nbsp;录"/>
                        <input type="reset" class="btnalink" value="重&nbsp;&nbsp;置"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>
</body>
</HTML>
