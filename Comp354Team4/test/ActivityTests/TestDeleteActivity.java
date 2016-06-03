package ActivityTests;

import static org.junit.Assert.*;

import org.junit.Test;

import models.ActivityDao;

public class TestDeleteActivity {

	@Test
	//testing deletion of an Activity
	public void deleteActivity(){
		int activityId = 1;
		System.out.println("Testing Deletion of an activity");
		String d1 = ActivityDao.DeleteActivityGivenActivityId.replace("@id", Integer.toString(activityId));
		assertNull(activityId);
		
	}
}
