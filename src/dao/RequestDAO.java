package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Friend;
import domain.Request;
import domain.User;
import global.DBInit;

public class RequestDAO {
	private static RequestDAO requestDAO;
	public static RequestDAO getInstance() {
		if(requestDAO == null) requestDAO = new RequestDAO();
		return requestDAO;
	}
	
	public Request addRequest(int id) {
		String sql = "select test.name from request \r\n" + 
				"join test on test.id = request.request_id\r\n" + 
				"where request.user_id = ?;"; // 쿼리
		Request request = null;
		try (Connection conn = DBInit.getTestConnection()) {
			conn.setAutoCommit(false); // 자동 커밋 취소
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(); // select만
			while(rs.next()) {
				int idx = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int reqeuestId = rs.getInt("request_id");
				String date = rs.getString("request_date");
				request = new Request(idx, userId, requestId, date);
			}
			
		} catch (Exception e) {
			System.out.println("실패");
		}
		return user;
	}
	
	public void saveRequest(Friend friend) {
		try(Connection conn = DBInit.getTestConnection();){
			conn.setAutoCommit(false);
			String sql = "insert into request values(null, ?, ?, now())";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, friend.getFriendId());
				ps.setInt(2, friend.getUserId());
				
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
