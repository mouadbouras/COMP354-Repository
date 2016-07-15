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
	private UserDao userDao = new UserDao();
	private ProjectDao projectDao = new ProjectDao();
	private ActivityDao activityDao = new ActivityDao();
	private ResourceDao resourceDao = new ResourceDao();
	private MemberActivityInfoDao memberActivityInfoDao = new MemberActivityInfoDao();
	private PropertyDao propertyDao = new PropertyDao();
	
	//get the user role, given the user id
	public User GetUser(int userId)
	{
		return this.userDao.GetUserGivenUserId(userId);	
	}
	
	//get the user role, given the user id
	public User GetUser(String username, String password)
	{
		return this.userDao.GetUserGivenUsernamePassword(username,password);	
	}
	
	public String[] GetProjectTableColumns()
	{
		return this.projectDao.GetProjectColumns();		
	}
	
	public String[][] GetProjectTableData(int userId)
	{
		String[][] tempData = null;
		
		List<Project> projects = this.projectDao.GetProjectsGivenManagerId(userId);
		
		tempData = new String[projects.size()][];
		
		for (int i = 0; i < projects.size(); ++i)
		{
			String[] tempRow = this.projectDao.returnDataRow(projects.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}
	
	public String[] GetActivityTableColumns(){
		return this.activityDao.GetActivityColumns();
	}
	
	public String[][] GetActivityTableData(int projectId){
		String tempData[][] = null;
		
		Project p = this.projectDao.getProjectByProjectId(projectId);
		
		List<Activity> activities = new GANTTService().CalculateGanttChartActivityTimes(p);
		
		tempData = new String[activities.size()][];
		for(int i = 0; i<activities.size(); ++i){
			String [] tempRow = this.activityDao.returnDataRow(activities.get(i));
			tempData[i] = tempRow;
		}
		
		return tempData;
	}
	
	public String[] GetUserTableColumns(){
		return this.userDao.GetPMColumns();
	}
	
	public String[][] GetUserTableData(User user){
	
		String tempData[][] = null;
		List<User> users = this.userDao.GetPMInfor(user);
		tempData = new String[users.size()][];
		
		for(int i = 0; i<users.size(); ++i)
		{
			String [] tempRow = this.userDao.returnDataRow(users.get(i));
			tempData[i] = tempRow;
		}		
		
		return tempData;
	}
	
	public String[] GetResourceTableColumns()
	{
		return this.resourceDao.GetResourceColumns();
	}
	
	public String[][] GetResourceTableData(int activityId)
	{
		String[][] tempData = null;
		
		List<Resource> resources = this.resourceDao.GetResourcesByActivityId(activityId);
		
		tempData = new String[resources.size()][];
		
		for (int i = 0; i < resources.size(); ++i)
		{
			String[] tempRow = this.resourceDao.returnDataRow(resources.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
	
	public String[] GetMemberActivitiesTableColumns()
	{
		return this.memberActivityInfoDao.GetMemberActivityInfoColumns();
	}
	
	public String[][] GetMemberActivitiesTableData(int memberId)
	{
		String[][] tempData = null;
		
		List<MemberActivityInfo> memberActivityInfo = this.memberActivityInfoDao.GetMemberActivityGivenUserId(memberId);
		
		tempData = new String[memberActivityInfo.size()][];
		
		for (int i = 0; i < memberActivityInfo.size(); ++i)
		{
			String[] tempRow = this.memberActivityInfoDao.returnDataRow(memberActivityInfo.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
	
	public String[] GetPropertyTableColumns()
	{
		return this.propertyDao.GetPropertyColumns();
	}
	
	public String[][] GetPropertyTableData(int activityId)
	{
		String[][] tempData = null;
		
		List<Property> properties = this.propertyDao.GetPropertyGivenActivityId(activityId);
		
		tempData = new String[properties.size()][];
		
		for (int i = 0; i < properties.size(); ++i)
		{
			String[] tempRow = this.propertyDao.returnDataRow(properties.get(i));
			tempData[i] = tempRow;			
		}
		
		return tempData;
	}	
}
