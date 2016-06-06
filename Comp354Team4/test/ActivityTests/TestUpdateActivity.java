package ActivityTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.junit.Test;

import models.Activity;
import models.ActivityDao;
import models.SqliteSetup;

public class TestUpdateActivity {
	
	private ActivityDao activityDao = new ActivityDao();

	@Test
	//testing update of an existing activity by using the "activity id"
	public void UpdateActivity() {

		System.out.println("Testing updating an Activty");
		Activity activity = null;

		try {
			activity = new Activity(20, "testDeleteActivity", "2016-05-29", "2016-05-31", 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create activity");
		}
		
		assertTrue(activityDao.InsertActivity(activity));
		
		activity.setActivityName("testUpdatedName");
		try {
			activity.setStartDate("2017-12-12");
			activity.setEndDate("2018-12-12");
		} catch (ParseException e) {
			e.printStackTrace();
			fail("Failed due to parse error while updating activity");
		}
		
		assertTrue(activityDao.UpdateActivity(activity));
		
		// TODO: check if the values are actually updated in the database. Right now they're not
		activityDao.DeleteActivity(20);
		
	}	
}
