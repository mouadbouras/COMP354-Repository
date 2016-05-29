import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import models.ProjectDao;
import models.Activity;
import models.ActivityDao;
import models.Project;
import models.User;
import controllers.DataService;
import views.State;


public class Tests {
	
@Test
public void databaseConnection(){
	
}
	
@Test	
//testing if user exists in DB
public void login(){
	DataService userService = new DataService();	
	String username = "mouad";
	String password = "password";
	User user = userService.GetUser(username,password);
	State.getStateInstance().setUser(user);	
	System.out.println("Testing User LOGIN ");
	assertNotNull(user);
} 	


@Test
//testing if manager is able to create a VALID project
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
//testing creation of a Valid activity
public void activitycreation() throws ParseException{
	
	Activity temp = new Activity();	
	System.out.println("Testing Creation of a Activity");
	temp.setId(100);
	temp.setActivityName("UserTest");		
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	temp.setStartDate(df.parse("2016-05-29"));
	temp.setEndDate(df.parse("2016-05-31"));	
	temp.setProjectId(1);	
	assertNotNull(temp);
	
	
}

@Test
//testing deletion of an Activity
public void activitydeletion(){
	int activityId = 100;
	System.out.println("Testing Deletion of an activity");
	String d1 = ActivityDao.DeleteActivityGivenActivityId.replace("@id", Integer.toString(activityId));
	assertNull(activityId);
	
}

}
