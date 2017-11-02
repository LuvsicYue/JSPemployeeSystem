package composite_task.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/composite_task";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "Zero648819";
	
	public static boolean validate(String name, String pass) {
		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DB_DRIVER).newInstance();
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			pst = conn.prepareStatement("SELECT * FROM login WHERE user=? AND password=?");
			pst.setString(1, name);
			pst.setString(2, pass);
			
			rs = pst.executeQuery();
			status = rs.next();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		return status;
	}
}
