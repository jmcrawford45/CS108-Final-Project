<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Home</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">

</head>
<body>  


<%
User defUser = (User)request.getSession().getAttribute("user");//correct//
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");
defUser = TableAbstraction.getUser(defUser.getDisplayName(), con);
if(!defUser.getAdminStatus()){
	request.setAttribute("title", "Permission Error");
	request.setAttribute("message", "Only admins can view this page!!!");
    request.getRequestDispatcher("Error.jsp").forward(request, response);
}
tableabstraction.Stats stats = TableAbstraction.getStats(con);
int quizzes = stats.getQuizzes();
int users = stats.getUsers();
%>
<h1>Quizzes taken: <%=quizzes %></h1>
<h1>Site users: <%=users %></h1>
<form action = "Announce.jsp" method="post">
		<input type = "submit" value = "Create Announcement" class="button"/>    
</form> 

<form action = "SearchProfiles.jsp" method="post">
		<input type = "submit" value = "Promote / Remove Users" class="button"/>    
</form> 
<form action = "EditQuizzes.jsp" method="post">

		<input type = "submit" value = "Edit Quizzes" class="button"/>      
</form>   
<form action = "SearchQuizzes.jsp" method="post">
		<input type = "submit" value = "Quizzes!" class="button"/>      
</form>  
<form action = "Logout" method="post">
		<input type = "submit" value = "Logout" class="button"/>      
</form> 
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>