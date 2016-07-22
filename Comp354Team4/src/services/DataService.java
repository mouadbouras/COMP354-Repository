package services;

import java.util.List;

import daos.ActivityDao;
import daos.ProjectDao;
import daos.UserDao;
import models.*;
import models.*;

public class DataService 
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
	
	//write by Gu
	public String[] GetUserTableColumns()
	{
		return UserDao.getInstance().GetPMColumns();
	}
	
	public String[][] GetUserTableData(User user){
	
		String tempData[][] = null;
		List<User> users = UserDao.getInstance().GetMembersGivenManagerId(user.getId());
		tempData = new String[users.size()][];
		
		for(int i = 0; i<users.size(); ++i)
		{
			String [] tempRow = UserDao.getInstance().returnDataRow(users.get(i));
			tempData[i] = tempRow;
		}		

		
		return tempData;
	}
	
	public List<Activity> GetActivitiesGivenProjectId(int projectId)
	{
		return ActivityDao.getInstance().GetActivitiesGivenProjectId(projectId);
	};
}
