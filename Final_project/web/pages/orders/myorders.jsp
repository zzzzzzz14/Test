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
        <i class="layui-icon" style="font-size: 30px;" lay-event="increase" >&#xe61f;</i>
        <i class="layui-icon" style="font-size: 30px;" lay-event="reduce">&#xe616;</i>
        <i class="layui-icon" style="font-size: 30px;" lay-event="del">&#x1006;</i>
    </div>
</script>
<div class="jiesuan"><button type="button" class="layui-btn layui-btn-danger" id="btn2">结算订单</button></div>
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
            url: 'http://localhost:8081/Final_project/OrderServlet?action=selectMyorders',
            cols: [[
                {type: 'checkbox', fixed: 'left' },
                {field: 'id', title: 'id', sort: true, fixed: 'left',hide:true }
                , {field: 'username', title: '用户名', sort: true, fixed: 'left',hide:true}
                , {field: 'fname', title: '菜品名', sort: true, fixed: 'left', totalRowText: '合计'}
                , {field: 'num', title: '数量', sort: true, fixed: 'left'}
                , {field: 'price', title: '单价', sort: true, fixed: 'left'}
                , {field: 'total_prices', title: '总价', sort: true, fixed: 'left', totalRow: true}
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
            if (layeven === 'increase') {
                // layer.alert(data.id,{icon:1});
                $.ajax({
                    type: 'post'
                    , url: '/Final_project/OrderServlet?action=increase'
                    , data: {'id': data.id}
                    , success: function (result) {
                        if (result == 1) {
                            table.reload('demo');//更新表格
                        }
                    }
                })
            }
            if (layeven === 'reduce') {
                $.ajax({
                    type: 'post'
                    , url: '/Final_project/OrderServlet?action=reduce'
                    , data: {'id': data.id}
                    , success: function (result) {
                        table.reload('demo');//更新表格
                        /*if (result == 0) {
                            layer.alert('不能买负数的哦', {icon: 2});
                        } else if (result == 1) {
                        }*/
                    }
                })
            }
            if (layeven === 'del') {
                $.ajax({
                    type: 'post'
                    , url: '/Final_project/OrderServlet?action=del'
                    , data: {'id': data.id}
                    , success: function (result) {
                        table.reload('demo');//更新表格
                    }
                })
            }

        })
        $('#btn2').on('click', function (index) {
            layer.confirm('结算账单？?', {icon: 3, title: '提示',btn: ['是的', '容我考虑一下']}, function () {
                $.ajax({
                    type: 'post'
                    , url: '/Final_project/OrderServlet?action=balance'
                    // , data: {'id': data.id}
                    , success: function (result) {
                        if(result==='succ'){
                            $.ajax({
                                type: 'post'
                                , url: '/Final_project/OrderServlet?action=orderhistory'
                            })
                            layer.confirm('结算成功，是否评价本次消费？？', {icon: 3, title: '提示',btn: ['是的', '容我考虑一下']}, function () {
                                window.location.href="/Final_project/pages/orders/rate.jsp";
                            })
                            table.reload('demo');//更新表格
                        }else if(result==='erro'){
                            layer.alert("可用余额不足，请充值后消费");
                        }else if(result==='null'){
                            layer.alert("请点些东西再结算吧？！！");
                        }
                    }
                })
                table.reload('demo');//更新表格
                layer.close(index);
            });
        });
    })
</script>
</html>
