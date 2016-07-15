package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dao.ActivityDao;
import dao.ActivityDependencyDao;
import models.Activity;
import models.ActivityDependency;
import models.Project;

public class GANTTService {
	
	private HashMap<Integer, Activity> ganttHash = null;
	private List<ActivityDependency> dependencies = null;
	private List<Activity> activities = null;
	private Date projectStartDate;
	
	public List<Activity> CalculateGanttChartActivityTimes(Project p)
	{
		this.activities = new ActivityDao().GetActivitiesGivenProjectId(p.getId());
		this.dependencies = new ActivityDependencyDao().GetDependencyIds(p.getId());
		this.projectStartDate = p.getStartDate();
		this.ganttHash = new HashMap<Integer, Activity>();
		
		for(Activity a : activities)
		{
			a.setStartDate((Date)null);
			a.setEndDate((Date)null);
			
			ganttHash.put(a.getId(), a);			
		}
		
		for(Activity a : activities)
		{
			recursivelyGetStartEndDate(a.getId());			
		}		
		
		return new ArrayList<Activity>(this.ganttHash.values());
	}
	
	private Date recursivelyGetStartEndDate(int id)
	{
		Date latestEndDate = null; //latestEndDate of the previous activities, making it the start date
		
		Activity a = this.ganttHash.get(id);
		int duration = a.getDuration();
		
		if (a.getEndDate() != null)
			return a.getEndDate();
		
		for(ActivityDependency d : dependencies)
		{
			if (d.activityId == id)
			{
				Date tempEndDate = recursivelyGetStartEndDate(d.dependeeId);
				
				if (latestEndDate == null)
					latestEndDate = tempEndDate;
				else 
				{
					if (tempEndDate.after(latestEndDate))
					{
						latestEndDate = tempEndDate;
					}					
				}
			}			
		}
		
		//this activity has no dependencies, it can start when the project starts
		if (latestEndDate == null)
		{
			latestEndDate = this.projectStartDate;
		}
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(latestEndDate); 
		c.add(Calendar.DATE, duration);
		
		Date endDate = c.getTime();
		
		a.setStartDate(latestEndDate);
		a.setEndDate(endDate);
		
		this.ganttHash.replace(id, a);
		
		return endDate;		
	}
}
