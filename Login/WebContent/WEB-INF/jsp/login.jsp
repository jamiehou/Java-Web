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
  loginFormObj.submit();
}
</script>
<body>
  <form action="login" method="post" id="loginForm">
    Username:<input type="text" id="userName" name="userName"/><br/>
    Password:<input type="password" id="password" name="password"/><br/>
    <input type="button" value="Login" onclick="login()"/>
  </form>
</body>
</html>