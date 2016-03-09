<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>Add Friends</title>
</head>
<body>

<%
String to = request.getParameter("to");
if(to == null) to = "";
User defUser = (User)request.getSession().getAttribute("user");//correct//
String from = defUser.getDisplayName();

%>

<form action="SendFriendRequest" method="post">  
<p>Add: <input type="text" name="toAdd" value = "<%=to%>"/></p>    
<%-- <input type = "hidden" name="fromUser" value = "<%= from%>">  
<p>Body:<br> <input type="text" name="message"/> --%>
<input type="submit" value = "Add Friend" />
</form>


<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>