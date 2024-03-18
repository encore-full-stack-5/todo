package controller;

import java.util.Scanner;

import dto.LoginDto;
import dto.InfoDto;
import service.AuthenticationService;

public class AuthenticationController {
	public void start() {
		while(true) {
			System.out.println("------------------------------���� ����-------------------------------\n");
			System.out.println("   1.�α���    2.ȸ������    3.����");
			String input = sc.nextLine();
			
			if(input.equals("1")) login();
			else if(input.equals("2")) signUp();
			else if(input.equals("3")) {
				System.out.println("���� �����մϴ�");
				return;
			}
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void login() {
		System.out.print("ID : ");
		String uid = sc.nextLine();
		System.out.print("PW : ");
		String pw = sc.nextLine();
		LoginDto dto = new LoginDto(uid, pw);
		int loginResult = authenticationService.longin(dto);
		if(loginResult == 0) System.out.println("�Է��Ͻ� ���̵� �����ϴ�");
		else if(loginResult == -1) System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�");
		else {
			System.out.println("���������� �α��� �Ͽ����ϴ�");
			MainController main = new MainController(sc, loginResult);
			main.start();
		}
	}
	private void signUp() {
		String uid;
		while(true) {
			System.out.print("ID : ");
			uid = sc.nextLine();
			boolean uidAvailable = AuthenticationService.isUidAvailable(uid);
			if(uidAvailable) break;
			System.out.println("��� �Ұ����� ���̵��Դϴ�");
		}
		System.out.print("PW : ");
		String pw = sc.nextLine();
		System.out.print("�̸� : ");
		String name = sc.nextLine();
		InfoDto dto = new InfoDto(uid, pw, name);
		int signUpResult = AuthenticationService.SignUp(dto);
		if(signUpResult == 1) System.out.println("ȸ������ �Ϸ�");
		else System.out.println("ȸ������ ���ФФ�");
	}
	
	
	
	private final Scanner sc;
	private static AuthenticationController authenticationController;
	private final AuthenticationService authenticationService;
	
	public static AuthenticationController getInstance() {
		if(authenticationController == null) authenticationController = new AuthenticationController();
		return authenticationController;
	}
	private AuthenticationController() {
		authenticationService = AuthenticationService.getInstance();;
		sc = new Scanner(System.in);
	}
}
