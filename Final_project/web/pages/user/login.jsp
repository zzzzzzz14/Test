<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/2
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>regist&login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
</head>
<body>
<div class="video-box">
    <video class="video-background" preload="auto" loop playsinline autoplay src="${pageContext.request.contextPath}/video/login.mp4" tabindex="-1"
           muted="muted"></video>
</div>
<div class="layui-box loginpage" id="loginpage">
    <div align="center" class="imgg" id="img1">
        <i class="layui-icon" style="font-size: 30px;">&#xe770; 登录</i>
    </div>
    <div align="center" class="imgg" id="img2">
        <i class="layui-icon" style="font-size: 30px;">&#xe650; 注册</i>
    </div>
    <form class="layui-form" lay-filter="form1" id="form1">
        <div class="layui-inline">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="login_username" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input" lay-event="user">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="userpass" id="login_userpass" required lay-verify="required"
                           placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                    <input type="text" name="imgcode" id="login_imgcode" required lay-verify="required" placeholder="请输入验证码"
                           autocomplete="off" class="layui-input">
                    <img src="${pageContext.request.contextPath}/kaptcha.jpg" id="login_img" width="90px" height="40px">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit lay-filter="login" style="width: 100px" id="login">登录</button>
                    <button class="layui-btn layui-btn-radius layui-btn-primary"  lay-filter="goregist" style="width: 100px" id = "goregist">去注册</button>
                </div>

            </div>
        </div>
    </form>
    <form class="layui-form" lay-filter="form2" id="form2">
        <div class="layui-inline">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="regist_username" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input" lay-event="user">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="userpass" id="regist_userpass" required lay-verify="required"
                           placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" id="regist_phone" required lay-verify="phone"
                           placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                    <input type="text" name="imgcode" id="regist_imgcode" required lay-verify="required" placeholder="请输入验证码"
                           autocomplete="off" class="layui-input">
                    <img src="${pageContext.request.contextPath}/kaptcha.jpg" id="regist_img" width="90px" height="40px">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-radius layui-btn-warm" lay-submit  lay-filter="regist" style="width: 100px" id = "regist">注册</button>
                    <button class="layui-btn layui-btn-radius layui-btn-primary"  lay-filter="gologin" style="width: 100px" id = "gologin">去登录</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login.js"></script>
</html>
