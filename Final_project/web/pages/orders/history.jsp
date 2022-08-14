<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/5/17
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>

</head>
<body>
<table id="demo" lay-filter="test"></table>
</body>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
    layui.use(['layer'], function () {
        let layer = layui.layer;
        let $ = layui.jquery;

    });
</script>
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
            url: 'http://localhost:8081/Final_project/OrderServlet?action=selectMyHisorders',
            cols: [[
                {type: 'checkbox', fixed: 'left' },
                {field: 'id', title: 'id', sort: true, fixed: 'left',hide:true }
                , {field: 'username', title: '用户名', sort: true, fixed: 'left',hide:true}
                , {field: 'info', title: '点菜信息', sort: true, fixed: 'left'}
                , {field: 'money', title: '花费', sort: true, fixed: 'left'}
                // , {field: 'evaluate', title: '评价', sort: true, fixed: 'left'}
                // , {field: 'evaluate', title: '评价星级', sort: true, fixed: 'left'}
                , {field: 'ordertime', title: '结算时间', sort: true, fixed: 'left'}
            ]],
            toolbar: '#tablebar1',
            totalRow: true,
            limit: 10,
            limits: [3,6,10],
            page: true,
        })
    })
</script>
</html>
