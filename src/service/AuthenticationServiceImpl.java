package service;

import dao.AuthenticationDao;

public class AuthenticationServiceImpl {
	
	
	private static AuthenticationServiceImpl service;
	private static AuthenticationDao dao;
	public static AuthenticationServiceImpl getInstance() {
		if(service == null)
			service = new AuthenticationServiceImpl();
		return service ;
	}
	
	//AuthenticationServiceImpl service1 = new AuthenticationServiceImpl();
	public AuthenticationServiceImpl() {
		dao = AuthenticationDao.getInstance();
	}
	
	public boolean logIn(String id, String pw) {
	
		return dao.login(id, pw);
		
	}

	
}


