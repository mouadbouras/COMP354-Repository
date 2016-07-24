package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;
import services.ConverterService;

public class UserDao 
{
	
	private static String SelectUserGivenUserId = "SELECT * FROM User WHERE id = @userId;";
	private static String SelectUserGivenRole = "SELECT * FROM User WHERE role = @role;";
	private static String SelectUserGivenUsernamePassword = "SELECT * FROM User WHERE username = '@username' and password = '@password';";
	private static String InsertUsers = "INSERT INTO User (firstName, lastName, role, username, password) VALUES ('@firstName', '@lastName', @role, '@username', '@password');";
	private static String SelectMembersGivenManagerId = "SELECT * FROM User AS U INNER JOIN WhoManagesWho AS W ON U.id = W.memberId WHERE managerId = @managerId";
	
	private static UserDao dao = null;
	
	private UserDao(){}
	
	public static UserDao getInstance()
	{
		if (dao == null)
			dao = new UserDao();
		
		return dao;
	}
	
	//get user given the userid primary key
	public User GetUserGivenUserId(int userId)
	{
		String sql = UserDao.SelectUserGivenUserId.replace("@userId", Integer.toString(userId)); //prepare sql
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		User temp = null;		
		
		try 		
		{
			if ( rs.next() ) 
			{
				temp = mapResultSetToUser(rs);
			}
		}
		catch (Exception e)
		{
			System.out.println("Result set failed");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return temp;		
	}
	
	//insert user into the database
	public boolean InsertUser(User user)
	{	
	    String sql = UserDao.InsertUsers.replace("@firstName", user.getFirstName()).
	    		  replace("@lastName", user.getLastName()).
	    		  replace("@role", Integer.toString(user.getRole())).
	    		  replace("@username", user.getUsername()).
	    		  replace("@password", user.getPassword());
	      
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
	      return true;

	}

	//get user given the userid primary key
	public User GetUserGivenUsernamePassword(String username, String password)
	{		
		String sql = UserDao.SelectUserGivenUsernamePassword.replace("@username", username ).replace("@password", password); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		User temp = null;
		
		try 		
		{
			if ( rs.next() ) 
			{
				temp = mapResultSetToUser(rs);
			}
		}
		catch (Exception e)
		{
			System.out.println("Result set failed");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return temp;			
	}
	
    //map result set from sqlite to User entity
	private User mapResultSetToUser(ResultSet rs)
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
				"Last Name"
				};
		
		return columns;		
	}
	
	public String[] returnDataRow(User user)
	{		
		String[] temp = new String[]{
				Integer.toString(user.getId()),
				user.getFirstName(),
				user.getLastName()
				};		
		return temp;		
	}
	
	public List<User> GetPMInfor(User user)
	{
		List<User> userlist = new ArrayList<User>();
		String sql = UserDao.SelectUserGivenRole.replace("@role", Integer.toString(user.getRole()) );
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
	
		try 		
		{
			while ( rs.next() ) 
			{
				User temp = null;
				temp = mapResultSetToUser(rs);
				userlist.add(temp);
			}
		}
		catch (Exception e)
		{
			System.out.println("Result set failed");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return userlist;	
		
	}
	
	public List<User> GetMembersGivenManagerId(int managerId)
	{
		List<User> userlist = new ArrayList<User>();
		
		String sql = UserDao.SelectMembersGivenManagerId.replace("@managerId", Integer.toString(managerId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
	
		try 		
		{
			while ( rs.next() ) 
			{
				User temp = null;
				temp = mapResultSetToUser(rs);
				userlist.add(temp);
			}
		}
		catch (Exception e)
		{
			System.out.println("Result set failed");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return userlist;	
		
	}
}
