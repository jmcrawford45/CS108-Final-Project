<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Challenge message</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
</head>
<body>


<%
String body = request.getParameter("body");
String from = request.getParameter("from");
String link = request.getParameter("link");

%>

<%= body %><br><br>

<a href="GoToQuiz.jsp?to=<%=link%>"> Accept Challenge!</a>
<form action = "HomePage.jsp" method="post">
		<input type = "submit" value = "Home" class="button"/> 
</body>
</html>
