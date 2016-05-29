package GeneralApplicationTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.DataService;
import models.User;
import views.State;

public class TestLogin {

	@Test
	public void correctLogin() {

		DataService userService = new DataService();

		// testing if the user exists in the DB

		int userId = 1;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);
		System.out.println("Testing User " + userId);
		assertNotNull(user);
	}
	
	// TODO
	@Test
	public void incorrectLogin() {

		DataService userService = new DataService();

		// testing if the user exists in the DB

		int userId = 1;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);
		System.out.println("Testing User " + userId);
		assertNotNull(user);
	}

}
