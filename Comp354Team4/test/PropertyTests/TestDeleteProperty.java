package PropertyTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;


import daos.PropertyDao;
import models.Project;
import models.Property;

public class TestDeleteProperty {

	private PropertyDao propertyDao = PropertyDao.getInstance();

	
	@Test
	// testing update property name
	public void deleteProperty() {

		Property property = new Property() ;
		
		String name = "testName" + System.currentTimeMillis(); 

		property.activityId = 1;
		property.propertyName = name;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		propertyDao.InsertProperty(property);
		
		property = getPropertyByActivity(name,1);
				
		assertTrue(propertyDao.DeleteProperty(property.id)); 
		property = getPropertyByActivity(name,1);
		assertEquals(getPropertyByActivity(name,1) , null );
	}
	
	//performs a search for a property using activityid and UNIQUE name (THE NAME MUST BE UNIQUE FOR THIS FUNCTION TO WORK)
	public Property getPropertyByActivity(String name , int activityId )
	{
		List<Property> propertyList = propertyDao.GetPropertyGivenActivityId(activityId); 
		for (int i = 0 ; i< propertyList.size() ; i++)
		{
			if (propertyList.get(i).propertyName.equals(name))
			{
				return propertyList.get(i);
			}
		}		
		return null;
	}
	
}
