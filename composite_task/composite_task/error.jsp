<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invalid Input</title>
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
		<center>
		Invalid input for Employee ID, Please enter only number(-2147483648 ~ 2147483647) for Employee ID
		
			<table>
				<tr>
					<td> 
						<a href="/composite_task/CompanyManagementServlet?whatsend=employeeInsert" target = "_top"> Enter Employee </a>
					</td>
				</tr>
			</table>
		</center>
	</div>
</body>
</html>