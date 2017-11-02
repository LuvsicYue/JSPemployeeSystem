<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="animate.css">
	<link rel="stylesheet" href="style.css">

</head>
<body>
	<div class="container">
		<form action="LoginServlet" method="post">
			<div class="top">
				<h1 id="title" class="hidden"><span id="logo">Employee<span> Management</span></span></h1>
			</div>
			<div class="login-box animated fadeInUp">
				<div class="box-header">
					<h2>Log In</h2>
				</div>
				<label for="username">Username</label>
				<br/>
				<input type="text" name="username">
				<br/>
				<label for="password">Password</label>
				<br/>
				<input type="password" name="userpass">
				<br/>
				<p style="color:red">Invalid Username or Password</p>
				<button type="submit">Sign In</button>
				<br/>
			</div>
		</form>
	</div>
</body>
</html>