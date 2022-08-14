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
        <div class="layui-input-inline">
        <select name="cflag" id="cflag">
            <option>状态</option>
            <option value="0">禁用</option>
            <option value="1">启用</option>
        </select>
        </div>
        <div class="layui-input-inline" id="search">
          <input type="text" name="cname" id="cname" placeholder="请输入履历类型" autocomplete="off" class="layui-input">
          <button type="button" class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
        </div>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加班级','<%=path %>/view/processtype/Processtype.jsp',700,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <table class="layui-table" id="mytable" lay-filter="mytable"></table>
      <script type="text/html" id="bardemo">
          <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      </script>   
    </div>
    <script>
    
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
          layui.use('table',function(){
                 var table=layui.table;
                 table.render({
                	 elem:'#mytable'
                	 ,url:'/ChinapartyProject/getAllProcesstypeByPageServlet'
                	 ,method:'post'
                	 ,cols:[[// 列
                		 {type:'checkbox'}
                		 ,{title:'序号',type:'numbers'}
                		 // ,{title:'ID',hide:'processtype_id'}
                		 ,{title:'名称',field:'ptypeName',sort:true}//sort:true是有排序功能
                		 ,{title:'状态',field:'ptypeFlag',sort:true}
                		,{title:'操作',field:'operation',toolbar:'#bardemo',fixed:'right'}
                       	  ]]
                 ,page:true
                 ,limit:10//每页有几条数据
                 });
                 //行工具事件
                 table.on('tool(mytable)', function(obj){
                   var data = obj.data;
                   //console.log(obj)
                   if(obj.event === 'del'){
                     layer.confirm('真的删除行么', function(index){
                    	 $.ajax({
                        	 type:'POST'
                        	 ,url:'/ChinapartyProject/deleteProcesstypeServlet'
                        	 ,data:{"cid":data.processtype_id}
                             ,success:function(data){
                        	 if(data=="Ok"){
                        		 layer.alert('删除成功',{icon:6});
                        		 obj.del();//删除表中当前行
                        	 }else{
                        		 layer.alert(data,{title:'删除失败'});
                        	 }
                         }
                      }); 
                     });
                   } else if(obj.event === 'edit'){
                	   x_admin_show('编辑','/ChinapartyProject/findProcesstypeServlet?cid='+data.processtype_id, 700, 400);
                   }
                 });
                 //查询
                 var active={
                		reload:function(){
                			//获取查询参数
                			var cflag=$('#cflag');
                			
                			var cname=$('#cname');
                			//执行重载
                			table.reload('mytable',{
                				page:{
                					curr:1
                				}
                			    ,where:{
                			    	cflag:cflag.val()
                			    	
                			    	,cname:cname.val()
                			    }
                			    ,method:'post'
                			});
                		} 
                 };
                 //激活查询操作
                 $('#search .layui-btn').on('click', function(){
                	    var type = $(this).data('type');
                	    active[type] ? active[type].call(this) : '';
                	  });
          });
    </script>
</body>
</html>