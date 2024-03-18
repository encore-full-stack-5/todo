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
			System.out.println("1.ģ������   2.��������   "
					+ "3.��������   4.�α׾ƿ�   5.���α׷� ����");
			String input = sc.nextLine();
			
			if(input.equals("1")) break; // Friendsip
				//friendship ��Ʈ�ѷ�
			else if(input.equals("2")) break; // schedule
			else if(input.equals("3")) userController.start();
			else if(input.equals("4")) break; // Auth
			else if(input.equals("5")){
				System.out.println("===================");
				System.out.println("���α׷� �����մϴ�.");
				break;
			}	
		}
	}
	
	
}
