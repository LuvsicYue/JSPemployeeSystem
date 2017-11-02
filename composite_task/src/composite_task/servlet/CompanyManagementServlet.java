package composite_task.servlet;

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

import composite_task.bean.CompanyBean;
import composite_task.bean.EmployeeBean;
import composite_task.utility.SaveMySQL;

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
		if (whatsend.equals("employeeInsert")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("homepage")) {
			request.getSession().removeAttribute("COMPANY");
			request.getSession().removeAttribute("EMPLOYEE");
			response.sendRedirect("/composite_task/formEmployeeFull.jsp");
			
		} else if (whatsend.equals("employee")) {
			request.getSession().removeAttribute("EMPLOYEE");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("company")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompanyFull.jsp");
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
			RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		// TODO Auto-generated method stub
		String whatsend = request.getParameter("whatsend");
		CompanyBean company = new CompanyBean();
		System.out.println("whatsend   :: " + whatsend);
		if (whatsend.equals("company")) {
			String idcompany = request.getParameter("idcompany");
			System.out.println("idcompany :: " + idcompany);
			String company_name = request.getParameter("company_name");
			System.out.println("company_name :: " + company_name);
			String phone = request.getParameter("phone");
			System.out.println("phone :: " + phone);
			String email = request.getParameter("email");
			System.out.println("email :: " + email);
			
			ArrayList<EmployeeBean> companyEmployees = new ArrayList<>();
			
			company.setIdcompany(idcompany);
			company.setCompany_name(company_name);
			company.setEmail(email);
			company.setPhone(phone);
			company.setCompanyEmployees(companyEmployees);
			
			ServletContext sc = request.getSession().getServletContext();
			
			request.getSession().removeAttribute("COMPANY");
			request.getSession().setAttribute("COMPANY", company);
			
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompanyFull.jsp");
			rd.forward(request, response);
			
			
		} else if (whatsend.equals("employee")) {
			String employee_id = request.getParameter("employee_id");
			System.out.println("employee_id :: " + employee_id);
			String name = request.getParameter("name");
			System.out.println("name :: " + name);
			String phone_number = request.getParameter("phone_number");
			System.out.println("phone_number :: " + phone_number);
			String supervisors = request.getParameter("supervisors");
			System.out.println("supervisors :: " + supervisors);
//			String fk_company = request.getParameter("FK_company");
//			System.out.println("FK_company :: " + fk_company);
			try {
				EmployeeBean employee = new EmployeeBean();
				int id = Integer.parseInt(employee_id);
				employee.setEmployee_id(id);
				employee.setName(name);
				employee.setSupervisors(supervisors);
				employee.setPhone_number(phone_number);
	//			employee.setFk_company(fk_company);
				
				
				request.getSession().removeAttribute("EMPLOYEE");
				request.getSession().setAttribute("EMPLOYEE", employee);
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
				rd.forward(request, response);
			} catch (NumberFormatException err) {
//				System.out.println("ERROR:" + err.getMessage());
//				System.out.println("Invalid input for Employee ID, Please enter only number for Employee ID");
////				err.printStackTrace();
//				ServletContext sc = request.getSession().getServletContext();
//				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
//				rd.forward(request, response);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				ServletContext sc = request.getSession().getServletContext();
				out.print("<center><p style=\"color:red\">Invalid input for Employee ID, Please enter only number(-2147483648 ~ 2147483647) </p></center>");
				RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
				rd.include(request, response);
			}
					
			// ADD Employee to Company
//			if (request.getSession() != null && request.getSession().getAttribute("COMPANY") != null) {
//				company = (CompanyBean) request.getSession().getAttribute("COMPANY");
//				ArrayList<EmployeeBean> companyEmployees = company.getCompanyEmployees();
//				
//				companyEmployees.add(employee);
//				company.setCompanyEmployees(companyEmployees);
//				request.getSession().removeAttribute("COMPANY");
//				request.getSession().setAttribute("COMPANY", company);
//			}
		} else if (whatsend.equalsIgnoreCase("saveCompany")) {
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySQL saveCompany = new SaveMySQL();
			
			try {
				saveCompany.insertCompany(company);
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			} 
			
			ArrayList<CompanyBean>  companyInDB = new ArrayList<CompanyBean>();
			try {
				companyInDB = saveCompany.searchCompanies();
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			
			ServletContext sc = request.getSession().getServletContext();
			request.removeAttribute("COMPANIES");
			request.setAttribute("COMPANIES", companyInDB);
			RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("deleteCompany")) {
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySQL deleteCompany = new SaveMySQL();
			
			try {
				deleteCompany.deleteCompany(company);
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			} 
			
			ArrayList<CompanyBean>  companyInDB = new ArrayList<CompanyBean>();
			try {
				companyInDB = deleteCompany.searchCompanies();
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			
			ServletContext sc = request.getSession().getServletContext();
			request.removeAttribute("COMPANIES");
			request.setAttribute("COMPANIES", companyInDB);
			RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
			rd.forward(request, response);
		} else if (whatsend.equalsIgnoreCase("saveEmployee")) {
			EmployeeBean employee= new EmployeeBean();
			employee = (EmployeeBean) request.getSession().getAttribute("EMPLOYEE");
			SaveMySQL saveEmployee = new SaveMySQL();
			int query = 0;
			try {
				query = saveEmployee.insertEmployee(employee);
			} catch (SQLException e) {
//				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
//				e.printStackTrace();
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				ServletContext sc = request.getSession().getServletContext();
				out.print("<center><p style=\"color:red\">Duplicate Employee ID in the database </p></center>");
				RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
				rd.include(request, response);
			} 
			ArrayList<EmployeeBean> employeeInDB = new ArrayList<>();
			try {
				employeeInDB = saveEmployee.searchEmployees();
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			if (query != 0) {
				ServletContext sc = request.getSession().getServletContext();
				request.removeAttribute("EMPLOYEES");
				request.setAttribute("EMPLOYEES", employeeInDB);
				RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
				rd.forward(request, response);
			}

		} else if (whatsend.equals("deleteEmployee")) {		
			String employee_id = request.getParameter("employee_id");
			System.out.println("employee_id  ::   " + employee_id);
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
			RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
			rd.forward(request, response);
		}  
	}

}
