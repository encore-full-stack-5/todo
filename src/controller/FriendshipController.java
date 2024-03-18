package controller;

import java.util.List;
import java.util.Scanner;

import domain.PersonalSchedule;
import domain.Request;
import dto.RequestDto;
import dto.UserDto;
import service.FriendshipService;

public class FriendshipController {
	
	public void start() {
		while(true) {
			System.out.println("------------------------------ģ������-------------------------------\n");
			System.out.println("   1.ģ���߰�    2.��û����    3.ģ�����    4.�ڷΰ���");
			String input = sc.nextLine();
			
			if(input.equals("1")) sendRequest();
			else if(input.equals("2")) manageRequest();
			else if(input.equals("3")) showFriend();
			else if(input.equals("4")) break;
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void sendRequest() {
		System.out.print("ģ���� ID : ");
		String uid = sc.nextLine();
		RequestDto dto = new RequestDto(userId, uid);
		int sendResult = friendshipService.sendRequest(dto);
		if(sendResult == 1) System.out.println("ģ����û�� �����Ͽ����ϴ�");
		else if(sendResult == 0) System.out.println("�Է��Ͻ� ���̴ٰ� �������� �ʽ��ϴ�");
		else if(sendResult == -1) System.out.println("�� ���̵� �Է��Ͽ����ϴ�");
		else if(sendResult == -2) System.out.println("�ش� �����ʹ� �̹� ģ���Դϴ�");
	}
	private void manageRequest() {
		List<UserDto> requestUserList = friendshipService.getRequestUser(userId);
		while(true) {
			int len = requestUserList.size();
			if(len == 0) {
				System.out.println("ģ�� ��û�� �����ϴ�");
				break;
			}
			for(int i = 0; i < len; i++) {
				UserDto sender = requestUserList.get(i);
				System.out.println((i+1) + "��  ��û     �̸� : " + sender.getName() + "   ID : " + sender.getUid());
			}
			System.out.println("\n  ó���� ��û�� ��ȣ�� �Է��ϼ��� (�����Ϸ��� 0�� �Է��ϼ���)");
			int index = 0;
			while(true) {
				String inputIndex = sc.nextLine();
				try {
					index = Integer.parseInt(inputIndex);
					if(index <= len && index >= 0) break;
					System.out.println("��� ���� ���ڸ� �Է����ּ���");
				}catch (NumberFormatException e) {
					System.out.println("���ڸ� �Է��ϼ���");
				}
			}
			if(index == 0) break;
			Request request = new Request(requestUserList.get(index-1).getId(), userId);
			System.out.println("\n   1.����    2.���� ");
			while(true) {
				String inputwork = sc.nextLine();
				if(inputwork.equals("1")) { 
					acceptRequest(request);
					requestUserList.remove(index-1);
					break; 
				}
				else if(inputwork.equals("2")) { 
					rejectRequest(request);
					requestUserList.remove(index-1);
					break; 
				}
				else System.out.println("�ٽ� �Է����ּ���");
			}
		}
	}
	private void acceptRequest(Request request) {
		int acceptResult = friendshipService.acceptRequest(request);
		if(acceptResult == 1) System.out.println("��û�� �����Ͽ����ϴ�");
		else System.out.println("��û ó���� �����Ͽ����ϴ�");
	}
	private void rejectRequest(Request request) {
		int rejectResult = friendshipService.rejectRequest(request);
		if(rejectResult == 1) System.out.println("��û�� �����Ͽ����ϴ�");
		else System.out.println("��û ó���� �����Ͽ����ϴ�");
	}
	private void showFriend() {
		List<UserDto> friendList = friendshipService.getFriendList(userId);
		while(true) {
			System.out.println("----ģ�� ���----");
			int len = friendList.size();
			if(len == 0) {
				System.out.println("ģ����  �����ϴ�");
				break;
			}
			for(int i = 0; i < len; i++) {
				UserDto friend = friendList.get(i);
				System.out.println((i+1) + "�� ģ��    �̸� : " + friend.getName() + "    ���̵� : " + friend.getUid());
			}
			System.out.println("�۾��� ģ���� ��ȣ�� �Է��ϼ��� (�ڷ� ������ 0�� �Է��ϼ���)");
			int index = 0;
			while(true) {
				String inputIndex = sc.nextLine();
				try {
					index = Integer.parseInt(inputIndex);
					if(index <= len && index >= 0) break;
					System.out.println("��� ���� ���ڸ� �Է����ּ���");
				}catch (NumberFormatException e) {
					System.out.println("���ڸ� �Է��ϼ���");
				}
			}
			if(index == 0) return;
			System.out.println("--------------------------------------\n   1.ģ������ Ȯ��    2.ģ�� ���� ");
			while(true) {
				String inputwork = sc.nextLine();
				if(inputwork.equals("1")) { 
					showSharedSchedule(friendList.get(index-1).getId());
					System.out.println("�����Ϸ��� �ƹ� �ؽ�Ʈ�� �Է��ϼ���");
					sc.nextLine();
					break; 
				}
				else if(inputwork.equals("2")) { 
					removeFriend(friendList.get(index-1).getId());
					friendList.remove(index-1);
					break; 
				}
				else System.out.println("�ٽ� �Է����ּ���");
			}
		}
	}
	private void showSharedSchedule(int friendId) {
		Request ids = new Request(friendId, userId);
		List<PersonalSchedule> mySchedules = friendshipService.getSharedSchedule(ids);
		int len = mySchedules.size();
		for(int i = 0; i < len; i++) {
			PersonalSchedule schedule = mySchedules.get(i);
			System.out.println((i+1) + "�� ����      ���� : " + schedule.getTitle() + "     ��¥ : " + schedule.getDate());
		}
	}
	private void removeFriend(int friendId) {
		Request ids = new Request(friendId, userId);
		int removeResult = friendshipService.removeFriend(ids);
		if(removeResult == 1) System.out.println("���������� �����Ͽ����ϴ�");
		else System.out.println("���� �۾��� �����Ͽ����ϴ�");
	}
	
	
	
	
	
	private static FriendshipController friendshipController;
	private final FriendshipService friendshipService;
	private final int userId;
	private final Scanner sc;
	public static FriendshipController getInstance(Scanner sc, int userId) {
		if(friendshipController == null) friendshipController = new FriendshipController(sc, userId);
		return friendshipController;
	}
	private FriendshipController(Scanner sc, int userId) {
		friendshipService = FriendshipService.getInstance();
		this.userId = userId;
		this.sc = sc;
	}
}
