package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Project;
import models.SqliteSetup;
import controllers.ConverterService;

public class ActivityDao {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String SelectActivitiesGivenProjectId = "Select * from Activity where projectId = @projectId AND isRemoved = 0";
	private static String InsertActivities = "INSERT INTO Activity(activityName,activityDescription, duration,startDate, endDate, projectId) VALUES ('@activityName', '@activityDescription', '@duration','1000-01-01', '1000-10-10', '@projectId');";
	private static String SelectActivitiesGivenActivityId = "Select * from Activity where id = @activityId AND isRemoved = 0";

	public static String CreateTable = "CREATE TABLE Activity (id INTEGER PRIMARY KEY, activityName varchar(50),	"
			+ "activityDescription varchar(255), startDate DateTime, endDate DateTime, projectId INTEGER, FOREIGN KEY(projectId) REFERENCES Project(id));";
	
	public static String DeleteActivityGivenActivityId = "UPDATE Activity SET isRemoved = '1' WHERE id = @id";
	public static String UpdateActivityGivenProjectId = "UPDATE Activity SET activityName = '@activityName', activityDescription = '@activityDescription', startDate = '@startDate', endDate = '@endDate' WHERE id = @id ";

	
	//map resultset from sqlite to Activity
	public static Activity mapResultSetToActivity(ResultSet rs)
	{
		Activity temp = null;
		try 
		{
			temp = new Activity(rs.getInt("id"), rs.getString("activityName"), rs.getString("activityDescription"), rs.getInt("duration"),
					rs.getString("startDate"), rs.getString("endDate"), rs.getInt("projectId"));			
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
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");	
			stmt = c.createStatement();
		    
			String sql = ActivityDao.SelectActivitiesGivenProjectId.replace("@projectId", Integer.toString(projectId)); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) 
			{
				Activity temp = null;
				temp = ActivityDao.mapResultSetToActivity(rs);
				activities.add(temp);
			}
		    
			rs.close();
			stmt.close();
			c.close();		

	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
		return activities;			
	}
	
	public static List<Activity> GetActivitiesGivenActivityId(int activityId)
	{
		List<Activity> activities = new ArrayList<Activity>();
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");	
			stmt = c.createStatement();
		    
			String sql = ActivityDao.SelectActivitiesGivenActivityId.replace("@activityId", Integer.toString(activityId)); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) 
			{
				Activity temp = null;
				temp = ActivityDao.mapResultSetToActivity(rs);
				activities.add(temp);
			}
		    
			rs.close();
			stmt.close();
			c.close();		

	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
		return activities;			
	}	
	
	//insert project into database
		public boolean InsertActivity(Activity activity)
		{		
		    Connection c = null;
		    Statement stmt = null;
		    try 
		    {
			      Class.forName(SqliteSetup.sqliteClass);
			      c = DriverManager.getConnection(SqliteSetup.connection);
				  System.out.println("Opened database successfully: Insert Activity");	
			      stmt = c.createStatement();
			      
			      String sql = ActivityDao.InsertActivities.replace("@activityName", activity.getActivityName()).
			    		  						  replace("@activityDescription", activity.getActivityDescription()). 
			    		  						  replace("@duration", activity.getDuration()+""). 									    		 
			    		  						  //replace("@duration", ConverterService.DateToString(activity.getStartDate())). //format date to string 
									    		  //replace("@endDate", ConverterService.DateToString(activity.getEndDate())). //format date to string 
									    		  replace("@projectId", Integer.toString(activity.getProjectId()));
			      //System.out.println(sql);
			      stmt.executeUpdate(sql);		      
			      stmt.close();
			      c.close();
				  System.out.println("Sql Executed Successfully:Insert Activity");	
		    } 
		    catch ( Exception e ) 
		    {
		    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    	return false;
		    }	
		    return true;
		}
	
	public void DeleteActivity(int activityId)
	{		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully");	
		      stmt = c.createStatement();		      
		      String sql = ActivityDao.DeleteActivityGivenActivityId.replace("@id", Integer.toString(activityId));
		      System.out.println(sql);
		      stmt.executeUpdate(sql);		      
		      stmt.close();
		      c.close();
			  System.out.println("Sql Executed Successfully");	
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
	    return;
	}
	
	public boolean UpdateActivity(Activity activity)
	{
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully: updateProject ");	
		      stmt = c.createStatement();		      
		      String sql = ActivityDao.UpdateActivityGivenProjectId.replace("@activityName", activity.getActivityName()).
					  replace("@activityDescription", activity.getActivityDescription()). 
		    		  replace("@startDate", ConverterService.DateToString(activity.getStartDate())). //format date to string 
		    		  replace("@endDate", ConverterService.DateToString(activity.getEndDate())). //format date to string 
		    		  replace("@id", Integer.toString(activity.getId()));

		      stmt.executeUpdate(sql);		      
		      stmt.close();
		      c.close();
			  System.out.println("Sql Executed Successfully: updateActivity");
			  
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	return false;
	    }	
	    return true;		
	}
	
	public String[] returnDataRow(Activity activity)
	{		
		String dependency = (new ActivityDependencyDao()).GetDependencyIdString(activity.getId());
		
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
