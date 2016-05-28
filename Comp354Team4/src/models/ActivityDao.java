package models;

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
import controllers.ConverterService;

public class ActivityDao {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String SelectActivitiesGivenProjectId = "Select * from Activity where projectId = @projectId AND isRemoved = 0";
	private static String InsertActivities = "INSERT INTO Activity(activityName, startDate, endDate, projectId) VALUES ('@activityName', '@startDate', '@endDate', '@projectId');";
	public static String CreateTable = "CREATE TABLE Activity (id INTEGER PRIMARY KEY, activityName varchar(50),	"
			+ "activityDescription varchar(50), startDate DateTime, endDate DateTime, projectId INTEGER, FOREIGN KEY(projectId) REFERENCES Project(id));";
	
	public static String DeleteActivityGivenActivityId = "UPDATE Activity SET isRemoved = '1' WHERE id = @id";
	
	//map resultset from sqlite to Activity
	public static Activity mapResultSetToActivity(ResultSet rs)
	{
		Activity temp = new Activity();	
		try 
		{
			temp.setId(rs.getInt("id"));
			temp.setActivityName(rs.getString("activityName"));			
			temp.setStartDate(dateFormat.parse(rs.getString("startDate"))); //parse date string to date
			temp.setEndDate(dateFormat.parse(rs.getString("endDate")));	
			
			temp.setProjectId(rs.getInt("projectId"));	
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
				"Start Date", 
				"End Date", 
				"Project Id",
				""
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
	
	
	public String[] returnDataRow(Activity activity)
	{		
		String[] temp = new String[]{
				Integer.toString(activity.getId()),
				activity.getActivityName(), 
				ConverterService.DateToString(activity.getStartDate()),
				ConverterService.DateToString(activity.getEndDate()), 
				Integer.toString(activity.getProjectId()),
				"Delete"
				};		
		return temp;		
	}
	
}
