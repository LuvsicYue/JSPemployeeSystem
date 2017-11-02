<%@page import="composite_task.bean.EmployeeBean" %>
<%@page import="composite_task.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%
Integer employee_id = 0;
String name = "";
String phone_number = "";
String supervisors = "";
//fields from session if actually present
EmployeeBean employee = new EmployeeBean();
boolean employeeFilled = false;

if (request.getSession() != null && request.getSession().getAttribute("EMPLOYEE") != null) {
	employee = (EmployeeBean) request.getSession().getAttribute("EMPLOYEE");
	employee_id = employee.getEmployee_id();
	name = employee.getName() + "";
	phone_number = employee.getPhone_number()+"";
	supervisors = employee.getSupervisors()+"";
	
	employeeFilled = true;
}
/* CompanyBean company = new CompanyBean(); */
/* if (request.getSession() != null && request.getSession().getAttribute("COMPANY") != null) {
	company = (CompanyBean) request.getSession().getAttribute("COMPANY");
	fK_company = company.getIdcompany()+"";
}*/
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Form</title>
<link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body >
<center>
<table >

	<tr>
		<td> 
			<!-- <a href="/composite_task/CompanyManagementServlet?whatsend=homepage" target = "_top"> Home Page </a> -->
			<a href="/composite_task/welcome.jsp" target = "_top"> Home Page </a>
			&nbsp;
<!-- 			<a href="/composite_task/CompanyManagementServlet?whatsend=company" target = "_top"> Company </a>
			&nbsp; -->
		<% if (employeeFilled) { %>
			<a href="/composite_task/CompanyManagementServlet?whatsend=employee" target = "_top"> Insert another employee </a>
		<% } %>
			&nbsp;
			<a href="/composite_task/CompanyManagementServlet?whatsend=lookEmployee" target = "_top"> Employee List </a>
		</td>
	</tr>

	<tr>
		<td>
			<form name="employeeForm" action="/composite_task/CompanyManagementServlet" method="post">
				<table style="width:300px" border = 2>
				
					<tr>
					 <td style="width:50%" > Employee ID (*):</td>
					 <td style="width:50%">
					 	<% if (employeeFilled) { %>
					 		<%= employee_id %>
					 	<%} else { %>
					 		<input name="employee_id" value="<%=employee_id%>" type= "text">
					 	<% } %>
					 </td>
					</tr>
					<tr>
					 <td> Name (*):</td>
					 <td>
					 	<% if (employeeFilled) { %>
					 		<%= name %>
					 	<%} else { %>
					 		<input name="name" value="<%=name %>" type="text" maxlength="45">
					 	<% } %>
					 </td>
					</tr>
					<tr>
					 <td> Phone Number(*):</td>
					 <td>
					 <% if (employeeFilled) { %>
					 		<%= phone_number %>
					 	<%} else { %>
					 	<input name="phone_number" value="<%=phone_number %>" type="text" maxlength = "45">
					 <% } %>
					 </td>
					</tr>
					<tr>
					 <td> Supervisors (*):</td>
					 <td>
					 	<% if (employeeFilled) { %>
					 		<%= supervisors %>
					 	<%} else { %>
					 	<input name = "supervisors" value = "<%=supervisors %>" type = "text" maxlength = "45">
					 	<% } %>
					 </td>
					</tr>
					<tr>
					 <td colspan = "2"> (*) Mandatory field</td>
					</tr>
					
				</table>
				 
				 <input name = "whatsend" value = "employee" type = "hidden">
				 <input type = "submit" value = "Insert Employee">
			</form>
			<form action="/composite_task/CompanyManagementServlet" method="post">
				<input name = "whatsend" value = "saveEmployee" type = "hidden">
				<input type = "submit" value = "Save Employee on DB">
			</form>
			<form action="/composite_task/CompanyManagementServlet" method = "post">
				<table border = 1>
					<tr>
					 <td> Delete Employee ID:</td>
					 <td>
					 		<input name="employee_id" value="" type= "text">
					 </td>
					</tr>
				</table>
						<input name = "whatsend" value = "deleteEmployee" type = "hidden">
						<input type = "submit" value = "Delete Employee on DB">
			</form>
			
		</td>
		</tr>
</table> 
</center>
					 	



</body>
</html>