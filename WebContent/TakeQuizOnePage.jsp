<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	QuizManager qm = new QuizManager(DBConnection.connect());
	int qid = Integer.parseInt(request.getAttribute("quizid").toString());
	Quiz q = qm.getQuizByID(qid);
%>
<title><%=q.name%></title>
</head>
<body>
<p class = "name"> <%=q.name%> <br></p>
<form action = "GradeQuiz" method="post">
<input type = "hidden" name="quizid" value = "<%=q.id%>">    
<input type = "hidden" name="userid" value = "<%=2222%>"> 
<input type = "submit" value = "Grade Quiz" class="button"/>
</form>
</body>
</html>