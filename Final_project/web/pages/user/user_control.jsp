<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/26
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% String path = request.getContextPath(); %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=path %>/statics/css/font.css">
    <link rel="stylesheet" href="<%=path %>/statics/css/xadmin.css">
    <script src="<%=path %>/statics/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/statics/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>个人中心</title>
    <style>
        .page {
            width: 500px;
            height: 390px;
            position: absolute;
            left: 0;
            top: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            background-color: #F9F8F8;
            border-radius: 25px;

        }
    </style>
</head>
<body>
<div class="x-body page">
    <form class="layui-form" id="form">
        <i class="layui-icon">&#xe66f;</i>
        <h2>个人中心</h2><br>
        <div class="layui-form-item">
            <input type="hidden" name="logins_id" value="">
            <label for="" class="layui-form-label">
                <span class="x-red">*</span>用户名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username"  required lay-verify="required"
                       autocomplete="off" class="layui-input" value="${user.username}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="userpass" name="userpass" required lay-verify="required"
                       autocomplete="off" class="layui-input" value="${user.userpass}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
                <span class="x-red">*</span>手机号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="phone" required lay-verify="required"
                       autocomplete="off" class="layui-input" value="${user.phone}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
                <span class="x-red"></span>余额
            </label>
            <div class="layui-input-inline">
                <label class="layui-form-label">
                    ${user.money}元
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
                </span>
            </label>
            <div class="layui-input-inline">
                <button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    //监听提交
    layui.use(['form', 'layer', 'util'], function () {
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        var util = layui.util;

        form.on('submit(update)', function (data) {
            var formData = data.field;
            $.ajax({
                type: 'POST'
                , url: '/Final_project/UserServlet'
                , data: {
                    'username': formData.username,
                    'userpass': formData.userpass,
                    'phone':formData.phone,
                    'action':'updateuser'
                }
                , success: function (data) {
                    if (data == "succ") {
                        layer.alert("修改成功", {icon: 6}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        layer.alert(data, {title: '修改失败'});
                    }
                }
            });
            return false;
        });
    })
</script>
</html>
