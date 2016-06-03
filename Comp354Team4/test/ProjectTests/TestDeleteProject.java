package ProjectTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Project;
import models.ProjectDao;

public class TestDeleteProject {

	@Test 
	public void deletePro() throws ParseException{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Project project = new Project();
		System.out.println("Testing Creation of a Project with all its information");
		project.setProjectName("TestingDeletion");
		project.setId(11);
		project.setStartDate(df.parse("2017-02-29"));
		project.setEndDate(df.parse("2018-02-29"));					
		project.setManagerId(1);
		
		ProjectDao projectDao = new ProjectDao();
		projectDao.InsertProject(project); //project created successfully 
		System.out.println("Testing Deletion of a Project");
		//not working... guess it only works when clicked on "delete" as an event
		projectDao.DeleteProject(11);
		assertNull(projectDao);//project should be null if deleted		  
	} 
	
	
}
