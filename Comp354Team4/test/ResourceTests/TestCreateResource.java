package ResourceTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;


import daos.ResourceDao;
import models.Property;
import models.Resource;


public class TestCreateResource {
	
	private ResourceDao resourceDao = ResourceDao.getInstance();
	
	@Test
	// Test creation of a valid Resource
	public void createValidResource()
	{
		System.out.println("Testing Creation of a VALID Resource");
		Resource resource = new Resource() ;
		
		resource.activityId = 96;
		resource.memberId = 1;
		
		assertTrue(resourceDao.InsertResources(resource));
	}
	
	@Test
	// Test creation of an invalid Resource: invalid activiy id
	public void createInvalidResourceActivityId()
	{
		System.out.println("Testing Creation of an INVALID Resource");
		Resource resource = new Resource();
		
		resource.activityId = 0; //invalid activity Id
		resource.memberId = 1;
		
		assertFalse(resourceDao.InsertResources(resource));
	}

	
	@Test
	// Test creation of an invalid Resource: invalid resource id
	public void createInvalidResourceMemberId()
	{
		System.out.println("Testing Creation of an INVALID Resource");
		Resource resource = new Resource();
		
		resource.activityId = 96; //invalid member Id
		resource.memberId = -1;
		
		assertFalse(resourceDao.InsertResources(resource));
	}


}
