package DataAccessLayer;

import java.sql.*;
import Models.*;

public class SqliteSetup {
	//connection strings
	public static String dataBaseName = "Project.db";
	public static String connection = "jdbc:sqlite:" + SqliteSetup.dataBaseName;
	public static String sqliteClass = "org.sqlite.JDBC";
	
	public void init()
	{
	
		//create all tables
		//createTables();
		
		insertValues();
		
		selectRows();
	}
	
	public void createTables()
	{
		executeSql(User.createTable);
		//executeSql(Project.createTable);
		//executeSql(Activity.createTable);	
	}
	
	public void insertValues()
	{
		User[] test = new User[1];
		test[0] = new User("H", "SUN", 0);
		
		String[] sqls = User.insert(test);
		
		executeMultipleSqls(sqls);		
	}
	
	public void selectRows()
	{
		selectUsers("SELECT * FROM USER");
		
	}
	
	//execute a single sql query
	public void executeSql(String preparedSql)
	{
		System.out.println(preparedSql);
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully");	
		      stmt = c.createStatement();
		      stmt.executeUpdate(preparedSql);
		      stmt.close();
		      c.close();
			  System.out.println("Sql Executed Successfully");	
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
	
	//execute multiple sql queries, good for multiple update, insert, delete, etc
	public void executeMultipleSqls(String[] preparedSqls)
	{		
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
		      Class.forName(SqliteSetup.sqliteClass);
		      c = DriverManager.getConnection(SqliteSetup.connection);
			  System.out.println("Opened database successfully");	
		      stmt = c.createStatement();
		      
		      for (int i = 0; i < preparedSqls.length; ++i)
		      {
		  			System.out.println(preparedSqls[i]);
		  			stmt.executeUpdate(preparedSqls[i]);
		      }
		      
		      stmt.close();
		      c.close();
			  System.out.println("Sqls Executed Successfully");	
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }		
	}
	
	public void selectUsers(String preparedSql)
	{
	    Connection c = null;
	    Statement stmt = null;
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			c = DriverManager.getConnection(SqliteSetup.connection);
			System.out.println("Opened database successfully");	

		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(preparedSql);
		    while ( rs.next() ) 
		    {
		    	//test;
				System.out.println( "id = " + rs.getInt("id"));
				System.out.println( "first = " + rs.getString("firstName"));
				System.out.println( "last = " + rs.getString("lastName"));
				System.out.println( "role = " + rs.getInt("role"));
				System.out.println();
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
	}
}
