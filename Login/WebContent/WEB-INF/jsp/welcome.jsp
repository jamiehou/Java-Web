<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ioryz.login.model.User" %>
<%@ page import="com.ioryz.login.Constants" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<% User user = (User)session.getAttribute(Constants.USER); %>
    Welcome, <%= user.getUserName() %> !
</body>
</html>