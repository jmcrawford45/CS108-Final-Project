<%@page import="quiz.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	Performance p = (Performance) request.getAttribute("performance");
	
%>
<title>Results</title>
</head>
<body>
<p class = "score"> Score: <%=p.score%>% </p>
<p class = "time"> Time: <%=p.time%>ms<br></p>
</body>
</html>