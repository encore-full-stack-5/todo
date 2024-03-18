package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.Friend;
import global.DBInit;

public class FriendDAO {
	private static FriendDAO friendDAO;
	public static FriendDAO getInstance() {
		if(friendDAO == null) friendDAO = new FriendDAO();
		return friendDAO;
	}
	
	public void saveFriend(Friend friend) {
		try(Connection conn = DBInit.getTestConnection();){
			conn.setAutoCommit(false);
			String sql = "insert into friend values(null, ?, ?)";
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
			System.out.println("½ÇÆÐ");
		}
	}
}
