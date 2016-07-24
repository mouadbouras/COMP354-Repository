package ProjectTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import daos.ProjectDao;
import daos.SqliteSetup;
import models.Project;

public class TestDeleteProject {
	
	private ProjectDao projectDao = ProjectDao.getInstance();
	//private static String SelectProjectsGivenProjectName = "SELECT * FROM Project WHERE projectName = @uniqueName";

	@Test 
	public void deleteProject() throws ParseException{
		System.out.println("Testing Deletion of a Project");
		
		Project project = null;
		String toDeleteName  = "testingDeleteProject" + System.currentTimeMillis();
		System.out.println(toDeleteName);
		try {
			project = new Project(toDeleteName, "2017-02-29", "2018-02-29", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create project");
		}

		assertTrue(projectDao.InsertProject(project));
		
		
		Project newProj = getProjectByName(toDeleteName, 1);
		assertTrue(projectDao.DeleteProject(newProj.getId()));		
		
		newProj = getProjectByName(toDeleteName, 1);
		assertTrue( newProj == null );
		// DONE
		
	} 
	
	public Project getProjectByName(String name , int managerId )
	{
		List<Project> projectList = projectDao.GetProjectsGivenManagerId(managerId); 
		
		for (int i = 0 ; i< projectList.size() ; i++)
		{
			if (projectList.get(i).getProjectName().equals(name))
			{
				System.out.println("found " + projectList.get(i).getProjectName());
				return projectList.get(i);
			}
		}
		
		return null;
	}
	
}
