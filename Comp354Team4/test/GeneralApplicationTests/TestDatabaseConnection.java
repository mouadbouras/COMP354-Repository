package GeneralApplicationTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import dao.SqliteSetup;

public class TestDatabaseConnection {
	
	Connection c = null;
    Statement stmt = null;

	@Test
	public void databaseConnection() {
		
		try{
			//testing Database Connection
		    Class.forName(SqliteSetup.sqliteClass);
		    c = DriverManager.getConnection(SqliteSetup.connection);
		    System.out.println("Testing if database opens successfully");
		    assertNotNull(c);
		}	
		 catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
}
