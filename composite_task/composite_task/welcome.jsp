<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="Content-Type" content="width=1024, initial-scale=1; charset=UTF-8"> -->
<title>Welcome</title>
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
<body>
	<div class="my-block">
		<h1 align="center">Welcome to Employee Management System!</h1>
		<h4 align="center">
		Hello,
		<%=session.getAttribute("name") %>
		</h4>

		<div style="text-align:center">  
		<a href="/composite_task/CompanyManagementServlet?whatsend=homepage" style="text-align:center" target = "_top" > Enter Employee </a>
		</div>
	</div>

</body>
</html>