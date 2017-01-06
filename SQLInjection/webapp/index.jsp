<%--
  Created by IntelliJ IDEA.
  User: Michlu
  Date: 2017-01-06
  Time: 00:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logowanie do bazy</title>
</head>
<body>
<h1>Zaloguj się</h1>
<form action="InputServlet" method="post">
    <input type="text" placeHolder="Username" name="username">
    <br>
    <input type="password" placeHolder="Password" name="password">
    <br>
    <input type="submit" value="Zaloguj">
</form>
</body>
</html>
