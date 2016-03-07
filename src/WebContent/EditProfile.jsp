<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile</title>
</head>
<body>

<form action="EditProfile" method="post">  
<p>Image: <input type="text" name="newImage"/>
<input type="hidden" value = "image" name = "type"/>    
<input type="submit" value = "Set Profile Image" /></p>
</form>

<form action="EditProfile" method="post">  
<p>Status:  <input type="text" name="newStatus"/>
<input type="hidden" value = "status" name = "type"/>
<input type="submit" value = "Set Status" /></p>
</form>

<form action="EditProfile" method="post">  
<p>About Me: <input type="text" name="newBio"/>
<input type="hidden" value = "bio" name = "type"/>
<input type="submit" value = "Set Bio" /></p>  
</form>

</body>
</html>