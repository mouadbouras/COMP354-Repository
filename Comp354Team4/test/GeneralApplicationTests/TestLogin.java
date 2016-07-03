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
		String username = "User";
		String password = "password";
		User user = userService.GetUser(username,password);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing Login: correct username and password ");
		assertNotNull(user);
	} 	
	
	//testing if user enters wrong "User name" and correct password
	@Test
	public void incorrectUserNameLogin() {
		
		System.out.println("Testing Login: Wrong username with corect Password  ");
		DataService userService = new DataService();	
		String username = "";
		String password = "password";
		User user = userService.GetUser(username,password);	
		assertNull(user);
	}
	
	//testing if user enters wrong "Password" and correct username
		@Test
		public void incorrectPasswordLogin() {
			
			System.out.println("Testing Login: Wrong Password with corect UserName  ");
			DataService userService = new DataService();	
			String username = "User";
			String password = "";
			User user = userService.GetUser(username,password);				
			assertNull(user);
		}
		
		//testing if user leaves empty fields for username and password
		@Test
		public void emptyLogin() {
			
			System.out.println("Testing Login: empty username/password fields");
			DataService userService = new DataService();	
			String username = null;
			String password = null;
			User user = userService.GetUser(username,password);			
			assertNull(user);
		}

}
