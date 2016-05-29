package ProjectTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Project;
import models.ProjectDao;
import models.User;
import views.State;

public class TestCreateProject {

	@Test
	//testing if manager is able to create a VALID project
	public void createValidProject() throws ParseException{
		
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
	//testing if manager is able to create an INVALID project
	public void createInvalidProject() throws ParseException{
		//TODO
	}

}
