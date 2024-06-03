import controller.UserController;
import view.UsersManagerView;

public class App {
	public static void main(String[] args) {
		UsersManagerView userManagerView = new UsersManagerView();
		UserController userController = new UserController(userManagerView);
		
		userController.render();
	}
}
