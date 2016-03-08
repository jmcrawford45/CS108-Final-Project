<%@page import="quiz.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	QuizManager qm = new QuizManager(DBConnection.connect());
	Quiz q = qm.getQuizByID(1111);
	
	ArrayList<Performance> userscores = qm.getUserQuizPerformances(1111, 2222);
	ArrayList<Performance> bestscores = qm.getQuizBestPerformances(1111);
	ArrayList<Performance> bestrecentscores = qm.getQuizBestRecentPerformances(1111);
	ArrayList<Performance> recentscores = qm.getQuizRecentPerformances(1111);
%>
<title><%=q.name%> Summary</title>
</head>
<body>
<p class = "name"> <%=q.name%> <br></p>
<p class = "description"> Description  <br> <%=q.description%> <br></p>
<p class = "creator"> Creator  <br> <a href="DisplayUser.jsp?user=<%=q.creator_id%>" class = "creator"> <%=q.creator_id%></a> <br></p>
<p class = "userquizscores"> Your Past Performance on This Quiz <br>  
<% 
for(int i = 0; i < userscores.size(); i++){   
	Performance p = userscores.get(i);
	Date d = new Date(p.start);
	%>
	You scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
<p class = "bestscores"> Highest Performers of All Time<br>  
<% 
for(int i = 0; i < bestscores.size(); i++){   
	Performance p = bestscores.get(i);
	Date d = new Date(p.start);
	%>
	<%=p.user_id%> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
<p class = "bestrecentscores"> Top Performers in the Last Day<br>  
<% 
for(int i = 0; i < bestrecentscores.size(); i++){   
	Performance p = bestrecentscores.get(i);
	Date d = new Date(p.start);
	%>
	<%=p.user_id%> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
<p class = "recentquizscores"> Recent Performances <br>  
<% 
for(int i = 0; i < recentscores.size(); i++){   
	Performance p = recentscores.get(i);
	Date d = new Date(p.start);
	%>
	<%=p.user_id%> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
</body>
</html>