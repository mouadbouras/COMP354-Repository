package ResourceTests;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

import daos.ResourceDao;
import models.Resource;

public class TestDeleteResource {
	
	private ResourceDao resourceDao = ResourceDao.getInstance();

	@Test
	// Test creation of a valid Resource
	public void deleteResource()
	{
		System.out.println("Testing Deletion of a Resource");
		Resource resource = new Resource() ;
		
		resource.activityId = 96;
		resource.memberId = 1;
		assertTrue(resourceDao.InsertResources(resource));
		
		List<Resource> resources = resourceDao.GetResourcesByActivityId(resource.activityId);		
		int size = resources.size();		
		System.out.println("There are " + size + "resources for activity id " + resource.activityId);			
		String[] temp = resourceDao.returnDataRow(resources.get(size-1));
		String id = temp[0];
		int resource_id = Integer.parseInt(id);		
		System.out.println("Resource Id is " + id);		
		assertTrue(resourceDao.DeleteResource(resource_id));
		
		List<Resource> resources_del = resourceDao.GetResourcesByActivityId(resource.activityId);
		int size_del = resources_del.size();
		boolean success = false;
		if(size == (size_del+1))
		{
			success = true;
			System.out.println("Success!");
		}			
		
		assertTrue(success);
		
	}

}
