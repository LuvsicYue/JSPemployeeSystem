package composite_apps.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import composite_apps.bean.EmployeeBean;

public class SaveMySQL {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/composite_task";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "Zero648819";
	
	private static Connection getDBConnection() {
		System.out.println("-------- MySQL JDBC Connection --------");
		
		// load the JDBC driver
		try {
			Class.forName(DB_DRIVER);  
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: MySQL JDBC Driver not found!!");
			System.out.println(e.getMessage());
		}
		
		// Connect to MySQL DB
		Connection dbConnection = null;
		try {
			dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("SQL Connection to Composite_task database established!");
			return dbConnection;
		} catch (SQLException e) {
			System.out.println("Connetion to Composite_task database failed!");
			System.out.println(e.getMessage());
		}
		return dbConnection;
		
	}
		
	public int insertEmployee(EmployeeBean employee) throws SQLException, IOException {
		Statement stmt = null;
		Connection conn = null;
		int query = 0;
		try {
			conn = getDBConnection();
			conn.setAutoCommit(false);  // not to make permanent changes to database until confirmed
			stmt = conn.createStatement();
			String insertEmployee = "INSERT INTO EMPLOYEES";
			insertEmployee += " VALUES (" 
					+ employee.getEmployee_id().toString() + ",'"
					+ employee.getName() + "','"
					+ employee.getPhone_number() + "','"
					+ employee.getSupervisors() + "',"
					+ "SYSDATE())";
			System.out.println("INSERT EMPLOYEES:" + insertEmployee);
			int resultInsertEmployee = stmt.executeUpdate(insertEmployee);
			System.out.println("result Insert QUERY: " + resultInsertEmployee);
			query = resultInsertEmployee;
			conn.commit();
		} catch (SQLException sqle) {
			conn.rollback();  // "forget" the uncommitted updates
			System.out.println("INSERT ERROR : Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			conn.rollback();
			System.out.println("GENERIC ERROR : Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage());
		} finally {
			// close stmt and conn no matter what
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} 
		return query;
	}
	public ArrayList<EmployeeBean> searchEmployees() throws SQLException, IOException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getDBConnection();
			stmt = conn.createStatement();
			String searchEmployee = " SELECT * FROM EMPLOYEES ORDER BY name";
			
			System.out.println("QUERY: " + searchEmployee);
			ResultSet employeeList = stmt.executeQuery(searchEmployee);
			
			ArrayList<EmployeeBean> employeeListInDB = new ArrayList<>();
			
			while (employeeList.next()) {  // move the cursor "go down" in sequence
				EmployeeBean employee = new EmployeeBean();
				Integer employee_id = employeeList.getInt("employee_id");
				String name = employeeList.getString("name");
				String phone = employeeList.getString("phone_number");
				String supervisors = employeeList.getString("supervisors");
				employee.setName(name);
				employee.setEmployee_id(employee_id);
				employee.setPhone_number(phone);
				employee.setSupervisors(supervisors);
				employeeListInDB.add(employee);
			}
			return employeeListInDB;
			
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + " : " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
	}

	public void deleteEmployee(Integer employee_id) throws SQLException, IOException {
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String deleteEmployee = "DELETE FROM EMPLOYEES WHERE";
			deleteEmployee += " employee_id = "
					+ employee_id ;
			System.out.println("DELETE QUERY " + deleteEmployee);
			int resultDeleteEmployee = stmt.executeUpdate(deleteEmployee);
			System.out.println("result Delete QUERY: " + resultDeleteEmployee);
			conn.commit();
		} catch (SQLException sqle ) {
			conn.rollback();
			System.out.println("DELETE ERROR : Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			conn.rollback();
			System.out.println("GENERIC ERROR : Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
	}
}

