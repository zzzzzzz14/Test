<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/5/14
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
</head>
<body>
<form action="" method="get">
    用户名 : <input name="username" type="text"><br>
    密码 : <input name="pass" type="password"> <br>
    验证码: <input type="text" name="img" id="imgstr"><br>
    <img src="${pageContext.request.contextPath}/kaptcha.jpg" id="img"><br>
    <input type="submit" value="提交" id="submit">
</form>
</body>
<script>
    $("#img").click(function () {
// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
// src 属性表示验证码 img 标签的 图片路径。它可读，可写
// alert(this.src);
        <%--            this.src="${pageContext.request.contextPath}/kaptcha.jpg";--%>
//             this.src=this.src;
        this.src="${basePath}kaptcha.jpg?d="+new Date();
    });
    $("#submit").click(function(){
        var img = $("#imgstr").val();
        $.ajax({
            type: 'get',
            url:'/MyTest/regist',
            data:{'img':img},
            success:function (data){
                if(data==='defild'){
                    alert("验证码错误");
                }
            }
        })
    })

</script>
</html>
