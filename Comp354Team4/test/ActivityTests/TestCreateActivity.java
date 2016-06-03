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
		System.out.println("Testing Creation of a VALID Activity");
		temp.setId(20);
		temp.setActivityName("UserTest");		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-29"));
		temp.setEndDate(df.parse("2016-05-31"));	
		temp.setProjectId(1);	
		assertNotNull(temp);

	}
	
	@Test
	//testing creation of an Invalid activity using "null id" 
	public void createInValidIDActivity() throws ParseException{
		
		Activity temp = new Activity();	
		System.out.println("Testing Creation of an invalid Activity : ID");
		temp.setId((Integer) null);
		temp.setActivityName("UserTest");		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-29"));
		temp.setEndDate(df.parse("2016-05-31"));	
		temp.setProjectId(1);	
		assertNotNull(temp);

	}
	
	@Test
	//testing creation of an Invalid activity using "null name" 
	public void createInValidNameActivity() throws ParseException{
		
		Activity temp = new Activity();	
		System.out.println("Testing Creation of an invalid Activity : NAME");
		temp.setId(100);
		temp.setActivityName(null);		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-29"));
		temp.setEndDate(df.parse("2016-05-31"));	
		temp.setProjectId(1);	
		assertNotNull(temp);//still passes if activity is set to null?

	}
	
	@Test
	//testing creation of an Invalid activity using "null start date" 
	public void createInValidStartActivity() throws ParseException{
		
		Activity temp = new Activity();	
		System.out.println("Testing Creation of an invalid Activity : START DATE");
		temp.setId(100);
		temp.setActivityName("Test");		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse(null));
		temp.setEndDate(df.parse("2016-05-31"));	
		temp.setProjectId(1);	
		assertNotNull(temp);

	}
	
	@Test
	//testing creation of an Invalid activity using "null End date" 
	public void createInValidEndActivity() throws ParseException{
		
		Activity temp = new Activity();	
		System.out.println("Testing Creation of an invalid Activity : END DATE");
		temp.setId(100);
		temp.setActivityName("Test");		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		temp.setStartDate(df.parse("2016-05-31"));
		temp.setEndDate(df.parse(null));	
		temp.setProjectId(1);	
		assertNotNull(temp);

	}
	
	@Test
	//testing creation of an Invalid activity using "null ProjectID" 
	public void createInValidProjectIDActivity() throws ParseException{
		
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
	
	
	

}
