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
			
			System.out.println("번호를 선택하세요  1. 로그인   2. 회원가입    3. 종료");
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
				System.out.println("종료");
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
			System.out.println("로그인 성공");
			System.out.println(UserInfo.loggedUser);
		}else {
			System.out.println("다시 입력해 주세요.");
		}
//		}
		

	}		
			
	
	
}
