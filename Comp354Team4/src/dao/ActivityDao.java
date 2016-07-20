package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Project;
import controllers.ConverterService;

public class ActivityDao {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String SelectActivitiesGivenProjectId = "Select * from Activity where projectId = @projectId AND isRemoved = 0";
	private static String InsertActivities = "INSERT INTO Activity(activityName,activityDescription, duration,startDate, endDate, projectId) VALUES ('@activityName', '@activityDescription', '@duration','1000-01-01', '1000-10-10', '@projectId');";
	private static String SelectActivitiesGivenActivityId = "Select * from Activity where id = @activityId AND isRemoved = 0";	
	private static String DeleteActivityGivenActivityId = "UPDATE Activity SET isRemoved = '1' WHERE id = @id;";
	private static String UpdateActivityGivenProjectId = "UPDATE Activity SET activityName = '@activityName', activityDescription = '@activityDescription', startDate = '1000-01-01', endDate = '1000-01-01', duration = '@duration' WHERE id = @id ";
	
	private static ActivityDao dao = null;
	
	private ActivityDao(){}
	
	public static ActivityDao getInstance()
	{
		if (dao == null)
			dao = new ActivityDao();
		
		return dao;
	}
	
	//map resultset from sqlite to Activity
	public static Activity mapResultSetToActivity(ResultSet rs)
	{
		Activity temp = null;
		try 
		{
			temp = new Activity(rs.getInt("id"), rs.getString("activityName"), rs.getString("activityDescription"), rs.getInt("duration"), rs.getString("startDate"), rs.getString("endDate"), rs.getInt("projectId"));			
			temp.setDuration(rs.getInt("duration"));
		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
	
	public String[] GetActivityColumns()
	{
		String[] columns = {
				"Activity Id", 
				"Activity Name", 
				"Description", 
				"Start Date", 
				"End Date", 
				"Duration", 
				"Project Id",
				"Depends On (Id)"
				};
		
		return columns;		
	}
	
	public List<Activity> GetActivitiesGivenProjectId(int projectId)
	{
		List<Activity> activities = new ArrayList<Activity>();
		
		String sql = ActivityDao.SelectActivitiesGivenProjectId.replace("@projectId", Integer.toString(projectId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try {
			while ( rs.next() ) 
			{
				Activity temp = null;
				temp = ActivityDao.mapResultSetToActivity(rs);
				activities.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return activities;		
	}
	
	public List<Activity> GetActivitiesGivenActivityId(int activityId)
	{
		List<Activity> activities = new ArrayList<Activity>();
		
		String sql = ActivityDao.SelectActivitiesGivenActivityId.replace("@activityId", Integer.toString(activityId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try {
			while ( rs.next() ) 
			{
				Activity temp = null;
				temp = ActivityDao.mapResultSetToActivity(rs);
				activities.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return activities;				
	}	
	
	//insert project into database
	public boolean InsertActivity(Activity activity)
	{		
	      String sql = ActivityDao.InsertActivities.
	    		  replace("@activityName", activity.getActivityName()).
	    		  replace("@activityDescription", activity.getActivityDescription()). 
	    		  replace("@duration", activity.getDuration()+""). 									    		 
	    		  replace("@projectId", Integer.toString(activity.getProjectId()));
	      
	      SqliteSetup.GetInstance().ExecuteUpdate(sql);
	      return true;
	}
	
	public void DeleteActivity(int activityId)
	{				
	      String sql = ActivityDao.DeleteActivityGivenActivityId.replace("@id", Integer.toString(activityId));
	      
	      SqliteSetup.GetInstance().ExecuteUpdate(sql);
	      return;
	}
	
	public boolean UpdateActivity(Activity activity)
	{	
	    
	      String sql = ActivityDao.UpdateActivityGivenProjectId.
	    		  replace("@activityName", activity.getActivityName()).
				  replace("@activityDescription", activity.getActivityDescription()). 
	    		  replace("@id", Integer.toString(activity.getId())).
	    		  replace("@duration", Integer.toString(activity.getDuration()));	
	      
	      SqliteSetup.GetInstance().ExecuteUpdate(sql);
	      return true;
	    
	}
	
	public String[] returnDataRow(Activity activity)
	{		
		String dependency = ActivityDependencyDao.getInstance().GetDependencyIdString(activity.getId());
		
		String[] temp = new String[]{
				Integer.toString(activity.getId()),
				activity.getActivityName(), 
				activity.getActivityDescription(), 
				ConverterService.DateToString(activity.getStartDate()),
				ConverterService.DateToString(activity.getEndDate()), 
				Integer.toString(activity.getDuration()),
				Integer.toString(activity.getProjectId()),
				dependency
				};		
		return temp;		
	}
	
}
