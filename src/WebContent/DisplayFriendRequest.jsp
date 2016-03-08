<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DisplayFriendRequest</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">

</head>
<body>


<%
String body = request.getParameter("body");
String from = request.getParameter("from");
String index = request.getParameter("index");


%>

<%= body %><br><br>

<form action="AddFriend" method="post">     
<input type = "hidden" name="toAdd" value = "<%= from%>">    
<input type="submit" value = "Add Friend" />
</form>

<form action="DeleteMessage" method="post"> 
<input type="hidden" name = "index" value = "<%=index %>">    
<input type="submit" value = "Decline Request" />
</form>


<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>