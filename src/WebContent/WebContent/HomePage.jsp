




<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">

</head>
<body>  


<%
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");

String admin = (defUser.getAdminStatus()) ?  "" : "hidden = \"hidden\"";
if(defUser.messageCount() != 0){
	RequestDispatcher dispatch = request.getRequestDispatcher("HomePageHasMessage.jsp");  
	dispatch.forward(request, response); 
}
tableabstraction.Stats stats = TableAbstraction.getStats(con);
%>
<%=defUser.getDisplayName() %>
<h1> Announcements</h1>  
<%
ArrayList<String> announcements = stats.getMsgs();
for(int i = 0; i < announcements.size(); i++){
	String msg = announcements.get(i);%>
	<%=msg %><br> <%
} 
%>
<h1> Popular Quizzes</h1>

<h1> Recent Quizzes</h1>

<% 
if(defUser.getQuizzes().size() != 0){
	%>  <h1> Quiz History</h1>  <%
}
%>
<!-- <h1> Quiz History</h1>   -->
<%
ArrayList<String> quizHistory = defUser.getQuizzes();
for(int i = 0; i < quizHistory.size(); i++){
	String quiz = quizHistory.get(i);%>
	<%=quiz %><br> <%
} 
%>

<% 
if(defUser.getAuthoredQuizzes().size() != 0){
	%>  <h1> Authored Quizzes</h1>   <%
}
%>
<!-- <h1> Authored Quizzes</h1>  -->   


<% 
if(defUser.getActivityLog().size() != 0){
	%>  <h1> Friend Activity</h1>   <%
}
%>
<!-- <h1> Friend Activity</h1> -->





<form action = "DisplaySelf.jsp?user=<%=defUser.getDisplayName() %>" method="post">
		<input type = "submit" value = "My Profile" class="button"/>    
</form> 

<form action = "EditProfile.jsp" method="post">
		<input type = "submit" value = "Edit Profile" class="button"/>    
</form> 
<form action = "DisplayInbox.jsp" method="post">

		<input type = "submit" value = "Inbox" class="button"/>      
</form>   
<form action = "ViewFriends.jsp" method="post">
		<input type = "submit" value = "Friends" class="button"/>      
</form>  

<form action = "AddFriends.jsp" method="post">
		<input type = "submit" value = "Add Friends" class="button"/>      
</form>  

<form action = "SearchProfiles.jsp" method="post">
		<input type = "submit" value = "Search" class="button"/>      
</form>  
<form action = "QuizList.jsp" method="post">
		<input type = "submit" value = "Quizzes!" class="button"/>      
</form>  
<form action = "AdminPage.jsp" method="post">
		<input type = "submit" <%= admin%> value = "Admin" class="button"/>      
</form> 
<form action = "Logout" method="post">
		<input type = "submit" value = "Logout" class="button"/>      
</form> 


</body>
</html>