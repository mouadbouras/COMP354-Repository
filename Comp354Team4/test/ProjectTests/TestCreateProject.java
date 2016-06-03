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
		project.setManagerId(1);
		
		ProjectDao projectDao = new ProjectDao();
		projectDao.InsertProject(project);
		assertNotNull(project);		
	}
	
	@Test
	//testing if manager is able to create an INVALID project with null project name
	//if you set project name to "null" nullPtrException is thrown.
	//If you leave projectName empty, the object is still created (this should fail)
	public void createInvalidProjectName() throws ParseException{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Project project = new Project();
		System.out.println("Testing Creation of an INVALID Project with a NULL/empty PROJECT NAME");
		project.setProjectName("");
		project.setStartDate(df.parse("2017-02-29"));
		project.setEndDate(df.parse("2018-02-29"));					
		project.setManagerId(1);
		
		ProjectDao projectDao = new ProjectDao();
		projectDao.InsertProject(project);
		assertNotNull(project);
		
	}
	@Test
	//testing if manager is able to create an INVALID project with null Start Date
		public void createInvalidProjectStartD() throws ParseException{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			Project project = new Project();
			System.out.println("Testing Creation of an INVALID Project with a null PROJECT Sdate");
			project.setProjectName("UserTest");
			project.setStartDate(df.parse(null));
			project.setEndDate(df.parse("2018-02-29"));					
			project.setManagerId(1);
			
			ProjectDao projectDao = new ProjectDao();
			projectDao.InsertProject(project);
			assertNotNull(project);
			
		}
	@Test
		//testing if manager is able to create an INVALID project with null End Date
				public void createInvalidProjectEndD() throws ParseException{
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					
					Project project = new Project();
					System.out.println("Testing Creation of an INVALID Project with a null PROJECT Edate");
					project.setProjectName("UserTest");
					project.setStartDate(df.parse("2018-02-29"));
					project.setEndDate(df.parse(null));					
					project.setManagerId(1);
					
					ProjectDao projectDao = new ProjectDao();
					projectDao.InsertProject(project);
					assertNotNull(project);
					
				}

}
