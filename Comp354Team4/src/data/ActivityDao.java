package data;

import java.sql.ResultSet;
import java.text.DateFormat;

import models.Activity;

public class ActivityDao {

	private static DateFormat dateFormat = null;
	
	//map resultset from sqlite to Activity
	public static Activity mapResultSetToActivity(ResultSet rs)
	{
		Activity temp = new Activity();	
		try 
		{
			temp.setId(rs.getInt("id"));
			temp.setActivityName(rs.getString("projectName"));
			
			temp.setStartDate(dateFormat.parse(rs.getString("startDate"))); //parse date string to date
			temp.setEndDate(dateFormat.parse(rs.getString("endDate")));	
			
			temp.setProjectId(rs.getInt("projectId"));	
		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
	
}
