package service;

import dao.MainDAO;
import domain.User;
import dto.InfoDto;

public class MainService {

	public InfoDto getMyInfo(int userId) {
		return MainDAO.getMyInfo(userId);
	}
	/*
	 * ������ ���������� 1�� ����, ���������� 0����
	 */
	public int changeInfo(User info) {
		return mainDAO.changeInfo(info);
	}
	
	
	
	private static MainService mainService;
	private final MainDAO mainDAO;
	public static MainService getInstance() {
		if(mainService == null) mainService = new MainService();
		return mainService;
	}
	private MainService(){
		mainDAO = MainDAO.getInstance();
	}
}
