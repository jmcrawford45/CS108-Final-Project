<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compose Text Message</title>
</head>
<body>

<%
User defUser = (User)request.getServletContext().getAttribute("defaultUser");
String from = defUser.getDisplayName();
String to;
if(request.getParameter("to") == null){
	to = "";
} else {
	to = request.getParameter("to");
}

System.out.print(to);
%>

<form action="SendMessage" method="post">  
<p>To: <input type="text" name="toUser" value = "<%=to%>"/></p>    
<input type = "hidden" name="fromUser" value = "<%= from%>">  
<p>Body:<br> <input type="text" name="message"/>
<input type="submit" value = "Send Message" /></p>
</form>

</body>
</html>