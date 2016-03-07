<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<style type="text/css"> 
body{
	background-color: rgb(140,120,250);

}  
 .button {  

	background: #00FFFF;   
	font-size: 20px;
	border-radius: 12px;  
	transition: all 2s;
	cursor: pointer;  
	margin: 7px;
	text-align: center;
}
.button:hover {
	background-color: #FF0000;
	padding-right: = 25px;
	
} 
</style>
</head>
<body>


<%
User defUser = (User)request.getServletContext().getAttribute("defaultUser");
System.out.print(defUser.getDisplayName());  

%>
<%-- <img src= "<%=imageStr%>"/> --%>
<h1> Announcements</h1>  
<h1> Popular Quizzes</h1>
<h1> Recent Quizzes</h1>
<h1> Quiz History</h1>
<%
ArrayList<String> quizHistory = defUser.getQuizzes();
for(int i = 0; i < quizHistory.size(); i++){
	String quiz = quizHistory.get(i);%>
	<%=quiz %><br> <%
}

%>
<h1> Authored Quizzes</h1>    

<h1> Friend Activity</h1>

<form action = "EditProfile.jsp" method="post">
		<%-- <input type = "hidden" name="toAdd" value = "<%=name %>">  --%> 
		<input type = "submit" value = "My Profile" class="button"/>    
</form> 
<form action = "DisplayInbox.jsp" method="post">
		<%-- <input type = "hidden" name="toAdd" value = "<%=name %>">  --%> 
		<input type = "submit" value = "Inbox" class="button"/>      
</form>   
<form action = "ViewFriends.jsp" method="post">
		<input type = "submit" value = "Friends" class="button"/>      
</form>  



</body>
</html>