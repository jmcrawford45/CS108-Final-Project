<%@page import="quiz.*"%>
<%@page import="question.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>Question Results</title>
</head>
<body>

<%
	Quiz quiz = (Quiz)request.getSession().getAttribute("quiztaken");
	int i  = Integer.parseInt(request.getParameter("index"));
	Question q = quiz.getQuestionbyIndex(i);
	String ans = request.getParameter("input" + i);
	Answer a = new Answer(ans);
	ArrayList<Answer> input = (ArrayList<Answer>)request.getSession().getAttribute("answers");
	input.add(a);
	request.getSession().setAttribute("answers", input);
	String result = "";
	if(q.isCorrectAnswer(a)){
		result = "CORRECT";
	} else {
		result = "INCORRECT";
	}
%>
<h2>Your response was <%=result%></h2>
<h3>Question</h3>
<p><%=request.getParameter("question") %></p>
<h3>Your Answer</h3>
<p><%=ans%></p>
<h3>Correct Answer</h3>
<p><%=q.answersToString()%></p>
<%
	if (i == (quiz.getNumQuestions() - 1)) {
		%>
		<form action="GradeQuiz" method="post">
		<input type="hidden" name="start" value="<%=request.getParameter("start")%>">
		</form>
		
		<%
	}
%>
</body>
</html>