import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import data.ProjectDao;
import models.Project;
import models.User;
import services.DataService;
import views.State;


public class Testing {

@Test	
public void login1() {
	
		DataService userService = new DataService();	
		
		//testing if the user exists in the DB
		
		int userId=1;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing User " + userId);
		assertNotNull(user);
	}	
@Test	
public void login2() {
		
		DataService userService = new DataService();	
		
		//testing if the user exists in the DB
		
		int userId=2;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing User " + userId);
		assertNotNull(user);
	}

@Test	
public void login3() {
		
		DataService userService = new DataService();	
		
		//testing if the user exists in the DB
		
		int userId=3;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing User " + userId);
		assertNotNull(user);
	}

@Test	
public void login4() {
		
		DataService userService = new DataService();	
		
		//testing if the user exists in the DB
		
		int userId=4;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing User" + userId);
		assertNotNull(user);
	}
/////////////////////////////////////////////////////////////////////////////////
@Test
//testing if the user does not exist in the DB
public void login5() {
		
		DataService userService = new DataService();	
			
		int userId=10;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing " + userId);
		assertNotNull(user);
	}	

@Test
//testing if the user does not exist in the DB
public void login6() {
		
		DataService userService = new DataService();	
			
		int userId=10;
		User user = userService.GetUser(userId);
		State.getStateInstance().setUser(user);	
		System.out.println("Testing " + userId);
		assertNull(user);
	}	
/////////////////////////////////////////////////////////////////////////////////
@Test
public void projectCreation1() throws ParseException{
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	Project project = new Project();
	System.out.println("Testing Creation of a Project with all its information");
	project.setProjectName("test1");
	project.setStartDate(df.parse("2017-02-29"));
	project.setEndDate(df.parse("2018-02-29"));					
	User temp = State.getStateInstance().getUser();
	project.setManagerId(1);
	
	ProjectDao projectDao = new ProjectDao();
	projectDao.InsertProject(project);
	assertNotNull(project);
	
}

@Test
public void projectCreation2() throws ParseException{
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	Project project = new Project();
	System.out.println("Testing Creation of a Project with all its information");
	project.setProjectName("test2");
	project.setStartDate(df.parse("2017-02-29"));
	project.setEndDate(df.parse("2018-02-29"));					
	User temp = State.getStateInstance().getUser();
	project.setManagerId(1);
	
	ProjectDao projectDao = new ProjectDao();
	projectDao.InsertProject(project);
	assertNotNull(project);
	assertNull(project);
}

}