package services;

import java.util.List;

import data.*;
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
}
