<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/2
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>玉竹阁</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="statics/css/font.css">
    <link rel="stylesheet" href="statics/css/xadmin.css">

    <script src="statics/js/jquery.min.js"></script>
    <script src="statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="statics/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="index.html">玉竹阁</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <c:if test="${username==null}">
                <a href="javascript:;">未登录</a>
                <dl class="layui-nav-child"> <!-- 二级菜单 -->
                    <dd><a href="pages/user/login.jsp">去登陆</a></dd>
                </dl>
            </c:if>
            <c:if test="${username!=null}">
                <a href="javascript:;">${username}</a>
                <dl class="layui-nav-child"> <!-- 二级菜单 -->
                    <dd><a href="/Final_project/UserServlet?action=finduser">个人信息</a></dd>
                    <dd><a href="/Final_project/UserServlet?action=exit">退出</a></dd>
                </dl>
            </c:if>
        </li>
        <li class="layui-nav-item to-index"><a href="#"></a></li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li >
                <a href="javascript:;">
                    <i class="iconfont">&#xe6e4;</i>
                    <cite>菜单</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <li><a _href="/Final_project/pages/menu/menu1.jsp"><i class="iconfont">&#xe6a7;</i><cite>川菜</cite></a></li>
                    <li><a _href="/Final_project/pages/menu/menu2.jsp"><i class="iconfont">&#xe6a7;</i><cite>鲁菜</cite></a></li>
                    <li><a _href="/Final_project/pages/menu/menu3.jsp"><i class="iconfont">&#xe6a7;</i><cite>粤菜</cite></a></li>
                    <li><a _href="/Final_project/pages/menu/menu4.jsp"><i class="iconfont">&#xe6a7;</i><cite>淮扬菜</cite></a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;"><i class="iconfont">&#xe6f6;</i><cite>个人中心</cite><i class="iconfont nav_right">&#xe6a7;</i></a>
                <ul class="sub-menu">
                    <li><a _href="/Final_project/UserServlet?action=finduser"><i class="iconfont">&#xe6a7;</i><cite>用户管理</cite></a></li>
                    <li><a _href="/Final_project/UserServlet?action=findmoney"><i class="iconfont">&#xe6a7;</i><cite>余额充值</cite></a></li>
                    <li><a _href="/Final_project/pages/rate/myrates.jsp"><i class="iconfont">&#xe6a7;</i><cite>评论管理</cite></a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;"><i class="iconfont">&#xe6f6;</i><cite>订单管理</cite><i class="iconfont nav_right">&#xe6a7;</i></a>
                <ul class="sub-menu">
                    <li><a _href="/Final_project/pages/orders/myorders.jsp"><i class="iconfont">&#xe6a7;</i><cite>我的订单</cite></a></li>
                    <li><a _href="/Final_project/pages/orders/history.jsp"><i class="iconfont">&#xe6a7;</i><cite>历史订单</cite></a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;"><i class="iconfont">&#xe6f6;</i><cite>热门评论</cite><i class="iconfont nav_right">&#xe6a7;</i></a>
                <ul class="sub-menu">
                    <li><a _href="/Final_project/pages/rate/hotRates.jsp"><i class="iconfont">&#xe6a7;</i><cite>热门评论</cite></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>首页</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='statics/html/welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<!--<div class="footer">
    <div class="copyright">Copyright ©2019 L-admin v2.3 All Rights Reserved</div>
</div>-->
<!-- 底部结束 -->
</body>
</html>
