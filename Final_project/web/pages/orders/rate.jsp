<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/29
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>评分组件</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <style>
        .divv{
            width: 600px;
            height: 500px;
            position: absolute;
            left: 0px;
            top: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            background-color: #E9E7E7;
            border-radius:25px;
        }
        .boxx h1{
            padding-left: 70px;
        }
        .boxx div{
            padding-left: 200px;
        }
        .boxx input{
            margin-left: 30px;
        }
        .boxx button{
            margin-left: 230px;
        }
    </style>
</head>
<body>
<div class="divv" id="divv">
    <div class="boxx">
    <h1>欢迎评价，让我们听到顾客的声音</h1>
    <div id="rate"></div><br>

        <input type="text" name="evaluate" id="evaluate" required lay-verify="required" placeholder="请输入评价"
                class="layui-input"  style="width: 500px;height: 300px"><br><br>
    <button type="button" class="layui-btn layui-btn-danger" id="but" >点击评价</button></div>
</div>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
    layui.use(['layer', 'jquery', 'rate'], function () {
        var $ = layui.jquery;
        var rate = layui.rate;
        // 渲染评分组件
        var instance = rate.render({
            elem: '#rate',
            length: 5,
            theme: '#FFB800',
            choose: function (value) {
                // 此处点击分数之后可用Ajax传递到后台并保存到数据库 具体略
                // 评分完成之后重载实例
                rate.render({
                    elem: '#rate',
                    length: 5,
                    theme: '#FFB800',
                    // value值也可以是前台通过Ajax获取到的数据库中的值
                    value: value,
                })
            }
        });
        $("#but").click(function () {
            // layer.msg("获取分数：" + instance.config.value);
            var evaluate=$("#evaluate").val();
            var evaluateValue=instance.config.value;
            $.ajax({
                type: 'post'
                , url: '/Final_project/RateServlet'
                , data: {'evaluate':evaluate,'evaluateValue':evaluateValue,'action':'addrate'}
                , success: function (result) {
                    if(result==='succ'){
                        layer.msg("评价成功");
                        setTimeout(function (){
                            window.location.reload();
                        }, 500);
                    }else {
                        layer.msg("评价失败，请稍后再试");
                    }
                }
            })

        });
    });
</script>
</body>
</html>
