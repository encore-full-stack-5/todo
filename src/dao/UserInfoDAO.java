package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.User;
import global.DBInit;

public class UserInfoDAO {
	private static UserInfoDAO userInfoDAO;
	public static UserInfoDAO getInstance() {
		if(userInfoDAO == null) userInfoDAO = new UserInfoDAO();
		return userInfoDAO;
	}
	
	public User findUserInfo(int id) {
		String sql = "select * from test where id = ?"; // 쿼리
		User user = null;
		try (Connection conn = DBInit.getTestConnection()) {
			conn.setAutoCommit(false); // 자동 커밋 취소
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(); // select만
			while(rs.next()) {
				int idx = rs.getInt("id");
				String uid = rs.getString("uid");
				String password = rs.getString("password");
				String name = rs.getString("name");
				user = new User(idx, uid, password, name);
			}
			
		} catch (Exception e) {
			System.out.println("실패");
		}
		return user;
	}
	
	public User findByUserId(String userId) {
		String sql = "select * from test where uid = ?"; // 쿼리
		User user = null;
		try (Connection conn = DBInit.getTestConnection()) {
			conn.setAutoCommit(false); // 자동 커밋 취소
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery(); // select만
			while(rs.next()) {
				int idx = rs.getInt("id");
				String uid = rs.getString("uid");
				String password = rs.getString("password");
				String name = rs.getString("name");
				user = new User(idx, uid, password, name);
			}
			
		} catch (Exception e) {
			System.out.println("실패");
		}
		return user;
	}
	
	public void saveUser(User user) {
		try(Connection conn = DBInit.getTestConnection();){
			conn.setAutoCommit(false);
			String sql = "insert into test values(null, ?, ?, ?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				
				int rs = ps.executeUpdate();
				if(rs != 1) conn.rollback();
				else conn.commit();
			} catch(Exception e) {
				conn.rollback();
			}
		} catch(Exception e) {
			System.out.println("실패");
		}
	}
	
	public void updateUser(User user) {
		String sql = "update test set password = ?, name = ? where id = ?"; // 쿼리

		try(Connection conn = DBInit.getTestConnection();){
			conn.setAutoCommit(false);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getPassword());
				ps.setString(2, user.getName());
				ps.setInt(3, user.getId());
				
				int rs = ps.executeUpdate();
				if(rs != 1) conn.rollback();
				else conn.commit();
			} catch(Exception e) {
				conn.rollback();
			}
		} catch(Exception e) {
			System.out.println("실패");
		}
	}
	
	
	public void deleteUser(int id) {
		try(Connection conn = DBInit.getTestConnection();){
			conn.setAutoCommit(false);
			String sql = "delete from test where id = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				int rs = ps.executeUpdate();
				
				if(rs != 1) conn.rollback();
				else conn.commit();
			} catch(Exception e) {
				conn.rollback();
			}
		} catch(Exception e) {
			System.out.println("실패");
		}
	}
}

