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
import javax.servlet.http.HttpSession;

import composite_apps.bean.EmployeeBean;
import composite_apps.utility.Login;
import composite_apps.utility.SaveMySQL;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		
		System.out.println("username: " + n);
		System.out.println("password: " + p);
		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute("name", n);
		} 
		if (Login.validate(n, p)){
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
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.include(request, response);
		}
	}

}

