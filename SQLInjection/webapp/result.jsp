<%--
  Created by IntelliJ IDEA.
  User: Michlu
  Date: 2017-01-06
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Witaj <%= session.getAttribute("username") %></h1>
<h2>Twoje uprawnienia: <%= session.getAttribute("privigiles") %></h2>
</body>
</html>
