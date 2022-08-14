<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=path%>/statics/css/font.css">
<link rel="stylesheet" href="<%=path%>/statics/css/xadmin.css">
<script src="<%=path%>/statics/js/jquery.min.js"></script>
<script type="text/javascript"src="<%=path%>/statics/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/statics/js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
	  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
	  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

<title>职务管理</title>
</head>
<body>
	<div class="x-body">
      <div class="layui-row">
      <form class="layui-form layui-col-md12 x-so">
        <div class="layui-input-inline" id="search">
          <input class="layui-input" placeholder="开始日" name="cdate_s" id="start" autocomplete="off">
          <input class="layui-input" placeholder="截止日" name="cdate_e" id="end" autocomplete="off">
          <input type="text" name="cname" id="cname" placeholder="请输入职务名称" autocomplete="off" class="layui-input">
          <button type="button" class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
        </div>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加职务','<%=path %>/view/post/Post.jsp',700,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
          <table class="layui-table" id="mytable" lay-filter="mytable"></table>
          <script type="text/html" id="bardemo">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
          </script>
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
      layui.use('table',function(){
			var table = layui.table;
			table.render({
				elem:'#mytable'
				,url:'/ChinapartyProject/getAllPostByPageServlet'
				,method:'post'
				,cols:[[
						{type:'checkbox'}
						,{title:'序号',type:'numbers'}
						// ,{title:'ID',hide:'post_id'}
						,{title:'名称',field:'postName',sort:true}
						,{title:'操作时间',field:'postCDate',sort:true}
						,{title:'操作',field:'operation',toolbar:'#bardemo',fixed:'right'}
					]]
				,page:true
				,limit:10
			});
    	  	
			//行工具事件
			  table.on('tool(mytable)', function(obj){
			    var data = obj.data;
			    //console.log(obj)
			    if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			    	  $.ajax({
		            	  type:'POST'
		            	  ,url:'/ChinapartyProject/deletePostServlet'
		            	  ,data:{"cid":data.post_id}
			              ,success:function(data){
				          		if(data=="Ok"){
				          			layer.alert("删除成功", {icon: 6});
				          			obj.del();
				          		}else{
				          			layer.alert(data,{title:'删除失败'}); 
				          		}
			          	}
		              	  
		              });
			      });
			    } else if(obj.event === 'edit'){
			    	x_admin_show('编辑','/ChinapartyProject/findPostServlet?cid='+data.post_id,700,400);
			    }
			  }); 

			//查询
			var active={
					reload:function(){
						//获取查询参数
						var sdate=$('#start');
						var edate=$('#end');
						var cname=$('#cname');
						//执行重载
						table.reload('mytable',{
							page:{
								curr:1
							}
							,where:{
								 sdate:sdate.val()
								,edate:edate.val()
								,cname:cname.val()
							}
							,method:'post'
						});
					}
			};

			//激活查询动作
			$('#search .layui-btn').on('click', function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
      });
    </script>
</body>
</html>