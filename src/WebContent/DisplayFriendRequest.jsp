<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DisplayFriendRequest</title>
</head>
<body>


<%
String body = request.getParameter("body");
String from = request.getParameter("from");

%>

<%= body %><br><br>

<form action="AddFriend" method="post">     
<input type = "hidden" name="toAdd" value = "<%= from%>">    
<input type="submit" value = "Add Friend" />
</form>

</body>
</html>