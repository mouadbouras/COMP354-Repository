package ActivityTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Activity;
import dao.ActivityDao;
import dao.ActivityDependencyDao;
import models.Project;
import dao.ProjectDao;

public class TestActivityDependency {

	private ActivityDao activityDao = ActivityDao.getInstance();
	private ActivityDependencyDao dependencyDao = ActivityDependencyDao.getInstance();

	@Test
	// testing creation of a Valid activity
	public void createValidDependency() {

		System.out.println("Testing Creation of a VALID activity dependency");
		Activity activity1 = null;
		Activity activity2 = null;
		
		int id1 = 80;
		int id2 = 81;

		try {
			activity1 = new Activity(id1, "CreateActivityTest", 5, "2016-05-29", "2016-05-31", 1);
			activity2 = new Activity(id2, "CreateActivityTest", 5, "2016-05-29", "2016-05-31", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create activity");
		}
		
		assertTrue(activityDao.InsertActivity(activity1));
		assertTrue(activityDao.InsertActivity(activity2));
		
		//assertTrue(dependencyDao.AddDependency(id1,id2));
		//TODO... 
		
		activityDao.DeleteActivity(id1);
		activityDao.DeleteActivity(id2);
	}

	@Test
	// testing duplicate dependency detection
	public void verifyDuplicateDependency() {

		System.out.println("Testing duplicate activity detection");
		Activity activity1 = null;
		Activity activity2 = null;
		
		int id1 = 80;
		int id2 = 81;

		try {
			activity1 = new Activity(id1, "CreateActivityTest", 5, "2016-05-29", "2016-05-31", 1);
			activity2 = new Activity(id2, "CreateActivityTest", 5, "2016-05-29", "2016-05-31", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create activity");
		}
		
		assertTrue(activityDao.InsertActivity(activity1));
		assertTrue(activityDao.InsertActivity(activity2));
		
		//assertTrue(dependencyDao.addDependency(id1,id2));
		
		//boolean dependencyExists = dependencyDao.CheckDependencyExists(id1,id2) ;
//    	if(!dependencyExists)
//    	{
//    		fail("The dependency should exist!");
//    	}
//		
		activityDao.DeleteActivity(id1);
		activityDao.DeleteActivity(id2);
	}
}
