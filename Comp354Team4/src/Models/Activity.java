package models;

import java.util.Date;

public class Activity {
	
	private int id;
	private String activityName;
	private Date startDate;
	private Date endDate;
	private int projectId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

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
