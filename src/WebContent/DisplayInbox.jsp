<%@page import="messages.ChallengeMessage"%>
<%@page import="messages.Message"%>
<%@page import="user.User"%>
<%@page import="setUp.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Inbox</title>
<style>
ul{
    list-style-type: none;
}
.linkB{
     background: none;
     border: none;
     color: #0065ff;
     text-decoration: underline;
     cursor: pointer;     
}

</style>          
</head>
<body>

My Inbox     

<% 
AccountManager accounts = (AccountManager)request.getServletContext().getAttribute("manager");
		User defUser = (User)request.getServletContext().getAttribute("defaultUser");
		java.util.ArrayList<messages.Message> inbox = defUser.getMessages();
		for(int i = 0; i < inbox.size(); i++){
			Message msg = inbox.get(i);    
			String type = msg.getType();
			String link = "";
			if(type == "ChallengeMessage"){
				ChallengeMessage msgCh = (ChallengeMessage)msg;
				link = msgCh.getLink();    
			}
			System.out.print(type);
			%>
			<form action = "DistinguishMessages" method="post">
				<input type = "hidden" name="type" value = "<%=type%>">
				<input type = "hidden" name="link" value = "<%=link%>">
				<input type = "hidden" name = "from" value = "<%=msg.getFrom() %>"/>
				<input type = "hidden" name = "string" value = "<%=msg.toString() %>"/> 
				<input type = "submit" value = <%= msg.getFrom() %> class = "linkB"/>
			</form>
	
			
		<%}%>
		<% 
		
%>
<a href= "ComposeTextMessage.jsp"> Compose New Message</a>    
</body>
</html>