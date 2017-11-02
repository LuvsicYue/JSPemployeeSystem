<%@ page import="java.util.*"%>
<%@ page import="composite_apps.bean.EmployeeBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Management System</title>
<!-- <link href="button.css" type="text/css" rel="stylesheet" /> -->
<link rel="stylesheet" type="text/css" href="searchBox.css" />

<script language="javascript">

function deleteRecord(id){
    window.location.href="deleteUserServlet?id=" + id; 
  }
  
function myFunction() {
	  // Declare variables 
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}
</script>

</head>
<body background="images/photo_bg.jpg">
 <br>
<table align="center">
</table>
<br>
<center>
    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search names">
</center>
<table id="myTable" width="600px"  align="center" style="background-color:#EDF6EA;border:1px solid #000000;">
 <th colspan="6" style="color:red">Invalid Employee ID, please enter only number(0 ~ 2147483647).</th>
 <tr><td colspan=6 align="center" height="2px"></td></tr>

    
    
    <tr><td colspan=6 align="center" height="2px"></td></tr>
  <tr style="background-color:#7BA88B;font-weight:bold;">
     <th>No.</th><th>Employee Name</th><th>Employee ID</th>
	 <th>Phone Number</th><th>Supervisors</th>
	 <th>Edit</th>
  </tr>
	<%
	String bgcolor="";
	int count=0;
	//List viewList = new ArrayList();
	Iterator  viewItr;
	ArrayList<EmployeeBean> employeeList = new ArrayList<>();
	if(request.getAttribute("EMPLOYEES")!=null)
	{
		employeeList =  (ArrayList<EmployeeBean>)request.getAttribute("EMPLOYEES");
		Iterator itr = employeeList.iterator(); 
		
		while(itr.hasNext())
		{
			
			if(count%2==0)
			{
			 bgcolor = "#C8E2D1";
			}
			else
			{
				
				bgcolor = "#EAF8EF";
			}
			
			//viewList = (ArrayList)itr.next();
			EmployeeBean employee = (EmployeeBean)itr.next();
			//int id = Integer.parseInt(viewList.get(0).toString());
			Integer employee_id = employee.getEmployee_id();
			String name = employee.getName();
			String phone = employee.getPhone_number();
			String supervisors = employee.getSupervisors();
			
			//viewItr = viewList.iterator();
			%>
			<tr style="background-color:<%=bgcolor%>;">
			<td align="center"><%=++count %></td>
			<td align="center"><%=name %></td>
		 	<td align="center"><%=employee_id %></td>
		 	<td align="center"><%=phone %></td>
		 	<td align="center"><%=supervisors %></td>
		 	
			<td align="center"><input type="button" name="delete" style="background-color:#ff0000;font-weight:bold;;color:#ffffff;" value="Delete" onclick="deleteRecord(<%=employee_id%>);"></td>
			</tr>
			
		<% } %>	
			<form action="/composite_apps/CompanyManagementServlet" method="post">
			<tr style="background-color:#C8E2D1">
				<td align="center"><%=++count %></td>
				<td align="center"><input name="name" value="" type= "text" placeholder="Given Name/Surname"></td>
				<td align="center"><input name="employee_id" value="" type= "text" placeholder="Employee ID"></td>
				<td align="center"><input name="phone_number" value="" type= "text" placeholder="XXX-XXX-XXXX"></td>
				<td align="center"><input name="supervisors" value="" type= "text" placeholder="Supervisors"></td>
				<td align="center">
				<input name = "whatsend" value = "saveEmployee" type = "hidden">
				<input type = "submit" value = "Save" style="background-color:#00ff00;font-weight:bold;;color:#ffffff;">
				</td>
			<tr>
			</form>
	<%
	} else {
		%>
		<tr><td colspan="9" align="center">&nbsp;</td></tr>
            <tr><td colspan="9" align="center">No Record Avaliable</td></tr>
		<%
	}
	%>
</table>

</body>
</html>