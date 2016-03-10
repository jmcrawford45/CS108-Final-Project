<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	Quiz q = (Quiz)request.getSession().getAttribute("newquiz");
%>
<title>Create a Quiz</title>
</head>
<body>
<h1>Create a Quiz</h1>

<form action= "CreateQuestions.jsp" action="post">
<h2>Name</h2><input type="text" name="name" value=""style="width:100px; height:10px;"><br>
<h2>Description</h2><input type="text" name="description" value=""style="width:500px; height:10px;"><br>
<h2>Options</h2>
Randomize question order<input type="checkbox" name="random" value="random"><br><br>
Display questions on multiple pages<input type="checkbox" name="pages" value="multiple"><br>
If questions are displayed on multiple pages, give answer feedback immediately <input type="checkbox" name="immediate" value="immediate"><br><br>
Allow quiz to be taken in practice mode<input type="checkbox" name="practice" value="practice"><br><br>
<input type = "submit" value = "Create Questions" class="button"/>
</form>
</body>
</html>