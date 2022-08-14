<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/23
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Succ</title>
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/layui/layui.js"></script>
</head>
<body>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">购买成功！<br>您所购买的商品详情如下:</label>
<%--        <label class="layui-form-label">购买成功！</label><br>--%>
<%--        <div class="layui-input-inline">--%>
<%--            <label class="layui-input-inline">您所购买的商品详情如下:</label>--%>
<%--        </div>--%>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">书名：${Book.name}</label>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">作者：${Book.author}</label>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class=layui-input-inline">出版社：${Book.press}</label>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">单价：${Book.price}</label>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">购买数量：${Book.num}</label>
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-input-inline">需付金额：${ans}￥</label>
    </div>
</div>
</body>
</html>
