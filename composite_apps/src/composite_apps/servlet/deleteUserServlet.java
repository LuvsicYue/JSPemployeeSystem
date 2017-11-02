package composite_apps.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import composite_apps.utility.SaveMySQL;
import composite_apps.bean.EmployeeBean;

/**
 * Servlet implementation class deleteUserServlet
 */
@WebServlet("/deleteUserServlet")
public class deleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter DeleteUserServlet");
		String employee_id = request.getParameter("id");
		System.out.println("employee_id :: " + employee_id);

		SaveMySQL deleteEmployee = new SaveMySQL();
		
		try {
			int id = Integer.parseInt(employee_id);
			deleteEmployee.deleteEmployee(id);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
			e.printStackTrace();
		} 
	
		ArrayList<EmployeeBean>  employeeInDB = new ArrayList<EmployeeBean>();
		try {
			employeeInDB = deleteEmployee.searchEmployees();
		} catch (SQLException e) {
			System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
			e.printStackTrace();
		}
		
		ServletContext sc = request.getSession().getServletContext();
		request.removeAttribute("EMPLOYEES");
		request.setAttribute("EMPLOYEES", employeeInDB);
		RequestDispatcher rd = sc.getRequestDispatcher("/home.jsp");
		rd.forward(request, response);
	}  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
