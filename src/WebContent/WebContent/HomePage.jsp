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
User defUser = (User)request.getSession().getAttribute("user");//correct//
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");
String admin = (defUser.getAdminStatus()) ?  "" : "hidden = \"hidden\"";
defUser = TableAbstraction.getUser(defUser.getDisplayName(), con);
if(defUser.getMessageCount() != 0){
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

<%-- <% 
for(int i = 0; i < defUser.getFriends().size(); i++){
	User friend = manager.getAccount(defUser.getFriends().get(i));%>
	<%=friend.getActivityLog().get(0) %>
<% 	 
} --%>



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
<form action = "ViewQuizzes.jsp" method="post">
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