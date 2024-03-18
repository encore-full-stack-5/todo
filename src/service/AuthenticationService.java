package service;

import dao.AuthenticationDAO;
import dto.LoginDto;
import dto.InfoDto;

public class AuthenticationService {
	
	/*
	 *�α��� �����ϸ� id����, ���̵� ������ 0 ����, ��й�ȣ�� Ʋ���� -1���� 
	 */
	public int longin(LoginDto dto) {
		boolean isUidExist = authenticationDAO.isUidExist(dto.getUid());
		if (isUidExist) {
			int checkPw = authenticationDAO.checkPw(dto);
			return checkPw;
		}else return 0;
		
	}
	public static boolean isUidAvailable(String uid) {
		return AuthenticationDAO.isUidAvailable(uid);
	}
	/*
	 * ȸ������ �����ϸ� 1����, �����ϸ� 0����
	 */
	public static int SignUp(InfoDto dto) {
		return AuthenticationDAO.signUp(dto);
	}

	
	
	
	private static AuthenticationService authenticationService;
	private final AuthenticationDAO authenticationDAO;
	public static AuthenticationService getInstance() {
		if(authenticationService == null) authenticationService = new AuthenticationService();
		return authenticationService;
	}
	private AuthenticationService() {
		authenticationDAO = AuthenticationDAO.getInstance();
	}
}
