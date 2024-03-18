package controller;

import java.util.Scanner;

import domain.Friend;
import domain.User;
import global.UserInfo;
import service.FriendshipService;
import service.UserService;

public class FriendshipController {

	private final Scanner sc;
	private static FriendshipController friendshipController;
	private final FriendshipService friendshipService;
	private final UserService userService;
	
	public static FriendshipController getInstance() {
		if (friendshipController == null) {
			friendshipController = new FriendshipController();
			return friendshipController;
		}
		return friendshipController;
	}
	
	public FriendshipController() {
		this.friendshipService = FriendshipService.getInstance();
		this.sc = new Scanner(System.in);
		this.userService = UserService.getInstance();
		
//		this.friendshipService = FriendshipService.getInstance();
	}
	
	public void start() {
		while(true) {
			System.out.println("1.ģ���߰� 2.��û���� 3.ģ�����");
			String input = sc.nextLine();
			if(input == "1") createUser();
			if(input == "2") break;
			if(input == "3") break;
		}
	}
	
	public Friend createUser() {
		Friend friend = new Friend();
		try {
			System.out.println("���̵� �Է�");
			String userId = sc.nextLine();
			
			User user = userService.findUserId(userId);
			friend.setFriendId(user.getId());
			friend.setUserId(UserInfo.loggedUser);
			friendshipService.createFriend(friend);
			
		} catch(Exception e) {
			System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
		}
		return friend;
	}
	
}
