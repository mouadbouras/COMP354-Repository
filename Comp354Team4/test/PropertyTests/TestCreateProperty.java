package PropertyTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;


import daos.PropertyDao;

import models.Property;

public class TestCreateProperty {

	private PropertyDao propertyDao = PropertyDao.getInstance();

	@Test
	// testing creation of a Valid activity
	public void createValidProperty() {

		System.out.println("Testing Creation of a VALID Property");
		Property property = new Property() ;
		
		property.activityId = 1;
		property.propertyName = "testName" ;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		//propertyDao.InsertProperty(property);
		
		assertTrue(propertyDao.InsertProperty(property));//userDao.InsertUser(temp));
	}

	@Test
	// testing creation of an Invalid Property name using "invalid id"
	public void createInvalidPropertyId() {
		System.out.println("Testing Creation of a VALID User");
		Property property = new Property() ;
		
		property.id = -10;
		property.activityId = 1;
		property.propertyName = "testName" ;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		//propertyDao.InsertProperty(property);
		
		assertFalse(propertyDao.InsertProperty(property));

	}


	@Test
	// testing creation of an Invalid Property name using "invalid id"
	public void createInvalidPropertyActivityId() {
		System.out.println("Testing Creation of a VALID User");
		Property property = new Property() ;
		
		property.activityId = -1;
		property.propertyName = "testName" ;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		//propertyDao.InsertProperty(property);
		
		assertFalse(propertyDao.InsertProperty(property));

	}


	@Test
	// testing creation of an Invalid Property name using "invalid name"
	public void createInvalidPropertyName() {
		System.out.println("Testing Creation of a VALID User");
		Property property = new Property() ;
		
		property.activityId = 1;
		property.propertyName = null ;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		//propertyDao.InsertProperty(property);
		
		assertFalse(propertyDao.InsertProperty(property));

	}
	

	@Test
	// testing creation of an Invalid Property name using "invalid description"
	public void createInvalidPropertyDescription() {
		System.out.println("Testing Creation of a VALID User");
		Property property = new Property() ;
		
		property.activityId = 1;
		property.propertyName = "testName" ;
		property.propertyDescription = null;
		property.propertyText = "testText";

		//propertyDao.InsertProperty(property);
		
		assertFalse(propertyDao.InsertProperty(property));

	}
	
	@Test
	// testing creation of an Invalid Property name using "invalid text"
	public void createInvalidPropertyText() {
		System.out.println("Testing Creation of a VALID User");
		Property property = new Property() ;
		
		property.activityId = 1;
		property.propertyName = "testName" ;
		property.propertyDescription = "testDecription";
		property.propertyText = null;

		//propertyDao.InsertProperty(property);
		
		assertFalse(propertyDao.InsertProperty(property));

	}
	
	
	
}
