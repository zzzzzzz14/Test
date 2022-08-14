<%--
  Created by IntelliJ IDEA.
  User: zhang ya ning
  Date: 2022/6/28
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div><img src="/Final_project/static/img/${food.menu}/${food.id}.png"></div>
    <h1>${food.price}元/每份</h1>
    <h1>&nbsp;&nbsp;${food.info}</h1>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/info.css">
</body>
</html>
