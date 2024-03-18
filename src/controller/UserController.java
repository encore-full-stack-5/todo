package controller;

import java.util.Scanner;

import domain.User;
import global.UserInfo;
import service.UserService;

public class UserController {
	private final Scanner sc;
	private static UserController userController;
	private final UserService userService;
	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
			return userController;
		}
		return userController;
	}
	
	public UserController() {
		this.sc = new Scanner(System.in);
		this.userService = UserService.getInstance();
	}
	
	public void start() {
		while(true) {
			System.out.println("1.조회\t 2.생성\t 3.삭제\t 4.나가기");
			String input = sc.nextLine();
			if(input.equals("1")) getUserInfo(UserInfo.loggedUser);
			else if(input.equals("2")) updateUser(UserInfo.loggedUser);
			else if(input.equals("3")) {
				dropUserInfo(UserInfo.loggedUser);
				break;
			}
			else if(input.equals("4")) break;
			else System.out.println("잘못된 입력입니다.");
			
		}
	}
	
	public User makeUser() {
		User user = new User();
		try {
			System.out.println("아이디를 입력");
			String userId = sc.nextLine();
			
			System.out.println("패스워드 입력");
			String password = sc.nextLine();
			
			System.out.println("이름을 입력");
			String name = sc.nextLine();
			
			user.setUserId(userId);
			user.setPassword(password);
			user.setName(name);
			userService.createUser(user);
			
		} catch(Exception e) {
			System.out.println("이미 존재하는 아이디입니다.");
		}
		return user;
	}
	
	public void getUserInfo(int id) {
		User user = userService.findUserInfo(id);
//		System.out.println(user.getName());
		System.out.println(user.getUserId()+", "+user.getName());
	}
	
	public void updateUser(int id) {
		System.out.println("변경할 비밀번호");
		String password = sc.nextLine();
		
		System.out.println("변경할 이름");
		String name = sc.nextLine();
		
		userService.updateUser(password, name, id);
	}
	
	public void dropUserInfo(int id) {
		userService.deleteUser(id);
		System.out.println("삭제를 완료하였습니다.");
		System.out.println("======================");
	}
}
