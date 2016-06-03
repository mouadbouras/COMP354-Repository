package ActivityTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import models.ActivityDao;
import models.SqliteSetup;

public class TestUpdateActivity {

	@Test
	//testing update of an existing activity by using the "activity id"
	public void UpdateActivity() throws SQLException, ClassNotFoundException {
		//fail("Not yet implemented");
		
		Connection c = null;
	    Statement stmt = null;
	    Class.forName(SqliteSetup.sqliteClass);
	      c = DriverManager.getConnection(SqliteSetup.connection);
		  System.out.println("Opened database successfully: TESTING updateActivity ");	
		  stmt = c.createStatement();		      
	      String sql = ActivityDao.UpdateActivityGivenProjectId.replace("@activityName", "UPDATE").
				  replace("@activityDescription", "this is a test"). 
	    		  replace("@startDate", "2016-02-29").
	    		  replace("@endDate", "2017-02-29").
	    		  replace("@id", Integer.toString(3));
	      stmt.executeUpdate(sql);		      
	      stmt.close();
	      c.close();
	      System.out.println("Sql Executed Successfully: updateActivity");
	      assertNotNull(sql);
		  
	}

}
