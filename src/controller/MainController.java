package controller;

import java.util.Scanner;

import domain.User;
import dto.InfoDto;
import service.AuthenticationService;
import service.MainService;

public class MainController {
	public void start() {
		while(true) {
			System.out.println("------------------------------����ȭ��-------------------------------\n");
			System.out.println("   1.ģ������    2.��������    3.�������� ����    4.�α׾ƿ�    5.����");
			String input = sc.nextLine();
			
			if(input.equals("1")) friendshipController.start();
			else if(input.equals("2")) scheduleController.start();
			else if(input.equals("3")) changeMyInfo();
			else if(input.equals("4")) break;
			else if(input.equals("5")) {
				System.out.println("���� �����մϴ�");
				System.exit(0);
			}
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void changeMyInfo() {
		InfoDto myInfo = mainService.getMyInfo(userId);
		String uid = myInfo.getUid();
		String pw = myInfo.getPw();
		String name = myInfo.getName();
		while(true) {
			System.out.println("------------------------------�������� ����-------------------------------\n");
			System.out.println("   1.ID ����    2.PW ����    3.�̸� ����    4.�Ϸ� ");
			String input = sc.nextLine();
			
			if(input.equals("1")) uid = change(uid);
			else if(input.equals("2")) {
				System.out.println("���� PW : " + pw);
				System.out.print("PW : ");
				pw = sc.nextLine();
			}
			else if(input.equals("3")) {
				System.out.println("���� �̸� : " + name);
				System.out.print("�̸� : ");
				name = sc.nextLine();
			}
			else if(input.equals("4")) break;
			else System.out.println("�ٽ� �Է����ּ���");
		}
		
		User info = new User(userId, uid, pw, name);
		int changeResult = mainService.changeInfo(info);
		if(changeResult == 1) System.out.println("������ �����Ͽ����ϴ�");
		else System.out.println("���� ���� ���ФФ�");
	}
	private String change(String uid) {
		String newUid;
		while(true) {
			System.out.println("���� ID : " + uid);
			System.out.print("ID : ");
			newUid = sc.nextLine();
			boolean uidAvailable = AuthenticationService.isUidAvailable(newUid);
			if(uidAvailable) break;
			else System.out.println("��� �Ұ����� ���̵��Դϴ�");
		}
		return newUid;
	}
	
	
	
	private final Scanner sc;
	private final int userId;
	private final MainService mainService;
	private final FriendshipController friendshipController;
	private final ScheduleController scheduleController;
	
	public MainController(Scanner sc, int userId) {
		mainService = MainService.getInstance();
		friendshipController = FriendshipController.getInstance(sc, userId);
		scheduleController = ScheduleController.getInstance(sc, userId);
		this.sc = sc;
		this.userId = userId;
	}
}
