<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>  
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
</head>
<body>

<p><b> Create New Account</b></p>
<p> Please enter proposed name and password </p>
<form action="AccountServlet" method="post">  
<p>Name: <input type="text" name="name" /></p>    

<p>Password: <input type="password" name="password"/>
<input type="submit" value = "Create Account" /></p>
</form>

</body>
</html>