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
<title>编辑班级信息</title>
</head>
<body>
	<jsp:include page="/view/classes/GetYears.jsp"></jsp:include>
	
	<div class="x-body">
        <form class="layui-form">
          <div class="layui-form-item">
          	<input type="hidden" name="classes_id" value="${cls.classes_id }">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>班级名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="classname" name="classname" required lay-verify="required"
                  autocomplete="off" class="layui-input" value="${cls.classesName }">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="x-red">*</span>班级名称不能同名
              </div>
          </div>
          
          
         <div class="layui-form-item">
              <label for="cflag" class="layui-form-label">
                  <span class="x-red">*</span>是否可用
              </label>
              <div class="layui-input-inline">
              	<c:choose>
						<c:when test="${cls.classesFlag=='启用' }">
							<input type="radio" name="cflag" value="1" checked="checked" title="启用"></input>
							<input type="radio" name="cflag" value="0" title="禁用"></input>
						</c:when>
						<c:otherwise>
							<input type="radio" name="cflag" value="1" title="启用"></input>
							<input type="radio" name="cflag" value="0" checked="checked" title="禁用"></input>
						
						</c:otherwise>
					</c:choose>
              	
              </div>
           </div>
            <div class="layui-form-item">
              <label for="years" class="layui-form-label">
                  <span class="x-red">*</span>入学年份
              </label>
              
              <div class="layui-input-inline">
              	<select name="years" >
                      <c:forEach items="${years }" var="y" varStatus="row">
                     <c:choose>
                    <c:when test="${cls.years_id==y.years_id }">
                     <option value="${y.years_id }" selected="selected">${y.yearsname }</option>
                      </c:when>
                  <c:otherwise>
                   <option value="${y.years_id }">${y.yearsname}</option>
                 </c:otherwise>
                </c:choose>
              </c:forEach>
    
            </select>
              
                
              </div>
           </div>
            <div class="layui-form-item">
              <label for="years" class="layui-form-label">
                  <span class="x-red">*</span>修改时间
              </label>
              
              <div class="layui-input-inline">
                  <input class="layui-input" id="classcdate" name="classcdate" required lay-verify="required"
                  autocomplete="off" value="${cls.classesCDate }">
              </div>
           </div>
           <div class="layui-form-item">
              <label for="classcdate" class="layui-form-label">
                  </span>
              </label>
              <div class="layui-input-inline">
                  <button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
              </div>
           </div>
   
          </form>
    </div>
    <script>
    	layui.use('laydate',function(){
    		var laydate=layui.laydate;
    		laydate.render({
    			elem:'#classcdate',
    			//type:"datetime",
    			trigger:'click'
    			//value:new Date(),
    			//isInitValue:true
    		});
    	}
    			
    	);
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        

          //监听提交
          form.on('submit(update)', function(data){
            //console.log(data);//date采集form的数据
            //发异步，把数据提交给php
            $.ajax({
            	type:'POST'
            	,url:'/ChinapartyProject/updateClassesServlet'
            	,data:data.field
            	,success:function(data){
            		if(data=="OK"){
            			 layer.alert("修改成功", {icon: 6},function () {
            	                // 获得frame索引
            	                var index = parent.layer.getFrameIndex(window.name);
            	                //关闭当前frame
            	                parent.layer.close(index);
            	                window.parent.location.reload();
            	            });
            		}else{
            			layer.alert(data,{title:'修改失败'});
            		}
            	}
            });
           
            return false;
          });
          
          
        });
    </script>
    
    
	<form action="${pageContext.request.contextPath }/updateClassesServlet" method="post">
		<table>
			<tr>
				<td>班级名称：</td><td>
				<input type="hidden" name="classes_id" value="${cls.classes_id }">
				<input type="text" name="classname" value="${cls.classesName }"/></td>
			</tr>
			<tr>
				<td>是否可用：</td>
				<td>
					
					<c:choose>
						<c:when test="${cls.classesFlag=='启用' }">
							<input type="radio" name="cflag" value="1" checked="checked">启用</input>
							<input type="radio" name="cflag" value="0" >禁用</input>
						</c:when>
						<c:otherwise>
							<input type="radio" name="cflag" value="1" >启用</input>
							<input type="radio" name="cflag" value="0" checked="checked">禁用</input>
						
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>入学年份：</td>
				<td>
					<select name="years">
						<!-- <option value="1">2018年</option>
						<option value="2">2019年</option> -->
						<c:forEach items="${years }" var="y" varStatus="row">
							<%--<option value="${y.years_id }">${y.yearsname }</option> --%>
							<c:choose>
								<c:when test="${cls.years_id==y.years_id }">
									<option value="${y.years_id }" selected="selected">
										${y.yearsname }
									</option>
								</c:when>
								<c:otherwise>
									<option value="${y.years_id }">
										${y.yearsname }
									</option>
								</c:otherwise>
								
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>操作时间：</td><td><input type="text" name="classcdate" value="${cls.classesCDate }" /></td>
			</tr>
		</table>
		<input type="submit" value="修改" />${flag }
	
	
	</form>

</body>
</html>