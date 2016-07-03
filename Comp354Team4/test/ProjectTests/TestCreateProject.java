package ProjectTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Project;
import dao.ProjectDao;
import models.User;
import services.StateService;

public class TestCreateProject {

	private ProjectDao projectDao = new ProjectDao();

	@Test
	// testing if manager is able to create a VALID project
	public void createValidProject() throws ParseException {
		System.out.println("Testing Creation of a Project with all its information");
		Project project = null;

		try {
			project = new Project("testCreate", "2017-02-29", "2018-02-29", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create project");
		}

		assertTrue(projectDao.InsertProject(project));
		projectDao.DeleteProject(project.getId()); 
	}

	@Test
	// testing if manager is able to create an INVALID project with null project
	// name
	public void createInvalidProjectName() {
		System.out.println("Testing Creation of an INVALID Project with a NULL/empty PROJECT NAME");
		try {
			Project project = new Project("", "2017-02-29", "2018-02-29", 1);
			fail("Expected IllegalArgumentException while creating project");
		} catch (Exception e) {
			assert (e instanceof IllegalArgumentException);
		}
	}

	@Test
	// testing if manager is able to create an INVALID project with null Start
	// Date
	public void createInvalidProjectStartD() {
		System.out.println("Testing Creation of an INVALID Project with a null PROJECT Sdate");
		try {
			Project project = new Project("test2", null, "2018-02-29", 1);
			fail("Expected ParseException while creating project");
		} catch (Exception e) {
			assert (e instanceof ParseException);
		}
	}

	@Test
	// testing if manager is able to create an INVALID project with null End
	// Date
	public void createInvalidProjectEndD() throws ParseException {
		System.out.println("Testing Creation of an INVALID Project with a null PROJECT Edate");
		try {
			Project project = new Project("test3", "2017-02-29", null, 1);
			fail("Expected ParseException while creating project");
		} catch (Exception e) {
			assert (e instanceof ParseException);
		}

	}

}
