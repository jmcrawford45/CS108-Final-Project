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
<%
	QuizManager qm = new QuizManager(DBConnection.connect());
	Quiz quiz = (Quiz)request.getAttribute("quiz");
	ArrayList<Question> qs = Test.questions;
	//ArrayList<Answer> as = new ArrayList<Answer>();
	String s = request.getAttribute("start").toString();
%>
<title><%=quiz.name%></title>
</head>
<body>
<p class = "name"> <%=quiz.name%> <br></p>

<form action = "GradeQuiz" method="post">
<ul>
    <%  	
            for (int i = 0; i < qs.size(); i ++) {
            	Question q = qs.get(i);
            	String answer = "";
                String item = "<li>" + q.getQuestion() + "<input type ='text' value='" + answer 
                		+ "' name='answer" + i + "'> <br></li>";
                out.print(item);
            }
        %>
        </ul>
<input type = "hidden" name="quizid" value = "<%=quiz.id%>"> 
<input type = "hidden" name="start" value = "<%=s%>">   
<input type = "hidden" name="userid" value = "<%=2222%>"> 
<input type = "submit" value = "Grade Quiz" class="button"/>
</form>
</body>
</html>