<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/5/31
  Time: 9:19
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
    <title>新增部门信息</title>
</head>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="cname" class="layui-form-label">
                <span class="x-red">*</span>部门名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="cname" name="cname" required lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>部门名称不可同名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="cflag" class="layui-form-label">
                <span class="x-red">*</span>是否可用
            </label>
            <div class="layui-input-inline">
                <input type="radio" id="cflag" name="cflag" value="1" title="启用" checked="checked"/>
                <input type="radio" name="cflag" value="0" title="禁用"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>上级部门
            </label>
            <div class="layui-input-inline">
                <select name="pdname" id="pdname">
                    <!-- <option value="1">2018年</option>
                    <option value="2">2019年</option>
                    <option value="3">2020年</option> -->
                    <!-- 将数据库years表中的数据读过来 -->
<%--                    <c:forEach items="${years }" var="y" varStatus="row">--%>
<%--                        <option value="${y.years_id }">${y.yearsname}</option>--%>

<%--                    </c:forEach>--%>

                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="cdate" class="layui-form-label">
                <span class="x-red">*</span>添加时间
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" id="cdate" name="cdate" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="cdate" class="layui-form-label">
                </span>
            </label>
            <div class="layui-input-inline">
                <button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#cdate',
            type: "datetime",
            trigger: 'click',
            value: new Date(),
            isInitValue: true
        });
    });

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        $.ajax({
            url: '/ChinapartyProject/getDeptsServlet',
            dataType: 'json', //服务器返回json格式数据
            type: 'post', //HTTP请求类型
            timeout: 10000, //超时时间设置为10秒；
            success: function (data) {
                $.each(data.data, function (index, value) {
                    $('#pdname').append(
                        new Option(value.departmentName, value.department_id));// 下拉菜单里添加元素
                });

                // 工具客户id，选择客户对应的值
                /* const value = [[
                ${project.years}]];
					$('#years').find("option[value="+value+"]").attr("selected",true); */
                layui.form.render("select");
            }
        });


        //监听提交
        form.on('submit(add)', function (data) {
            console.log(data);//data采集form的数据
            //发异步，把数据提交给php
            $.ajax({
                type: 'POST'
                , url: '/ChinapartyProject/addDepartmentervlet'
                , data: data.field
                , success: function (data) {
                    if (data == "Ok") {
                        layer.alert("增加成功", {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            window.parent.location.reload();
                        });
                    } else {
                        layer.alert(data, {title: '添加失败'});
                    }
                }
            });
            return false;
        });


    });
</script>
</body>
</html>
