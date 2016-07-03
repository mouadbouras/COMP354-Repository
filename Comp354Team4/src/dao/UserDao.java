package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConverterService;
import models.SqliteSetup;
import models.User;

public class UserDao 
{
	
	private static String SelectUserGivenUserId = "SELECT * FROM User WHERE id = @userId;";
	private static String SelectUserGivenRole = "SELECT * FROM User WHERE role = @role;";
	private static String SelectUserGivenUsernamePassword = "SELECT * FROM User WHERE username = '@username' and password = '@password';";
	private static String InsertUsers = "INSERT INTO User (firstName, lastName, role, username, password) VALUES ('@firstName', '@lastName', @role, '@username', '@password');";
	private static String CreateTable = "CREATE TABLE User(id INTEGER PRIMARY KEY, firstName CHAR(50), lastName CHAR(50), role INTEGER, username CHAR(255), password CHAR(255));";
		
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

	//get user given the userid primary key
	public User GetUserGivenUsernamePassword(String username, String password)
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
		    
			String sql = UserDao.SelectUserGivenUsernamePassword.replace("@username", username ).replace("@password", password); //prepare sql
		    System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			if ( rs.next() ) 
			{
				temp = mapResultSetToUser(rs);
			}
		    
			rs.close();
			stmt.close();
			c.close();	       
		    System.out.println("Operation done successfully");		

			return temp;
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	return null;
	    }
	    
		
		
	}
	
	
    //map result set from sqlite to User entity
	public static User mapResultSetToUser(ResultSet rs)
	{
		User temp = new User();
		try 
		{
			temp.setId(rs.getInt("id"));
			temp.setFirstName(rs.getString("firstName"));
			temp.setLastName(rs.getString("lastName"));
			temp.setRole(rs.getInt("role"));	
			temp.setUsername(rs.getString("username"));	
			temp.setPassword(rs.getString("password"));	

		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
	
	//write by Gu
	public String[] GetPMColumns()
	{
		String[] columns = {
				"ProjectMember Id", 
				"First Name", 
				"Last Name", 
				"Current Activitiy",
				};
		
		return columns;		
	}
	
	public String[] returnDataRow(User user)
	{		
		String[] temp = new String[]{
				Integer.toString(user.getId()),
				user.getFirstName(),
				user.getLastName(), 
				"Edit",
				};		
		return temp;		
	}
	
	public List<User> GetPMInfor(User user)
	{
		List<User> userlist = new ArrayList<User>();
		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");	
			stmt = c.createStatement();
		    
			String sql = UserDao.SelectUserGivenRole.replace("@role", Integer.toString(user.getRole()) ); //prepare sql
		    
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) 
			{
				User temp = null;
				temp = UserDao.mapResultSetToUser(rs);
				userlist.add(temp);
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
		
		return userlist;			
	}
}
