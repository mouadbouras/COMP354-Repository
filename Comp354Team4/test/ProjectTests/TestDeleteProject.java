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
	public void deleteProject() throws ParseException{
		
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Project project = new Project();
		System.out.println("Testing Creation of a Project");
		project.setProjectName("TestingDeletion");
		project.setId(11);
		project.setStartDate(df.parse("2017-02-29"));
		project.setEndDate(df.parse("2018-02-29"));					
		project.setManagerId(1);
		ProjectDao projectDao = new ProjectDao();
		projectDao.InsertProject(project); //project created successfully */
		
		ProjectDao projectDao2 = new ProjectDao();
		System.out.println("Testing Deletion of a Project");
		//Project is deleted in the database but test still fails...
		projectDao2.DeleteProject(8);
		assertNull(projectDao2);//project should be null if deleted		  
	} 
	
	
}
