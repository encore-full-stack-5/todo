package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import domain.GroupSchedule;
import domain.PersonalSchedule;
import dto.UserDto;
import dto.GroupScheduleDto;
import dto.PersonalScheduleDto;
import dto.ScheduleDto;
import dto.ShareDto;
import service.ScheduleService;

public class ScheduleController {
	public void start() {
		while(true) {
			System.out.println("------------------------------��������-------------------------------\n");
			System.out.println("   1.�������� ��ȸ    2.��ü���� ��ȸ    3.�������� ����    4.��ü���� ����    5.�ڷΰ���");
			String input = sc.nextLine();
			
			if(input.equals("1")) showMySchedule();
			else if(input.equals("2")) showGroupSchedule();
			else if(input.equals("3")) addPersonalSchedule();
			else if(input.equals("4")) addGroupSchedule();
			else if(input.equals("5")) break;
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void showMySchedule() {
		List<PersonalSchedule> mySchedules = scheduleService.getMySchedule(userId);
		int len = mySchedules.size();
		if(len == 0) {
			System.out.println("���������� �����ϴ�");
			return;
		}
		for(int i = 0; i < len; i++) {
			PersonalSchedule schedule = mySchedules.get(i);
			System.out.println((i+1) + "�� ����      ���� : " + schedule.getTitle() + "     ��¥ : " + schedule.getDate());
		}
		System.out.println("\n     �۾��� ������ ��ȣ�� �Է��ϼ���");
		int index = 0;
		while(true) {
			String inputIndex = sc.nextLine();
			try {
				index = Integer.parseInt(inputIndex);
				if(index <= len && index > 0) break;
				System.out.println("��� ���� ���ڸ� �Է����ּ���");
			}catch (NumberFormatException e) {
				System.out.println("���ڸ� �Է��ϼ���");
			}
		}
		System.out.println("\n   1.���� ����    2.���� ����    3.���� ���� ");
		while(true) {
			String inputwork = sc.nextLine();
			if(inputwork.equals("1")) { removePersonalSchedule(mySchedules.get(index-1).getId()); break; }
			else if(inputwork.equals("2")) { changePersonalSchedule(mySchedules.get(index-1)); break; }
			else if(inputwork.equals("3")) { sharePersonalSchedule(mySchedules.get(index-1)); break; }
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void removePersonalSchedule(int personalScheduleId) {
		scheduleService.removePersonalSchedule(personalScheduleId);
	}
	private void changePersonalSchedule(PersonalSchedule schedule) {
		System.out.println("���� ���� : " + schedule.getTitle());
		System.out.println("������ ���� �Է� : ");
		String title = sc.nextLine();
		System.out.println("���� ��¥ : " + schedule.getDate());
		System.out.println("������ ��¥ �Է� : ");
		String date = sc.nextLine();
		
		PersonalScheduleDto dto = new PersonalScheduleDto(schedule.getId(), title, date);
		scheduleService.changePersonalSchedule(dto);
	}
	private void sharePersonalSchedule(PersonalSchedule schedule) {
		List<UserDto> dtoList = scheduleService.getMyFriend(userId);
		System.out.println("----ģ�� ���----");
		int len = dtoList.size();
		if(len == 0) {
			System.out.println("ģ���� �����ϴ�");
			return;
		}
		for(int i = 0; i < len; i++) {
			UserDto friend = dtoList.get(i);
			System.out.println((i+1) + "�� ģ��    �̸� : " + friend.getName() + "    ���̵� : " + friend.getUid());
		}
		System.out.println("������ ģ���� ��ȣ�� �Է��ϼ���");
		int index = 0;
		while(true) {
			String inputIndex = sc.nextLine();
			try {
				index = Integer.parseInt(inputIndex);
				if(index <= len && index > 0) break;
				System.out.println("��� ���� ���ڸ� �Է����ּ���");
			}catch (NumberFormatException e) {
				System.out.println("���ڸ� �Է��ϼ���");
			}
		}
		ShareDto share = new ShareDto(schedule.getId(), dtoList.get(index-1).getId());
		scheduleService.shareMySchedule(share);
	}
	private void addPersonalSchedule() {
		ScheduleDto dto = make();
		scheduleService.addPersonalSchedule(dto);
	}
	private ScheduleDto make() {
		System.out.print("TO-DO ���� : ");
		String title = sc.nextLine();
		System.out.print("TO-DO ��¥  : ");
		String date = sc.nextLine();
		return new ScheduleDto(title, date, userId);
	}
	
	
	
	
	private void showGroupSchedule() {
		List<GroupSchedule> groupSchedules = scheduleService.getGroupSchedule(userId);
		int len = groupSchedules.size();
		if(len == 0) {
			System.out.println("��ü������ �����ϴ�");
			return;
		}
		for(int i = 0; i < len; i++) {
			GroupSchedule schedule = groupSchedules.get(i);
			System.out.println((i+1) + "�� ����      ���� : " + schedule.getTitle() + "     ��¥ : " + schedule.getDate());
		}
		System.out.println("\n     �۾��� ������ ��ȣ�� �Է��ϼ���");
		int index = 0;
		while(true) {
			String inputIndex = sc.nextLine();
			try {
				index = Integer.parseInt(inputIndex);
				if(index <= len && index > 0) break;
				System.out.println("��� ���� ���ڸ� �Է����ּ���");
			}catch (NumberFormatException e) {
				System.out.println("���ڸ� �Է��ϼ���");
			}
		}
		System.out.println("\n   1.������� ����    2.���� ����    3.���� ���� ");
		while(true) {
			String inputwork = sc.nextLine();
			if(inputwork.equals("1")) { showParticipant(groupSchedules.get(index-1).getId()); break; }
			else if(inputwork.equals("2")) { changeGroupSchedule(groupSchedules.get(index-1)); break; }
			else if(inputwork.equals("3")) { removeGroupSchedule(groupSchedules.get(index-1)); break; }
			else System.out.println("�ٽ� �Է����ּ���");
		}
	}
	private void showParticipant(int groupScheduleId) {
		List<UserDto> participants = scheduleService.getParticipant(groupScheduleId);
		System.out.println("----���� ���----");
		int len = participants.size();
		if(len == 0) {
			System.out.println("ģ���� �����ϴ�");
			return;
		}
		for(int i = 0; i < len; i++) {
			UserDto friend = participants.get(i);
			System.out.println((i+1) + "�� ģ��    �̸� : " + friend.getName() + "    ���̵� : " + friend.getUid());
		}
	}
	private void changeGroupSchedule(GroupSchedule groupSchedule) {

		System.out.println("���� ���� : " + groupSchedule.getTitle());
		System.out.println("������ ���� �Է� : ");
		String title = sc.nextLine();
		System.out.println("���� ��¥ : " + groupSchedule.getDate());
		System.out.println("������ ��¥ �Է� : ");
		String date = sc.nextLine();
		
		ScheduleDto dto = new ScheduleDto(title, date, groupSchedule.getId());
		scheduleService.changeGroupSchedule(dto);
	}
	private void removeGroupSchedule(GroupSchedule groupSchedule) {
		scheduleService.removeGroupSchedule(groupSchedule.getId());
	}
	private void addGroupSchedule() {
		ScheduleDto dto = make();
		List<UserDto> dtoList = scheduleService.getMyFriend(userId);
		Set<Integer> friendIdSet = new HashSet<>();
		System.out.println("----ģ�� ���----");
		int len = dtoList.size();
		for(int i = 0; i < len; i++) {
			UserDto friend = dtoList.get(i);
			System.out.println((i+1) + "�� ģ��    �̸� : " + friend.getName() + "    ���̵� : " + friend.getUid());
		}
		System.out.println("������ �Բ��� ģ���� ��ȣ�� �ϳ��� �Է��ϼ���\n�Ϸ�� �ƹ� ���ڳ� �Է��Ͻø� �˴ϴ�");
		int index = 0;
		while(true) {
			String inputIndex = sc.nextLine();
			try {
				index = Integer.parseInt(inputIndex);
				if(index <= len && index > 0) {
					friendIdSet.add(dtoList.get(index-1).getId());
					System.out.println("ģ�� " + index + " �߰�!!");
				}
				else System.out.println("��� ���� ���ڸ� �Է����ּ���");
			}catch (NumberFormatException e) { break; }
		}
		GroupScheduleDto groupDto = new GroupScheduleDto(dto, friendIdSet);
		if(friendIdSet.size() > 0) {
			List<String> unvailableList = scheduleService.getUnavailableParticipantsList(groupDto);
			if(!unvailableList.isEmpty()) {
				System.out.println(unvailableList.toString() + " �ش� �ο��� ���Ͻô� ��¥�� ������ �ֽ��ϴ� �׷��� �����Ͻó���?");
				System.out.println("   1. ����           2. ���");
				while(true) {
					String input = sc.nextLine();
					
					if(input.equals("1")) break;
					else if(input.equals("2")) return;
					else System.out.println("�ٽ� �Է����ּ���");
				}
			}
		}
		scheduleService.addGroupSchedule(groupDto);
		
	}
	
	
	
	
	
	private final ScheduleService scheduleService;
	private final Scanner sc;
	private final int userId;
	private static ScheduleController scheduleController;
	
	public static ScheduleController getInstance(Scanner sc, int id) {
		if(scheduleController == null) scheduleController = new ScheduleController(sc, id);
		return scheduleController;
	}
	private ScheduleController(Scanner sc, int id) {
		this.sc = sc;
		this.userId = id;
		scheduleService = ScheduleService.getInstance();
	}
}
