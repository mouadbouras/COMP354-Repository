package daos;

import java.sql.*;

import models.*;

public class SqliteSetup {
	//connection strings
	public static String dataBaseName = "Project.db";
	public static String connection = "jdbc:sqlite:" + SqliteSetup.dataBaseName;
	public static String sqliteClass = "org.sqlite.JDBC";
	
	private static SqliteSetup sqliteSetup = null;
	
	private Connection c;
	private Statement stmt;
	private ResultSet rs;
	
	private SqliteSetup()
	{
		
	}
	
	public static SqliteSetup GetInstance()
	{
		if (sqliteSetup == null)
		{
			sqliteSetup = new SqliteSetup();			
		}		
		return sqliteSetup;		
	}
	
	public ResultSet ExecuteQuery(String sql)
	{
	    this.c = null;
	    this.stmt = null;
	    this.rs = null;
	    
	    try 
	    {
	    	Class.forName(SqliteSetup.sqliteClass);
	    	this.c = DriverManager.getConnection(SqliteSetup.connection);  			  
	    	this.stmt = c.createStatement();  
     
			System.out.println(sql);				  
			this.rs = stmt.executeQuery(sql);     
			  
			return this.rs;			  
	    } 
	    
	    catch (Exception e) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return null;		
	}
	
	public void ExecuteUpdate(String sql)
	{
	    this.c = null;
	    this.stmt = null;
	    this.rs = null;
	    
	    try 
	    {
			Class.forName(SqliteSetup.sqliteClass);
			this.c = DriverManager.getConnection(SqliteSetup.connection);  			  
			this.stmt = c.createStatement();  
	    
			System.out.println(sql);				  
			this.stmt.executeUpdate(sql);     	
				  
			this.stmt.close();
			this.c.close();	
	    } 
	    
	    catch (Exception e) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }   		
	}
	
	public void CloseQuery()
	{
		try 
		{
			this.rs.close();
			this.stmt.close();
			this.c.close();	
		}
	    catch (Exception e) 
	    {
	    	System.err.println("Close Query Failed");
	    }
	}
}
