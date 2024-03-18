package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import global.DBInit;
import global.UserInfo;

public class AuthenticationDao {
	private static AuthenticationDao dao;
	
	public static AuthenticationDao getInstance() {
		if(dao == null)
			dao = new AuthenticationDao();
		return dao;
	}
	
	public boolean login(String id, String pw){
		try (Connection conn = DBInit.getTestConnection()) {
		    conn.setAutoCommit(false); // Disable auto-commit
		    String sql = "select id, uid, password from test where uid = ? and password = ?"; // Query
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, id);
		    ps.setString(2, pw);
		    ResultSet rs = ps.executeQuery(); // Execute the query
		    while (rs.next()) {
		    	int idx = rs.getInt("id");
		    	String userId = rs.getString("uid");
		        String pass = rs.getString("password");

		        UserInfo.loggedUser = idx;
		        
		        return true;
		    }
		} catch (SQLException e) {
		    System.out.println("실패");
		    e.printStackTrace();
		}
		System.out.println("실패");
		return false;
	}
	
}


