package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import models.Activity;

public class ActivityDependencyDao 
{
	
	//private static String SelectUserGivenUserId = "SELECT * FROM User WHERE id = @userId;";
	private static String SelectActivityDependency = "SELECT * FROM ActivityDependency WHERE activityId = '@activityId';";
	private static String InsertActivityDependency = "INSERT INTO ActivityDependency (activityId, dependeeActivityId) VALUES ('@activityId', '@dependeeActivityId');";
	private static String CreateTable = "CREATE TABLE ActivityDependency(id INTEGER PRIMARY KEY, activityId INTEGER, dependeeActivityId INTEGER, FOREIGN KEY(activityId) REFERENCES Activity(id), FOREIGN KEY(dependeeActivityId) REFERENCES Activity(id));";
		
	//get the list of activities on which the given activity depends
	public int[] GetDependencyIds(int activityId)
	{
		int[] temp = new int [0];
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully: GetDependency");	
			stmt = c.createStatement();
		    
			String sql = ActivityDependencyDao.SelectActivityDependency.replace("@activityId", Integer.toString(activityId)); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			if ( rs.next() ) 
			{
				temp = mapResultSetToActivity(rs);
			}
		    
			rs.close();
			stmt.close();
			c.close();	       
			return temp;
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.out.println("getDep");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
		return temp;	
		
	}
	
	//insert user into the database
	public void InsertDependency(int activityId,int dependeeActivityId )
	{		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully: InsertDependency");	
		      stmt = c.createStatement();
		      
		      String sql = ActivityDependencyDao.InsertActivityDependency.replace("@activityId", Integer.toString(activityId)).
								    		  							  replace("@dependeeActivityId",Integer.toString(dependeeActivityId));
		      stmt.executeUpdate(sql);		      
		      stmt.close();
		      c.close();
			  System.out.println("Sql Executed Successfully: InsertDependency");	
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
	    return;
	}

	
	public String GetDependencyIdString(int activityId)
	{
		int[] ids = GetDependencyIds(activityId);;
		String str ="";
		
		//System.out.println(ids.length);
		for (int i = 0 ; i<ids.length ; i++  ){
			str += ids[i];
			if(i != ids.length-1 )
			{
				str += ", ";
			}
		}
		
		return str;
	}
	//get user given the userid primary key
//	public User GetUserGivenUsernamePassword(String username, String password)
//	{
//		User temp = null;
//		
//	    Connection c = null;
//	    Statement stmt = null;
//	    try 
//	    {
//			Class.forName(SqliteSetup.sqliteClass);
//			c = DriverManager.getConnection(SqliteSetup.connection);
//			System.out.println("Opened database successfully");	
//			stmt = c.createStatement();
//		    
//			String sql = UserDao.SelectUserGivenUsernamePassword.replace("@username", username ).replace("@password", password); //prepare sql
//		    System.out.println(sql);
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			if ( rs.next() ) 
//			{
//				temp = mapResultSetToUser(rs);
//			}
//		    
//			rs.close();
//			stmt.close();
//			c.close();	       
//		    System.out.println("Operation done successfully");		
//
//			return temp;
//	    } 
//	    
//	    catch ( Exception e ) 
//	    {
//	    	//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	    	return null;
//	    }
//	    
//		
//		
//	}
//	
	
    //map result set from sqlite to User entity
	public static int[] mapResultSetToActivity(ResultSet rs)
	{

		int[] temp = new int[0];
		//int tmpList ;
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		
		try 
		{	
			while(!rs.isClosed())
			{
					tmpList.add( rs.getInt("dependeeActivityId"));
					rs.next();
			}

			temp = new int[tmpList.size()];
			for (int i = 0 ; i < tmpList.size() ; i++ )
			{	
				temp[i] = tmpList.get(i);
			}
		}
		

		catch (Exception e)
		{			
	    	System.out.println("mapResultSetToActivity");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
}
