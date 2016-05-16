package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import models.User;

public class UserDao 
{
	
	private static String SelectUserGivenUserId = "SELECT * FROM User WHERE id = @userId;";
	private static String InsertUsers = "INSERT INTO User (firstName, lastName, role) VALUES ('@firstName', '@lastName', @role);";
	private static String CreateTable = "CREATE TABLE User(id INTEGER PRIMARY KEY, firstName CHAR(50), lastName CHAR(50), role INTEGER);";
		
	//get user given the userid primary key
	public User GetUserGivenUserId(int userId)
	{
		User temp = null;
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");	
			stmt = c.createStatement();
		    
			String sql = UserDao.SelectUserGivenUserId.replace("@userId", Integer.toString(userId)); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			if ( rs.next() ) 
			{
				temp = mapResultSetToUser(rs);
			}
		    
			rs.close();
			stmt.close();
			c.close();	       
			return temp;
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Operation done successfully");		
		
		return temp;	
		
	}
	
	//insert user into the database
	public void InsertUser(User user)
	{		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully");	
		      stmt = c.createStatement();
		      
		      String sql = UserDao.InsertUsers.replace("@firstName", user.getFirstName()).
								    		  replace("@lastName", user.getLastName()).
								    		  replace("@role", Integer.toString(user.getRole()));
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

    //map resultset from sqlite to User entity
	public static User mapResultSetToUser(ResultSet rs)
	{
		User temp = new User();
		try 
		{
			temp.setId(rs.getInt("id"));
			temp.setFirstName(rs.getString("firstName"));
			temp.setLastName(rs.getString("lastName"));
			temp.setRole(rs.getInt("role"));	
		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
}
