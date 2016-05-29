package GeneralApplicationTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.DataService;
import models.User;
import views.State;

public class TestLogin {

	@Test	
	//testing if user exists in DB
	public void correctLogin(){
		DataService userService = new DataService();	
		String username = "mouad";
		String password = "password";
		User user = userService.GetUser(username,password);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing User LOGIN ");
		assertNotNull(user);
	} 	
	
	// TODO
	@Test
	public void incorrectLogin() {
	}

}
