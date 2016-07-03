package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.ActivityDependency;

import models.SqliteSetup;

public class ActivityDependencyDao 
{
	
	private static String SelectActivityDependencyForProject = "SELECT d.* FROM ActivityDependency d INNER JOIN Activity a ON d.activityId = a.id WHERE a.projectId = @projectId AND d.isRemoved = 0 AND a.isRemoved = 0";
	
	private static String SelectActivityDependencyForActivity = "SELECT d.* FROM ActivityDependency d INNER JOIN Activity a ON d.activityId = a.id WHERE d.activityId = '@activityId' AND d.isRemoved = 0 AND a.isRemoved = 0;";
	
	private static String InsertActivityDependency = "INSERT INTO ActivityDependency (activityId, dependeeActivityId) VALUES ('@activityId', '@dependeeActivityId');";
	
	private static String RemoveActivityDependency = "UPDATE ActivityDependency SET IsRemoved = 1 WHERE activityId = @activityId AND dependeeActivityId = @dependeeActivityId";
	
	private static String RemoveAllActivityDependencyForActivity = "UPDATE ActivityDependency SET IsRemoved = 1 WHERE activityId = @activityId OR dependeeActivityId = @activityId";
	
	//get the list of activities on which the given activity depends
	public List<ActivityDependency> GetDependencyIds(int projectId)
	{
		List<ActivityDependency> dependencies = new ArrayList<ActivityDependency>();
		
	    Connection c = null;
	    Statement stmt = null;
	    ActivityDependency temp = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.SelectActivityDependencyForProject.replace("@projectId", Integer.toString(projectId)); //prepare sql
			
		    System.out.println(sql);
		    
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) 
			{
				temp = mapResultSetToActivityDependency(rs);
				dependencies.add(temp);
			}
		    
			rs.close();
			stmt.close();
			c.close();	       
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
		return dependencies;			
	}
	
	public void AddDependency(int activityId, int dependentId)
	{
	    Connection c = null;
	    Statement stmt = null;
	    ActivityDependency temp = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.InsertActivityDependency.
					replace("@activityId", Integer.toString(activityId)).
					replace("@dependeeActivityId", Integer.toString(dependentId));
			
		    System.out.println(sql);
		    
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();	       
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }		
	    System.out.println("Operation done successfully");		
	}
	
	public void RemoveDependency(int activityId, int dependentId)
	{
	    Connection c = null;
	    Statement stmt = null;
	    ActivityDependency temp = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.RemoveActivityDependency.
					replace("@activityId", Integer.toString(activityId)).
					replace("@dependeeActivityId", Integer.toString(dependentId));
			
		    System.out.println(sql);
		    
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();	       
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
	    System.out.println("Operation done successfully");		
		
	}
	
	public void DeleteActivityRemoveDependency(int activityId)
	{
	    Connection c = null;
	    Statement stmt = null;
	    ActivityDependency temp = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.RemoveAllActivityDependencyForActivity.
					replace("@activityId", Integer.toString(activityId)).
					replace("@activityId", Integer.toString(activityId));
			
		    System.out.println(sql);
		    
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();	         
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
	    System.out.println("Operation done successfully");		
		
	}
		
	//map resultset from sqlite to Activity
	public static ActivityDependency mapResultSetToActivityDependency(ResultSet rs)
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
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.SelectActivityDependencyForActivity.replace("@activityId", Integer.toString(activityId)); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				ActivityDependency temp = mapResultSetToActivityDependency(rs);
				dependencies.add(temp);
			}
		    
			rs.close();
			stmt.close();
			c.close();	  

	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
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
