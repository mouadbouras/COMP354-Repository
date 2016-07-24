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

public class TestEditProperty {

	private PropertyDao propertyDao = PropertyDao.getInstance();

	
	@Test
	// testing update property name
	public void updatePropertyName() {

		Property property = new Property() ;
		
		String name = "testName" + System.currentTimeMillis(); 

		property.id = 99;
		property.activityId = 1;
		property.propertyName = name;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		propertyDao.InsertProperty(property);
		
		property = getPropertyByActivity(name,1);
		
		property.propertyName = name + "1";
		
		assertTrue(propertyDao.UpdateProperty(property)); 
		

		assertEquals(getPropertyByActivity(name+"1",1).propertyName , name + "1" );
	}

	@Test
	// testing update property description
	public void updatePropertyDescription() {
		
		Property property = new Property() ;
		
		String name = "testName" + System.currentTimeMillis(); 
		
		property.id = 99;
		property.activityId = 1;
		property.propertyName = name;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		propertyDao.InsertProperty(property);
		
		property = getPropertyByActivity(name,1);
		
		property.propertyDescription = "testDescriptionUpdated";
		
		assertTrue(propertyDao.UpdateProperty(property)); 
		

		assertEquals(getPropertyByActivity(name,1).propertyDescription ,  "testDescriptionUpdated" );

	}


	@Test
	// testing update property text
	public void updatePropertyText() {
		Property property = new Property() ;
		
		String name = "testName" + System.currentTimeMillis(); 
		
		property.id = 99;
		property.activityId = 1;
		property.propertyName = name;
		property.propertyDescription = "testDescription";
		property.propertyText = "testText";

		propertyDao.InsertProperty(property);
		
		property = getPropertyByActivity(name,1);
		
		property.propertyText = "testTextUpdated";
		
		assertTrue(propertyDao.UpdateProperty(property)); 
		

		assertEquals(getPropertyByActivity(name,1).propertyText ,  "testTextUpdated" );

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
