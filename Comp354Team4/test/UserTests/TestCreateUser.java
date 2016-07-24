package UserTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;


import daos.UserDao;

import models.User;

public class TestCreateUser {

	private UserDao userDao = UserDao.getInstance();

	@Test
	// testing creation of a Valid activity
	public void createValidUser() {

		System.out.println("Testing Creation of a VALID User");
		User temp ;

		//try {
			temp = new User();
			temp.setUsername("TestUsername1");
			temp.setPassword("TestPassword1");
			temp.setFirstName("TestFirstName");
			temp.setLastName("TestLastName");
			temp.setRole(1);


		
		assertTrue(userDao.InsertUser(temp));
	}

	@Test
	// testing creation of an Invalid User name using "null name"
	public void createInValidUserName() {
		System.out.println("Testing Creation of an invalid Activity : USERNAME");
		User temp = null;

		try {
			temp = new User();
			temp.setUsername("TestUsername");
			 userDao.InsertUser(null);
			fail("Expected IllegalArgumentException while creating project");
		} catch (Exception e) {
			assert (e instanceof IllegalArgumentException);
		}

	}

	@Test
	// testing creation of an Invalid User password using "null name"
	public void createInValidUserPassword() {
		System.out.println("Testing Creation of an invalid Activity : PASSWORD");
		User temp = null;

		try {
			temp = new User();
			temp.setPassword("TestPassword");
			 userDao.InsertUser(null);
			fail("Expected IllegalArgumentException while creating project");
		} catch (Exception e) {
			assert (e instanceof IllegalArgumentException);
		}

	}
	
//	@Test
//	// testing creation of an Invalid activity using "null start date"
//	public void createInValidStartActivity() {		
//		System.out.println("Testing Creation of an invalid Activity : START DATE");
//		User temp = null;
//		
//		try {
//			temp = new Activity(100, "test", 5, null, "2016-05-31", 1);
//			fail("Expected ParseException while creating project");
//		} catch (Exception e) {
//			assert (e instanceof ParseException);
//		}
//
//	}
	
//	@Test
//	// testing creation of an Invalid activity using a negative duration
//	public void createInValidDuration() {		
//		System.out.println("Testing Creation of an invalid Activity : START DATE");
//		Activity temp = null;
//		
//		try {
//			temp = new Activity(100, "test", -1, null, "2016-05-31", 1);
//			fail("Expected ParseException while creating project");
//		} catch (Exception e) {
//			assert (e instanceof IllegalArgumentException);
//		}
//
//	}
//	
//	@Test
//	// testing creation of an Invalid activity using "null end date"
//	public void createInValidEndActivity() {		
//		System.out.println("Testing Creation of an invalid Activity : END DATE");
//		Activity temp = null;
//		
//		try {
//			temp = new Activity(100, "test", 5, "2016-05-31", null, 1);
//			fail("Expected ParseException while creating project");
//		} catch (Exception e) {
//			assert (e instanceof ParseException);
//		}
//
//	}
//
//	@Test
//	// testing creation of an Invalid activity using "null ProjectID"
//	public void createInValidProjectIDActivity() {
//
//		Activity temp = null;
//		int id = -10;
//		
//		try {
//			System.out.println("Testing Creation of an invalid Activity : PROJECT ID");
//			temp = new Activity(100,"UserTest", "description", 5, "2016-05-29","2016-05-31", id);
//			fail("Expected ParseException while creating project");
//
//		}
//		 catch (Exception e) {
//			 System.out.println("Failed to create acitivity with invalid ID");
//			assert (e instanceof ParseException);
//			
//		}
//	}

	
}
