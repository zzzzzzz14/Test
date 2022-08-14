<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/29
  Time: 16:59
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
<script type="text/html" id="tablebar2">
    <div class="layui-btn-container">
        <i class="layui-icon" style="font-size: 30px;" lay-event="del">&#x1006;</i>
    </div>
</script>
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
            url: 'http://localhost:8081/Final_project/RateServlet?action=selectMyrate',
            cols: [[
                {type: 'checkbox', fixed: 'left' },
                {field: 'id', title: 'id', sort: true, fixed: 'left',hide:true }
                , {field: 'username', title: '用户名', sort: true, fixed: 'left',hide:true}
                , {field: 'evaluate', title: '评论', sort: true, fixed: 'left'}
                , {field: 'evaluateValue', title: '星级', sort: true, fixed: 'left'}
                , {field: 'ratedate', title: '评价时间', sort: true, fixed: 'left'}
                , {fixed: 'right', title: '操作', align: 'left', toolbar: '#tablebar2'}
            ]],
            toolbar: '#tablebar1',
            totalRow: true,
            limit: 10,
            limits: [3,6,10],
            page: true,
        })

        table.on('tool(test)', function (obj) {
            var data = obj.data;
            var layeven = obj.event;
            if (layeven === 'del') {
                $.ajax({
                    type: 'post'
                    , url: '/Final_project/RateServlet?action=delrate'
                    , data: {'id': data.id}
                    , success: function (result) {
                        if(result==='succ'){
                            layer.msg("删除成功");
                        }else {
                            layer.msg("删除失败，请稍后再试");
                        }
                        table.reload('demo');//更新表格

                    }
                })
            }

        })
    })
</script>
</html>
