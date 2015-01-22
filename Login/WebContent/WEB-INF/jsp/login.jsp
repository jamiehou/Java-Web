<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<style>
.errorMsg {
    color: red;
}
</style>
<script type="text/javascript">
function login() {
  var loginFormObj = document.getElementById("loginForm");
  
  if (validate()) {
	  loginFormObj.submit();
  }
}

function validate() {
  var username = document.getElementById("userName").value;
  var password = document.getElementById("password").value;
  var isValid = true;
  
  if (!username) {
	  document.getElementById("errorUsername").innerHTML = "User name is required!";
    isValid = false;
  } else {
	  document.getElementById("errorUsername").innerHTML = "";
  }
  
  if (!password) {
	  document.getElementById("errorPassword").innerHTML = "Password is required!";
    isValid = false;
  } else {
	  document.getElementById("errorPassword").innerHTML = "";
  } 
	return isValid;
}
</script>
<body>
  <form action="login" method="post" id="loginForm">
    <% Map<String, String> errorMsg = (HashMap<String, String>)session.getAttribute("ERROR");
    if (errorMsg == null) {
        errorMsg = new HashMap<String, String>();
    }
    %>
    Username:<input type="text" id="userName" name="userName"/>
    <label id="errorUsername" class="errorMsg"></label>
    <label class="errorMsg"><% String errorUsername = errorMsg.get("username") == null ? "" : errorMsg.get("username");
    out.print(errorUsername);
    %></label><br/>
    Password:<input type="password" id="password" name="password"/>
    <label id="errorPassword" class="errorMsg"></label>
    <lable class="errorMsg"><% String errorPassword = errorMsg.get("password") == null ? "" : errorMsg.get("password");
    out.print(errorPassword); 
    %></lable><br/>
    <input type="button" value="Login" onclick="login()"/>
  </form>
</body>
</html>