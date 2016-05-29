package ActivityTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Activity;

public class TestCreateActivity {

	@Test
	//testing creation of a Valid activity
	public void createValidActivity() throws ParseException{
		
		Activity temp = new Activity();	
		System.out.println("Testing Creation of a Activity");
		temp.setId(100);
		temp.setActivityName("UserTest");		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-29"));
		temp.setEndDate(df.parse("2016-05-31"));	
		temp.setProjectId(1);	
		assertNotNull(temp);
		
		
	}

}
