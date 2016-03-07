<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@page import="setUp.AccountManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<title>View Friends</title>
.friends{
	color: white;
}
</style>
</head>
<body>

<% AccountManager accounts = (AccountManager)request.getServletContext().getAttribute("manager");
User defUser = (User)request.getServletContext().getAttribute("defaultUser");
System.out.print(defUser.getDisplayName());
ArrayList<String> friends = defUser.getFriends(); 
%>

<p class = "friends"> Friends <br>
<%
for(int i = 0; i < friends.size(); i++){
	String friend = friends.get(i);
	%>
	<a href="DisplayUser.jsp?user=<%=friend%>" class = "friends"> <%= friend%></a><br> 
<%
}
%>
</p>  

</body>
</html>