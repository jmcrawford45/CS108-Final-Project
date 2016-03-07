<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@page import="setUp.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display User</title>
<style type="text/css">
body{
	background-color: rgb(51, 51, 153)
}
.name{
/*	text-align: center;
*/	text-indent: 65px;
	color: white;
}
.bio{
	color: white;
}
.quizH{
	color: white;
}
.friends{
	color: white;
}
/*.addFriend{
	color: white;
}*/
.Button{

	background: #00FFFF;   
	font-size: 20px;
	border-radius: 12px;  
	transition: all 2s;
	cursor: pointer;
	margin: 7px;
	text-align: center;
}
.Button:hover {
	background-color: #FF0000;
	padding-right: = 25px;
	
}

</style>
</head>
<body>

<%
String toDisplay = request.getParameter("user");  
System.out.print("USER IS" + toDisplay);
AccountManager accounts = (AccountManager)request.getServletContext().getAttribute("manager");
User user = (User)accounts.getAccount(request.getParameter("user"));  
String name = user.getDisplayName();
String bio = user.getBio();
String imageStr = user.getImage();
ArrayList<String> quizzes = user.getQuizzes();
ArrayList<String> friends = user.getFriends(); 
%>
<img src= "<%=imageStr%>"/>
<p class = "name"> <%=name %> <br></p>

<%-- <form action = "AddFriend" method="post">
		<input type = "hidden" name="toAdd" value = "<%=name %>">  
		<input type = "submit" value = "Add Friend" class="addFriend"/>  
</form>   --%>
<form action = "SendFriendRequest" method="post">
		<input type = "hidden" name="toAdd" value = "<%=name %>">  
		<input type = "submit" value = "Add Friend" class="Button"/>  
</form>  
<form action = "SendChallenge.jsp" method="post">
		<input type = "hidden" name="toChallenge" value = "<%=name %>">  
		<input type = "submit" value = "Challenge" class="Button"/>  
</form>  

<form action = "ComposeTextMessage.jsp" method="post">
		<input type = "hidden" name="to" value = "<%=name %>">  
		<input type = "submit" value = "Send Message" class="Button"/>  
</form>  

<p class = "bio"> About me  <br> <%=bio %> <br></p>
<p class = "quizH"> Top Quizzes <br>  
<% 
for(int i = 0; i < quizzes.size(); i++){   
	String quiz = quizzes.get(i);
	%>
	<%= quiz %><br>  
<%	
}
%>
</p>

<p class = "friends"> Friends <br>
<%
for(int i = 0; i < friends.size(); i++){
	String friend = friends.get(i);
	%>
	<a href="DisplayUser.jsp?user=<%=friend%>" class = "friends"> <%= friend%></a><br> 
<%
}
%>
</p>



</body>
</html>







