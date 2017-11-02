<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Application</title>
<link rel="stylesheet" type="text/css" href="styles.css"/>
<style>

        html, body {
            height: 70%;
            margin: 0;
            padding: 0;
            width: 100%;
        }

        body {
            display: table;
        }

        .my-block {
            text-align: center;
            display: table-cell;
            vertical-align: middle;
        }
        </style>
</head>
</head>
<body>
	<div class="my-block">
	<form action="LoginServlet" method="post">
	<h1 align="center">Employee Management System</h1>
	<center>
		<fieldset style="width: 300px">
		
			<legend> Login</legend>
				
				<table><!-- <tr><td align="center" valign="top" border = 1> -->
					
					<tr>
						<td> Username</td>
						<td><input type="text" name="username" required="required"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="userpass" required="required"/></td>
					</tr>
					<tr>
					
						<td><input type="submit" value="Login"/></td>
					
					</tr>
					
				</table>

		</fieldset>
		</center>
	</form>
	</div>
</body>
</html>