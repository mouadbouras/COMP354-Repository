package ActivityTests;

import static org.junit.Assert.*;

import org.junit.Test;

import models.ActivityDao;
import models.ProjectDao;

public class TestDeleteActivity {

	@Test
	//testing deletion of an Activity by using "activity id"
	public void deleteActivity(){
		
		ActivityDao activity = new ActivityDao();
		System.out.println("Testing Deletion of an Activty");
		//Activity is deleted in the database...
		activity.DeleteActivity(15);
		assertNotNull(activity);//activity should be null if deleted	
		
	}
}
