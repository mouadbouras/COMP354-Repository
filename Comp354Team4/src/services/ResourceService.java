package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import daos.ActivityDao;
import daos.ActivityDependencyDao;
import daos.ResourceDao;
import daos.UserDao;
import models.Activity;
import models.ActivityDependency;
import models.Project;
import models.Resource;
import models.User;

public class ResourceService {
	
	public String[] getAddableResources(int managerId, int activityId)
	{
		HashMap<Integer, Resource> addableResources = new HashMap<Integer, Resource>();
		
		//get all resources available for this project manager user;
		List<User> addableUsers = UserDao.getInstance().GetMembersGivenManagerId(managerId);
		
		//get all resources for this activity
		List<Resource> alreadyAddedUsers = ResourceDao.getInstance().GetResourcesByActivityId(activityId);		

		for (User u : addableUsers)
		{
			Resource temp = new Resource();
			temp.activityId = activityId;
			temp.memberId = u.getId();
			temp.firstname = u.getFirstName();
			temp.lastname = u.getLastName();
			addableResources.put(temp.memberId, temp);
		}
		
		for (Resource r : alreadyAddedUsers)
		{
			addableResources.remove(r.memberId);
		}			

		List<Resource> resources = new ArrayList<Resource>(addableResources.values());
		
		if (resources.size() == 0)
			return null;
		
		List<String> addableResourceStrings = new ArrayList<String>();
		
		for (Resource r : resources)
		{
			String temp = "{" + r.memberId + "} : " + r.firstname + " " + r.lastname;
			addableResourceStrings.add(temp);
		}
		
		String[] temp = new String[resources.size()];		
		temp = addableResourceStrings.toArray(temp);
		return temp;
	}
}
