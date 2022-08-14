<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信缴纳水费</title>
<link rel="stylesheet" href="static/layui/css/layui.css">
<script src="static/layui/layui.js"></script>
</head>
<body>
	<form class="layui-form" action="/Exam_zhangyaning/jiaofeiServlet" method="post"><!--method="post"作用是中文转义  -->
	
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">余额：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="yuer" id="yuer" value="${user.yuer}元" ></input>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">缴费用户名：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="sname" id="sname" value="张亚宁" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">缴费户号：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="snum" id="snum" value="2014126042" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">缴费单位：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="sdept" id="sdept" value="包头市惠民水务水费" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">输入金额：</label>
				<div class="layui-input-inline">
					<input type="text" name="jiner" id="jiner" lay-verify="required"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="submit" class="layui-btn" lay-submit=""
					lay-filter="demo1">立即缴费</button>
			</div>
		</div>
	</form>
</body>
</html>