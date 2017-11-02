<%@page import="java.util.ArrayList" %>
<%@page import="composite_task.bean.EmployeeBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
ArrayList<EmployeeBean> employeeList = new ArrayList<>();
if (request.getAttribute("EMPLOYEES") != null) {
	employeeList = (ArrayList<EmployeeBean>) request.getAttribute("EMPLOYEES");
}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Employees List</title>
	<link rel="stylesheet" type="text/css" href="styles.css"/>
</head>

<body>
<center>
	<h1> Employees List </h1>
	<table style="width:50%"border = 2> 
		<tr>
		 <th> Employee Name</th>
		 <th> Employee ID</th>
		 <th> Phone </th>
		 <th> Supervisors </th>
		</tr>
		<%
		
			for (EmployeeBean employee : employeeList) {
				Integer employee_id = employee.getIdemployee();
				String name = employee.getName();
				String phone = employee.getPhone_number();
				String supervisors = employee.getSupervisors();

		%> 
		<tr>
		 <td><%=name %></td>
		 <td><%=employee_id %></td>
		 <td><%=phone %></td>
		 <td><%=supervisors %></td>
		</tr>
		 <% 
			}
		 %>
	</table>
	
	<table>
		<tr>
			<td> 
				<a href="/composite_task/CompanyManagementServlet?whatsend=employeeInsert" target = "_top"> Go back </a>
			</td>
		</tr>
		<tr>
			<td> 
				<a href="/composite_task/CompanyManagementServlet?whatsend=employee" target = "_top"> Insert Another Employee </a>
			</td>
		</tr>
	</table>
	</center>
</body>
</html>