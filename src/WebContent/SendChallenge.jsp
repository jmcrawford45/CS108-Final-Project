<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Challenge </title>
</head>
<body>
<%
String to = request.getParameter("toChallenge");
System.out.print("Challenge: " + to);
User defUser = (User)request.getServletContext().getAttribute("defaultUser");
String from = defUser.getDisplayName();
%>

<form action="SendChallengeMessage" method="post">  
<p>Challenge Quiz:  <input type="text" name="link" /></p>   
<input type = "hidden" name="fromUser" value = "<%= from%>">    
<input type = "hidden" name="toUser" value = "<%= to%>">  
<input type="submit" value = "Challenge!" />
</form>


</body>
</html>