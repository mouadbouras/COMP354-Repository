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

import models.Project;
import models.ProjectDao;
import models.SqliteSetup;

public class TestDeleteProject {
	
	private ProjectDao projectDao = new ProjectDao();
	private static String SelectProjectsGivenProjectName = "SELECT * FROM Project WHERE projectName = @name";

	@Test 
	public void deleteProject() throws ParseException{
		System.out.println("Testing Deletion of a Project");
		
		Project project = null;

		try {
			project = new Project("testDelete", "2017-02-29", "2018-02-29", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create project");
		}

		assertTrue(projectDao.InsertProject(project));
		
		assertTrue(projectDao.DeleteProject(project.getId()));	
		
		// TODO uncomment and make it pass
		// check if the project is actually deleted in the database
		/*
		
		List<Project> projects = new ArrayList<Project>();

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			
			String sql = SelectProjectsGivenProjectName.replace("@name", "'testDelete'");

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Project temp = null;
				temp = ProjectDao.mapResultSetToProject(rs);
				projects.add(temp);
			}

			rs.close();
			stmt.close();
			c.close();

		}

		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		System.out.println("Operation done successfully");

		assertTrue(projects.isEmpty());
		 */
	} 
}
