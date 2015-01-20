<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<script type="text/javascript">
function login() {
  var loginFormObj = document.getElementById("loginForm");
  var username = document.getElementById("userName").value;
  var password = document.getElementById("password").value;
  
  if (username == null || username == "") {
    alert("User name is required!");
    return;
  }
  
  if (password == null || password == "") {
    alert("Password is required!");
    return;
  }
  
  loginFormObj.submit();
}
</script>
<body>
  <form action="login" method="post" id="loginForm">
    <% String errorMsg = (String)session.getAttribute("ERROR_MSG"); 
    if (errorMsg != null) {
        out.println(errorMsg + "</br>");
    }
    %>
    Username:<input type="text" id="userName" name="userName"/><br/>
    Password:<input type="password" id="password" name="password"/><br/>
    <input type="button" value="Login" onclick="login()"/>
  </form>
</body>
</html>