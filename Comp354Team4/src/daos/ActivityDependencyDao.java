package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.ActivityDependency;

public class ActivityDependencyDao 
{
	
	private static String SelectActivityDependencyForProject = "SELECT d.* FROM ActivityDependency d INNER JOIN Activity a ON d.activityId = a.id WHERE a.projectId = @projectId AND d.isRemoved = 0 AND a.isRemoved = 0";
	
	private static String SelectActivityDependencyForActivity = "SELECT d.* FROM ActivityDependency d INNER JOIN Activity a ON d.activityId = a.id WHERE d.activityId = '@activityId' AND d.isRemoved = 0 AND a.isRemoved = 0;";
	
	private static String InsertActivityDependency = "INSERT INTO ActivityDependency (activityId, dependeeActivityId) VALUES ('@activityId', '@dependeeActivityId');";
	
	private static String RemoveActivityDependency = "UPDATE ActivityDependency SET IsRemoved = 1 WHERE activityId = @activityId AND dependeeActivityId = @dependeeActivityId";
	
	private static String RemoveAllActivityDependencyForActivity = "UPDATE ActivityDependency SET IsRemoved = 1 WHERE activityId = @activityId OR dependeeActivityId = @activityId";
	
	private static ActivityDependencyDao dao = null;
	
	private ActivityDependencyDao(){}
	
	public static ActivityDependencyDao getInstance()
	{
		if (dao == null)
			dao = new ActivityDependencyDao();
		
		return dao;
	}
	
	//get the list of activities on which the given activity depends
	public List<ActivityDependency> GetDependencyIds(int projectId)
	{
		List<ActivityDependency> dependencies = new ArrayList<ActivityDependency>();
		
		String sql = ActivityDependencyDao.SelectActivityDependencyForProject.replace("@projectId", Integer.toString(projectId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try 
		{
			while (rs.next()) 
			{
			    ActivityDependency temp = null;
				temp = mapResultSetToActivityDependency(rs);
				dependencies.add(temp);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return dependencies;		
	}
	
	public void AddDependency(int activityId, int dependentId)
	{
		String sql = ActivityDependencyDao.InsertActivityDependency.
				replace("@activityId", Integer.toString(activityId)).
				replace("@dependeeActivityId", Integer.toString(dependentId));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);		
	}
	
	public void RemoveDependency(int activityId, int dependentId)
	{
		String sql = ActivityDependencyDao.RemoveActivityDependency.
				replace("@activityId", Integer.toString(activityId)).
				replace("@dependeeActivityId", Integer.toString(dependentId));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);		
		
		return;		
	}
	
	public void DeleteActivityRemoveDependency(int activityId)
	{
		String sql = ActivityDependencyDao.RemoveAllActivityDependencyForActivity.
				replace("@activityId", Integer.toString(activityId)).
				replace("@activityId", Integer.toString(activityId));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);				
	}
		
	//map resultset from sqlite to Activity
	private ActivityDependency mapResultSetToActivityDependency(ResultSet rs)
	{
		ActivityDependency temp = null;
		try 
		{
			temp = new ActivityDependency();
			temp.activityId = rs.getInt("activityId");
			temp.dependeeId = rs.getInt("dependeeActivityId");			
		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
	
	//get the list of activities on which the given activity depends
	public List<ActivityDependency> GetDependencyIdsForActivity(int activityId)
	{
		List<ActivityDependency> dependencies = new ArrayList<ActivityDependency>();
		
		String sql = ActivityDependencyDao.SelectActivityDependencyForActivity.replace("@activityId", Integer.toString(activityId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try 
		{
			while (rs.next()) 
			{
				ActivityDependency temp = mapResultSetToActivityDependency(rs);
				dependencies.add(temp);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dependencies;			
	}
	
	public String GetDependencyIdString(int activityId)
	{
		List<ActivityDependency> dependencies = GetDependencyIdsForActivity(activityId);
		
		String str ="";
		
		for (int i = 0 ; i < dependencies.size(); i++)
		{
			str += Integer.toString(dependencies.get(i).dependeeId) + ",";
		}		
		
		if (dependencies.size() == 0)
			return "";
		
		return str.substring(0, str.length()-1);
	}
}
