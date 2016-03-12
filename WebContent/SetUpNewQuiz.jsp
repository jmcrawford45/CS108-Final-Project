<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>Create a Quiz</title>
</head>
<body>
<h1>Create a Quiz</h1>

<form action= "FormNewQuiz" method="post">
<h2>Name</h2><input type="text" name="name" value=""style="width:100px; height:10px;"><br>
<h2>Description</h2><input type="text" name="description" value=""style="width:500px; height:10px;"><br>
<h2>Category (Choose One)</h2>
<%
	for (int i = 0; i < QuizManager.CATEGORIES.length; i ++){
		String cat = "<input type='radio' name='category_id' value=" + i + ">"
				+ QuizManager.CATEGORIES[i] + "<br>";
		out.print(cat);
	}
%>
<h2>Options</h2>
<h4>Question Ordering</h4>
<input type="radio" name="random" value="ordered">Keep Questions in order<br>
<input type="radio" name="random" value="random">Randomize Order<br>
<h4>Number of Pages</h4>
<input type="radio" name="pages" value="multiple">Display questions on multiple pages<br>
<input type="radio" name="pages" value="single">Display questions on single page<br>
<h4>Feedback Response Period</h4>
<input type="radio" name="immediate" value="immediate">For multiple page quizzes, check answers immediately<br>
<input type="radio" name="immediate" value="end">Or, only give feedback when quiz is completed<br>
<h4>Available Test Modes</h4>
<input type="radio" name="practice" value="practice">Quiz can be taken in practice mode<br>
<input type="radio" name="practice" value="nopractice">Quiz cannot be taken in practice mode<br><br>
<input type = "submit" value = "Create Questions" class="button"/>
</form>
</body>
</html>