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
			System.out.println("1.��ȸ\t 2.����\t 3.����\t 4.������");
			String input = sc.nextLine();
			if(input.equals("1")) getUserInfo(UserInfo.loggedUser);
			else if(input.equals("2")) updateUser(UserInfo.loggedUser);
			else if(input.equals("3")) {
				dropUserInfo(UserInfo.loggedUser);
				break;
			}
			else if(input.equals("4")) break;
			else System.out.println("�߸��� �Է��Դϴ�.");
			
		}
	}
	
	public User makeUser() {
		User user = new User();
		try {
			System.out.println("���̵� �Է�");
			String userId = sc.nextLine();
			
			System.out.println("�н����� �Է�");
			String password = sc.nextLine();
			
			System.out.println("�̸��� �Է�");
			String name = sc.nextLine();
			
			user.setUserId(userId);
			user.setPassword(password);
			user.setName(name);
			userService.createUser(user);
			
		} catch(Exception e) {
			System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
		}
		return user;
	}
	
	public void getUserInfo(int id) {
		User user = userService.findUserInfo(id);
//		System.out.println(user.getName());
		System.out.println(user.getUserId()+", "+user.getName());
	}
	
	public void updateUser(int id) {
		System.out.println("������ ��й�ȣ");
		String password = sc.nextLine();
		
		System.out.println("������ �̸�");
		String name = sc.nextLine();
		
		userService.updateUser(password, name, id);
	}
	
	public void dropUserInfo(int id) {
		userService.deleteUser(id);
		System.out.println("������ �Ϸ��Ͽ����ϴ�.");
		System.out.println("======================");
	}
}
