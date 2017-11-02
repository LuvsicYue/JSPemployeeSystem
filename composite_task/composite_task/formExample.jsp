<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Form Example</title>
	</head>
<body>
	<form action="/composite_task/FirstServlet" method = "post">
		name:<input type="text" value="" name="name"/>
		<input type = "submit" value = "POST SUBMIT">
	</form>
	<form action="/composite_task/FirstServlet" method = "get">
		idemployee:<input type="text" value="" name="idemployee"/>
		<input type = "submit" value = "GET SUBMIT">
	</form>
</body>
</html>