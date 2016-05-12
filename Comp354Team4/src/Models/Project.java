package Models;

import java.util.Date;;

public class Project {
	private int id;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int managerId;
	
	public static String createTable = 	"CREATE TABLE Project"
										+ "("
										+ "id INTEGER PRIMARY KEY,"
										+ "projectName varchar(50),"
										+ "startDate DateTime,"
										+ "endDate DateTime,"
										+ "managerId INTEGER,"
										+ "FOREIGN KEY(managerId) REFERENCES User(id)"			
										+ ");";
	
}
