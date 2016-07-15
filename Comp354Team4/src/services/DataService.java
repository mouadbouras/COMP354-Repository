package services;

import java.util.List;

import dao.ActivityDao;
import dao.ProjectDao;
import dao.UserDao;
import models.*;
import models.*;

public class DataService 
{
	private UserDao userDao = new UserDao();
	private ProjectDao projectDao = new ProjectDao();
	private ActivityDao activityDao = new ActivityDao();
	
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
	
	//write by Gu
	public String[] GetUserTableColumns()
	{
		return this.userDao.GetPMColumns();
	}
	
	public String[][] GetUserTableData(User user){
	
		String tempData[][] = null;
		List<User> users = this.userDao.GetMembersGivenManagerId(user.getId());
		tempData = new String[users.size()][];
		
		for(int i = 0; i<users.size(); ++i)
		{
			String [] tempRow = this.userDao.returnDataRow(users.get(i));
			tempData[i] = tempRow;
		}		

		
		return tempData;
	}
	
	public List<Activity> GetActivitiesGivenProjectId(int projectId)
	{
		return this.activityDao.GetActivitiesGivenProjectId(projectId);
	};
}
