package data;

import java.sql.*;

import models.*;

public class SqliteSetup {
	//connection strings
	public static String dataBaseName = "Project.db";
	public static String connection = "jdbc:sqlite:" + SqliteSetup.dataBaseName;
	public static String sqliteClass = "org.sqlite.JDBC";
	
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

}
