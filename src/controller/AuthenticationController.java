package controller;

import java.util.Scanner;

import global.UserInfo;
import service.AuthenticationServiceImpl;

public class AuthenticationController {
	
	private final Scanner sc;
	private static AuthenticationController controller;
	private final  AuthenticationServiceImpl service;
	private final UserController userController;
	public static AuthenticationController getInstance() {
		if(controller == null)
			controller = new AuthenticationController();
		return controller;
	}
	public AuthenticationController() {
		this.sc = new Scanner(System.in);
		service = AuthenticationServiceImpl.getInstance();
		userController = UserController.getInstance();
	}
	
	public void start() {
		while(true) {
			
			System.out.println("��ȣ�� �����ϼ���  1. �α���   2. ȸ������    3. ����");
			String input = sc.nextLine();
			if(input.equals("1")) {
				logIn();
				break;
			}else if(input.equals("2")) {
				userController.makeUser();
				logIn();
				break;
			}
			else if(input.equals("3")) {
				System.out.println("����");
				break;
			}
		}
	}
	
	
	
	public void logIn() {
		System.out.println("ID:");
		String id = sc.nextLine();
		System.out.println("PW:");
		String pw = sc.nextLine();
			
		if(service.logIn(id, pw)) {
			System.out.println("�α��� ����");
			System.out.println(UserInfo.loggedUser);
		}else {
			System.out.println("�ٽ� �Է��� �ּ���.");
		}
//		}
		

	}		
			
	
	
}
