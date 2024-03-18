package service;

import dao.UserInfoDAO;
import domain.User;

public class UserService {
	private static UserService service;
	private final UserInfoDAO dao;
	public static UserService getInstance() {
		if(service == null) service = new UserService();
		return service;
	}
	
	public UserService() {
		dao = UserInfoDAO.getInstance();
	}
	
	public void createUser(User user) {
		User exist = dao.findUserInfo(user.getId());
		if(exist != null) {
			System.out.println("유저가 이미 있습니다.");
			
		}
		dao.saveUser(user);
	}
	
	public User findUserInfo(int id) {
		return dao.findUserInfo(id);
	}
	
	public User findUserId(String userId) {
		return dao.findByUserId(userId);
	}
	
	public void updateUser(String password, String name, int id) {
		User user = dao.findUserInfo(id);
		user.setPassword(password);
		user.setName(name);

		dao.updateUser(user);
	}
	
	public void deleteUser(int id) {
		dao.deleteUser(id);
	}
}
