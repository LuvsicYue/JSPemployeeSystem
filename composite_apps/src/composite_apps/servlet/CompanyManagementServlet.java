package composite_apps.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import composite_apps.bean.EmployeeBean;
import composite_apps.utility.SaveMySQL;

/**
 * Servlet implementation class CompanyManagementServlet
 */
@WebServlet("/CompanyManagementServlet")
public class CompanyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyManagementServlet() {
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
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend   :: " + whatsend);
		if (whatsend.equals("homepage")) {
			request.getSession().removeAttribute("EMPLOYEE");
			response.sendRedirect("/composite_apps/formEmployeeFull.jsp");
			
		} else if (whatsend.equals("employee")) {
			request.getSession().removeAttribute("EMPLOYEE");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("lookEmployee")) {
			SaveMySQL saveEmployee = new SaveMySQL();
			ArrayList<EmployeeBean> employeeInDB = new ArrayList<>();
			try {
				employeeInDB = saveEmployee.searchEmployees();
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend   :: " + whatsend);
		String employee_id = request.getParameter("employee_id");
		System.out.println("employee_id :: " + employee_id);
		String name = request.getParameter("name");
		System.out.println("name :: " + name);
		String phone_number = request.getParameter("phone_number");
		System.out.println("phone_number :: " + phone_number);
		String supervisors = request.getParameter("supervisors");
		System.out.println("supervisors :: " + supervisors);
		SaveMySQL saveEmployee = new SaveMySQL();
		ArrayList<EmployeeBean> employeeInDB = new ArrayList<>();
		try {
			employeeInDB = saveEmployee.searchEmployees();
		} catch (SQLException e) {
			System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
			e.printStackTrace();
		}
		ServletContext sc = request.getSession().getServletContext();
		request.removeAttribute("EMPLOYEES");
		request.setAttribute("EMPLOYEES", employeeInDB);
		boolean success = false;
		EmployeeBean employee= new EmployeeBean();
		try {
			int id = Integer.parseInt(employee_id);
			if (id < 0) {
				throw new NumberFormatException();
			}
			employee.setEmployee_id(id);
			employee.setName(name);
			employee.setPhone_number(phone_number);
			employee.setSupervisors(supervisors);
			success = true;
		} catch (NumberFormatException e) {
			System.out.println("Invalid Employee ID");
			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();
//			out.print("<center><p style=\"color:red\">Invalid input for Employee ID, Please enter only number(-2147483648 ~ 2147483647) </p></center>");
			RequestDispatcher rd = sc.getRequestDispatcher("/wrongId.jsp");
			rd.forward(request, response);
		}
		
		int query = 0;
		if (success) {
			
			try {
				query = saveEmployee.insertEmployee(employee);
			} catch (SQLException e) {
	//			System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
	//			e.printStackTrace();
				response.setContentType("text/html");
				RequestDispatcher rd = sc.getRequestDispatcher("/duplicateId.jsp");
				rd.include(request, response);
			} 
		}
		if (query != 0) {
			
			try {
				employeeInDB = saveEmployee.searchEmployees();
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			sc = request.getSession().getServletContext();
			request.removeAttribute("EMPLOYEES");
			request.setAttribute("EMPLOYEES", employeeInDB);
			RequestDispatcher rd = sc.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
	}

}
