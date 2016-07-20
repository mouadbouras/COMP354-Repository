package services;

import java.util.List;

import dao.ActivityDao;
import dao.MemberActivityInfoDao;
import dao.ProjectDao;
import dao.PropertyDao;
import dao.ResourceDao;
import dao.UserDao;
import models.*;
import models.*;

public class TableDataService 
{
	//get the user role, given the user id
	public User GetUser(int userId)
	{
		return UserDao.getInstance().GetUserGivenUserId(userId);	
	}
	
	//get the user role, given the user id
	public User GetUser(String username, String password)
	{
		return UserDao.getInstance().GetUserGivenUsernamePassword(username,password);	
	}
	
	public String[] GetProjectTableColumns()
	{
		return ProjectDao.getInstance().GetProjectColumns();		
	}
	
	public String[][] GetProjectTableData(int userId)
	{
		String[][] tempData = null;
		
		List<Project> projects = ProjectDao.getInstance().GetProjectsGivenManagerId(userId);
		
		tempData = new String[projects.size()][];
		
		for (int i = 0; i < projects.size(); ++i)
		{
			String[] tempRow = ProjectDao.getInstance().returnDataRow(projects.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}
	
	public String[] GetActivityTableColumns(){
		return ActivityDao.getInstance().GetActivityColumns();
	}
	
	public String[][] GetActivityTableData(int projectId){
		String tempData[][] = null;
		
		Project p = ProjectDao.getInstance().getProjectByProjectId(projectId);
		
		List<Activity> activities = new GANTTService().CalculateGanttChartActivityTimes(p);
		
		tempData = new String[activities.size()][];
		for(int i = 0; i<activities.size(); ++i){
			String [] tempRow = ActivityDao.getInstance().returnDataRow(activities.get(i));
			tempData[i] = tempRow;
		}
		
		return tempData;
	}
	
	public String[] GetUserTableColumns(){
		return UserDao.getInstance().GetPMColumns();
	}
	
	public String[][] GetUserTableData(User user){
	
		String tempData[][] = null;
		List<User> users = UserDao.getInstance().GetPMInfor(user);
		tempData = new String[users.size()][];
		
		for(int i = 0; i<users.size(); ++i)
		{
			String [] tempRow = UserDao.getInstance().returnDataRow(users.get(i));
			tempData[i] = tempRow;
		}		
		
		return tempData;
	}
	
	public String[] GetResourceTableColumns()
	{
		return ResourceDao.getInstance().GetResourceColumns();
	}
	
	public String[][] GetResourceTableData(int activityId)
	{
		String[][] tempData = null;
		
		List<Resource> resources = ResourceDao.getInstance().GetResourcesByActivityId(activityId);
		
		tempData = new String[resources.size()][];
		
		for (int i = 0; i < resources.size(); ++i)
		{
			String[] tempRow = ResourceDao.getInstance().returnDataRow(resources.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
	
	public String[] GetMemberActivitiesTableColumns()
	{
		return MemberActivityInfoDao.getInstance().GetMemberActivityInfoColumns();
	}
	
	public String[][] GetMemberActivitiesTableData(int memberId)
	{
		String[][] tempData = null;
		
		List<MemberActivityInfo> memberActivityInfo = MemberActivityInfoDao.getInstance().GetMemberActivityGivenUserId(memberId);
		
		tempData = new String[memberActivityInfo.size()][];
		
		for (int i = 0; i < memberActivityInfo.size(); ++i)
		{
			String[] tempRow = MemberActivityInfoDao.getInstance().returnDataRow(memberActivityInfo.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
	
	public String[] GetPropertyTableColumns()
	{
		return PropertyDao.getInstance().GetPropertyColumns();
	}
	
	public String[][] GetPropertyTableData(int activityId)
	{
		String[][] tempData = null;
		
		List<Property> properties = PropertyDao.getInstance().GetPropertyGivenActivityId(activityId);
		
		tempData = new String[properties.size()][];
		
		for (int i = 0; i < properties.size(); ++i)
		{
			String[] tempRow = PropertyDao.getInstance().returnDataRow(properties.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
}
