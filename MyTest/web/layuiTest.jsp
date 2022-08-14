<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/5/17
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
</head>
<body>
<table id="demo" lay-filter="test"></table>

<script type="text/html" id="tablebar1">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn" id="getAll" lay-event="getAll">获取选中行</button>
        <button type="button" class="layui-btn" id="delAll" lay-event="dellAll">批量删除</button>
    </div>
</script>

<script type="text/html" id="tablebar2">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn " lay-event="detail">查看</button>
        <i class="layui-icon" style="font-size: 30px;" lay-event="increase" >&#xe61f;</i>
        <i class="layui-icon" style="font-size: 30px;" lay-event="reduce">&#xe616;</i>
        <i class="layui-icon" style="font-size: 30px;" lay-event="del">&#x1006;</i>
    </div>
</script>
<button type="button" class="layui-btn" id="btn2">confirm</button>
</body>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
    layui.use(['layer'], function () {
        let layer = layui.layer;
        let $ = layui.jquery;


        $('#btn2').on('click', function () {
             //例子1，icon取值可以看官网，1到7
            layer.confirm('确定删除？?', {icon: 3, title: '提示'}, function (index) {
                //do something
                layer.close(index);
            });
        });
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
            url: 'http://localhost:8081/MyTest/Json',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'id', sort: true, fixed: 'left', totalRowText: '合计'}
                , {field: 'username', title: '用户名', sort: true, fixed: 'left'}
                , {field: 'goodname', title: '菜品名', sort: true, fixed: 'left'}
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
            if (layeven === 'del') {
                layer.alert(data.id, {icon: 1});
            }
            if (layeven === 'increase') {
                // layer.alert(data.id,{icon:1});
                $.ajax({
                    type: 'post'
                    , url: '/MyTest/updateGoods?action=increase'
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
                    , url: '/MyTest/updateGoods?action=reduce'
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
                    , url: '/MyTest/updateGoods?action=del'
                    , data: {'id': data.id}
                    , success: function (result) {
                        table.reload('demo');//更新表格
                    }
                })
            }

        })

    })
</script>
</html>
