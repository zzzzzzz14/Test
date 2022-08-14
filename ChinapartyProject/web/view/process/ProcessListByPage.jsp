<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% String path=request.getContextPath(); %>
	<meta charset="UTF-8">
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
    <title>履历信息管理</title>
</head>
<body>
	<div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        <!-- <div class="layui-input-inline">
        	<select name="cflag" id="cflag">
        		<option>状态</option>
        		<option value="0">禁用</option>
        		<option value="1">启用</option>
       	 	</select>
        </div> -->
       <div class="layui-input-inline" id="search">
          <input class="layui-input" placeholder="开始日" name="procescdate_s" id="start" autocomplete="off">
          <input class="layui-input" placeholder="截止日" name="processcdate_e" id="end" autocomplete="off">
          <input type="text" name="processContent" id="processContent" placeholder="输入履历内容" autocomplete="off" class="layui-input">
          <button type="button" class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
        </div>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加履历','<%=path %>/view/process/Process.jsp',700,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      
      <table class="layui-table" id="mytable" lay-filter="mytable"></table>
      <script type="text/html" id="bardemo">
	   		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
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
    //履历信息分页
      layui.use('table',function(){
  		var table=layui.table;
  		table.render({
  			elem:'#mytable'
  			,url:'/ChinapartyProject/getAllProcessByPageServlet'
  			,method:'post'
  			,cols:[[
  				{type:'checkbox'}
  				,{title:'序号',type:'numbers'}
  				// ,{title:'编号',hide:'process_id'}
  				,{title:'履历类型',field:'ptypeName',sort:true}
  				,{title:'时间',field:'processDate',sort:true}
  				,{title:'地点',field:'processPlace',sort:true}
  				,{title:'经办人',field:'processHandler',sort:true}
  				,{title:'履历内容',field:'processContent',sort:true}
  				,{title:'操作人',field:'logins_id',sort:true}
  				,{title:'操作时间',field:'processTime',sort:true}
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
           	  ,url:'/ChinapartyProject/deleteProcessServlet'
           	  ,data:{"cid":data.process_id}
             	  ,success:function(data){
             		  if(data=="Ok"){
             			  layer.alert('删除成功',{icon:6});
             			  obj.del();
             		  }else{
             			  layer.alert(data,{title:'删除失败'});
             		  }
             	  }
             });
  	      });
  	    } else if(obj.event === 'edit'){
  	    	x_admin_show('编辑','/ChinapartyProject/findProcessServlet?cid='+data.process_id,700,400);
  	    }
  	  }); 
  	//查询
      var active={
    		  reload:function(){
    			  //获取查询数据
    			  /* var cflag=$('#cflag'); */
    			  var sdate=$('#start');
    			  var edate=$('#end');
    			  var cname=$('#processContent');
    			  //执行重载
    			  table.reload('mytable',{
    				  page:{
    					  curr:1
    				  }
    			  ,where:{
    				  /* cflag:cflag.val() */
    				   sdate:sdate.val()
    			      ,edate:edate.val()
    			      ,pcontent:cname.val()
    			  }
    			  ,method:'post'
    			  });
    		  }
      	};
      //激活查询动作
      $('#search .layui-btn').on('click', function(){//当点击时，执行下面的操作
          var type = $(this).data('type');
          active[type] ? active[type].call(this) : '';
        });
	});
    </script>
</body>
</html>