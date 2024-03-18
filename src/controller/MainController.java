package controller;

import java.util.Scanner;

public class MainController {
	private final Scanner sc;
	private static MainController mainController;
	private final UserController userController;
	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
			return mainController;
		}
		return mainController;
	}
	
	public MainController() {
		this.sc = new Scanner(System.in);
		this.userController = UserController.getInstance();
	}
	
	public void start() {
		while(true) {
			System.out.println("1.친구관리   2.일정관리   "
					+ "3.개인정보   4.로그아웃   5.프로그램 종료");
			String input = sc.nextLine();
			
			if(input.equals("1")) break; // Friendsip
				//friendship 컨트롤러
			else if(input.equals("2")) break; // schedule
			else if(input.equals("3")) userController.start();
			else if(input.equals("4")) break; // Auth
			else if(input.equals("5")){
				System.out.println("===================");
				System.out.println("프로그램 종료합니다.");
				break;
			}	
		}
	}
	
	
}
