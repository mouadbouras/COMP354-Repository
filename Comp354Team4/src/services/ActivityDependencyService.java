package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.ActivityDao;
import dao.ActivityDependencyDao;
import models.Activity;
import models.ActivityDependency;

public class ActivityDependencyService 
{
	private boolean checkIfThisActivityIsParentToSelectedActivity(HashMap<Integer, List<Integer>> dependencyGraph, int activityId, int selectedId)
	{
		if (activityId == selectedId)
			return true;
		
		List<Integer> dependencies = dependencyGraph.get(activityId);
		
		if (dependencies.size() == 0)
			return false;
		
		for(Integer i : dependencies)
		{
			if (checkIfThisActivityIsParentToSelectedActivity(dependencyGraph, i, selectedId))
			{
				return true;
			};
		}		
		
		return false;
	}
	
	public String[] GetAddableDependencies(int projectId, int activityId)
	{
		//get the activities of a project		
		List<Activity> activities = ActivityDao.getInstance().GetActivitiesGivenProjectId(projectId);
		//get the dependencies of the activities of that project
		List<ActivityDependency> dependencies = ActivityDependencyDao.getInstance().GetDependencyIds(projectId);
		
		//init a list of all dependencies that can be added without creating a cycle in the graph
		List<String> addables = new ArrayList<String>();
		
		//build a hashmap
		HashMap<Integer, List<Integer>> dependencyGraph = new HashMap<Integer, List<Integer>>();
		
		//for each activity, build a list of what it is dependent on
		for(Activity a : activities)
		{
			dependencyGraph.put(a.getId(), new ArrayList<Integer>());
		}		
		//build the hashmap with dependencies
		for(ActivityDependency d : dependencies)
		{
			List<Integer> ptr = dependencyGraph.get(d.activityId);
			ptr.add(d.dependeeId);
			dependencyGraph.replace(d.activityId, ptr);
		}			
		
		//if A is dependent on B, then B cannot be added as depending on A
		for (Activity a : activities)
		{
			if (a.getId() != activityId)
			{
				//check for each activity in the project (activities without other dependencies are "free floating" and can be added as a dependency
				if (!checkIfThisActivityIsParentToSelectedActivity(dependencyGraph, a.getId(), activityId))
				{
					String temp = Integer.toString(a.getId());
					addables.add(temp);
				}				
			}			
		}
		
		//remove addable dependencies already added
		List<Integer> alreadyAdded = dependencyGraph.get(activityId);
		if (alreadyAdded.size() != 0)
		{
			for (Integer i: alreadyAdded)
			{
				addables.remove(i.toString());
			}			
		}
		
		if (addables.size() == 0)
		{
			return null;
		}
		
		//to string and return
		String[] temp = new String[addables.size()];		
		return addables.toArray(temp);		
	}
	
	public String[] GetRemovableDependencies(int projectId, int activityId)
	{
		List<ActivityDependency> dependencies = ActivityDependencyDao.getInstance().GetDependencyIdsForActivity(activityId);	
		
		List<String> removableDependencies = new ArrayList<String>();
		
		for (ActivityDependency a : dependencies)
		{
			if (a.activityId == activityId)
			{
				removableDependencies.add(Integer.toString(a.dependeeId));
			}			
		}
		
		if (removableDependencies.size() == 0)
		{
			return null;
		}
		
		String[] template = new String[removableDependencies.size()];
		return removableDependencies.toArray(template);
	}

}
