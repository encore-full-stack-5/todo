package service;

import dao.FriendDAO;
import dao.UserInfoDAO;
import domain.Friend;
import domain.User;

public class FriendshipService {
	private static FriendshipService service;
	private final FriendDAO dao;
	private final UserInfoDAO userDAO;
	public static FriendshipService getInstance() {
		if(service == null) service = new FriendshipService();
		return service;
	}
	
	public FriendshipService() {
		dao = FriendDAO.getInstance();
		userDAO = UserInfoDAO.getInstance();
	}
	
	public void createFriend(Friend friend) {
		User exist = userDAO.findUserInfo(friend.getUserId());
		if(exist == null) {
			System.out.println("등록되지 않은 사용자입니다.");
		}
		dao.saveFriend(friend);
	}
}