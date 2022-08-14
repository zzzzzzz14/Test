<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% String path=request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
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
<title>班级信息管理</title>
</head>
<body>
	 <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        <!-- 使状态与其他俩个同为一行 -->
        <div class="layui-input-inline">
        	<select name="cflag">
          	<option>状态</option>
            <option value="0">禁用</option>
            <option value="1">启用</option>
          </select>
        </div>
          <input class="layui-input" placeholder="开始日" name="classescdate_s" id="start">
          <input class="layui-input" placeholder="截止日" name="classescdate_e" id="end">
          <input type="text" name="classname"  placeholder="请输入班级名称" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加班级','./view/classes/Classes.jsp',500,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>序号</th>
            <th>名称</th>
            <th>状态</th>
            <th>入学年份</th>
            <th>操作时间</th>
            <th>操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${classes }" var="cls" varStatus="row">
          <tr>
          
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
			<td>${row.count }</td>
			<td>${cls.classesName }</td>
			<td>${cls.classesFlag }</td>
			<td>${cls.yearsname }</td>
			<td>${cls.classesCDate }</td>
			
           
            <td class="td-manage">
              <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                <i class="layui-icon">&#xe601;</i>
              </a>
              <a title="编辑"  onclick="x_admin_show('编辑','/ChinapartyProject/findClassesServlet?cid=${cls.classes_id}',700,400)" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="classes_del(this,'${cls.classes_id}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>
    </div>
     <script>
     layui.use('laydate',function(){
 		var laydate=layui.laydate;
 		laydate.render({
 			elem:'#start',
 		});
 	});
     layui.use('laydate',function(){
  		var laydate=layui.laydate;
  		laydate.render({
  			elem:'#end',
  		});
  	});
       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*班级-删除*/
      function classes_del(obj,cid){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //$(obj).parents("tr").remove();
              //layer.msg('已删除!',{icon:1,time:1000});
              //layer.alert(cid);
              $.ajax({
            	  type:'POST'
            	  ,url:'/ChinapartyProject/deleteClassesServlet'
            	  ,data:{"cid":cid}
              	,success:function(data){
              		if(data=="Ok"){
              			layer.alert('删除成功',{icon:6});
              			$(obj).parents("tr").remove();//删除表中当前行
              		}else{
              			layer.alert(data,{title:'删除失败'});
              		}
              	}
              });
          });
      }
    </script>
</body>
</html>