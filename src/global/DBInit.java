package global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInit {
	private static final String url = "jdbc:mysql://192.168.80.21:3306/project_1";
	private static final String url2 = "jdbc:mysql://localhost:3306/test";
	private static final String user = "encore";
	private static final String user2 = "root";
	private static final String password = "1234";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static Connection getTestConnection() throws SQLException {
		return DriverManager.getConnection(url2, user2, password);
	}
	
	
}
