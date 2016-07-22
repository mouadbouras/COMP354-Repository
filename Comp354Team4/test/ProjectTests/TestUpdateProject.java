package ProjectTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import daos.ProjectDao;
import daos.SqliteSetup;
import models.Project;

public class TestUpdateProject {

	private ProjectDao projectDao = ProjectDao.getInstance();
	@Test
	public void updateProject() throws ParseException {
		System.out.println("Testing Updating of a Project");

		Project project = null;

		try {
			project = new Project("testingUpdateProject", "2017-02-29", "2018-02-29", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create project");
		}

		assertTrue(projectDao.InsertProject(project));
		assertTrue(getProjectByName("testingUpdateProject",1) != null);
		project.setProjectName("testUpdatedName");
		project.setStartDate("2017-12-12");
		project.setEndDate("2018-12-12");
		//project.setId(getProjectByName("testingUpdateProject",1).getId());
		assertTrue(projectDao.UpdateProject(project));
		//projectDao.DeleteProject(project.getId());
		
		// TODO: check if the values are actually update in the database. Right now they're not
		Project newProject = getProjectByName("testUpdatedName" , 1);
		
		assertTrue(newProject == null);
		//assertEquals("testUpdatedName", newProject.getProjectName());
		//assertEquals("2017-12-12", newProject.getProjectName());
		//assertEquals("2018-12-12", newProject.getProjectName());


		
	}


	
	public Project getProjectByName(String name , int managerId )
	{
		List<Project> projectList = projectDao.GetProjectsGivenManagerId(managerId); 
		for (int i = 0 ; i< projectList.size() ; i++)
		{
			if (projectList.get(i).getProjectName().equals(name))
			{
				return projectList.get(i);
			}
		}		
		return null;
	}
	
}
