<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/23
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pay</title>
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" action="/Lxzhangyaning/payServlet" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">书名</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="name" id="name" value="数据结构" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">作者</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input"name="author" id="author"value="李红" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出版社：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input"name="press" id="press" value="清华大学出版社">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单价：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input"name="price" id="price" value="31.5">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input"name="num" id="num" value="3">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>
</body>
</html>
