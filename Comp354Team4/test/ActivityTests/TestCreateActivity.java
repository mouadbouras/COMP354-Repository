package ActivityTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Activity;
import models.ActivityDao;
import models.Project;
import models.ProjectDao;

public class TestCreateActivity {

	private ActivityDao activityDao = new ActivityDao();

	@Test
	// testing creation of a Valid activity
	public void createValidActivity() {

		System.out.println("Testing Creation of a VALID Activity");
		Activity temp = null;

		try {
			temp = new Activity(20, "CreateActivityTest", "2016-05-29", "2016-05-31", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create activity");
		}
		
		assertTrue(activityDao.InsertActivity(temp));
		activityDao.DeleteActivity(20);
	}

	@Test
	// testing creation of an Invalid activity using "null name"
	public void createInValidNameActivity() {
		System.out.println("Testing Creation of an invalid Activity : NAME");
		Activity temp = null;

		try {
			temp = new Activity(100, null, "2016-05-29", "2016-05-31", 1);
			fail("Expected IllegalArgumentException while creating project");
		} catch (Exception e) {
			assert (e instanceof IllegalArgumentException);
		}

	}

	@Test
	// testing creation of an Invalid activity using "null start date"
	public void createInValidStartActivity() {		
		System.out.println("Testing Creation of an invalid Activity : START DATE");
		Activity temp = null;
		
		try {
			temp = new Activity(100, "test", null, "2016-05-31", 1);
			fail("Expected ParseException while creating project");
		} catch (Exception e) {
			assert (e instanceof ParseException);
		}

	}
	
	@Test
	// testing creation of an Invalid activity using "null end date"
	public void createInValidEndActivity() {		
		System.out.println("Testing Creation of an invalid Activity : END DATE");
		Activity temp = null;
		
		try {
			temp = new Activity(100, "test", "2016-05-31", null, 1);
			fail("Expected ParseException while creating project");
		} catch (Exception e) {
			assert (e instanceof ParseException);
		}

	}

	/*
	// Can we have an id = 0? That could be tested. This test should be changed though

	@Test
	// testing creation of an Invalid activity using "null ProjectID"
	public void createInValidProjectIDActivity() {

		Activity temp = new Activity();
		System.out.println("Testing Creation of an invalid Activity : PROJECT ID");
		temp.setId(100);
		temp.setActivityName("UserTest");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-29"));
		temp.setEndDate(df.parse("2016-05-31"));
		temp.setProjectId((Integer) null);
		assertNotNull(temp);

	}
*/
}
