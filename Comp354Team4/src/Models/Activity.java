package Models;

import java.util.Date;

public class Activity {
	
	private int id;
	private String activityName;
	private Date startDate;
	private Date endDate;
	private int projectId;
	
	public static String createTable = 	"CREATE TABLE Project"
			+ "("
			+ "id INTEGER PRIMARY KEY,"
			+ "projectName varchar(50),"
			+ "startDate DateTime,"
			+ "endDate DateTime,"
			+ "managerId INTEGER,"
			+ "FOREIGN KEY(managerId) REFERENCES User(id)"			
			+ ")";

}
