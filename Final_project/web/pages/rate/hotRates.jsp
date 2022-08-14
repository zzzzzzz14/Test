<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/29
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <style>
        .jiesuan{
            padding-left: 92%;
        }
    </style>
</head>
<body>
<table id="demo" lay-filter="test"></table>
</body>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
    layui.use(['table', 'layer', 'util'], function () {
        let table = layui.table;
        var util = layui.util;
        let $ = layui.jquery;
        let layer = layui.layer;

        table.render({
            elem: '#demo',
            // height: 'full-200',
            defaultToolbar: ['filter', 'exports', 'print'],
            url: 'http://localhost:8081/Final_project/RateServlet?action=selectHotrate',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'id', sort: true, fixed: 'left', hide: true}
                , {field: 'username', title: '用户名', sort: true, fixed: 'left'}
                , {field: 'evaluate', title: '热门评论', sort: true, fixed: 'left'}
                , {field: 'evaluateValue', title: '评论星级', sort: true, fixed: 'left'}
                , {field: 'ratedate', title: '评论时间', sort: true, fixed: 'left'}
            ]],
            toolbar: '#tablebar1',
            totalRow: true,
            limit: 10,
            limits: [3, 6, 10],
            page: true,
        })
    })
</script>
</html>
