package todo;

import java.sql.Connection;

import controller.AuthenticationController;
import controller.MainController;
import global.DBInit;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthenticationController authController = AuthenticationController.getInstance();
		authController.start();
		MainController controller = MainController.getInstance();
		controller.start();
		try(Connection conn = DBInit.getConnection()){
			System.out.println("����");
		}catch (Exception e) {
			System.out.println("����");
		}
	}

}
