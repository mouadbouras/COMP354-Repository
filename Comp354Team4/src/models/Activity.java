package models;

import java.util.Date;

public class Activity {
	
	private int id;
	private String activityName;
	private String activityDescription;
	private Date startDate;
	private Date endDate;
	private int projectId;
	
	public Activity(){
		
	}
	
	public Activity(int id, String name,String description, Date start, Date end, int projId){
		this.id = id;	activityName = name;activityDescription = description	;startDate = start;	endDate = end;
		projectId = projId;
	}
	
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

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescripition) {
		this.activityDescription = activityDescripition;
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

	public static String createTable = 	"CREATE TABLE Activity"
										+ "("
										+ "id INTEGER PRIMARY KEY,"
										+ "activityName varchar(50),"
										+ "activityDescription varchar(255),"
										+ "startDate DateTime,"
										+ "endDate DateTime,"
										+ "projectId INTEGER,"
										+ "FOREIGN KEY(projectId) REFERENCES Project(id)"			
										+ ")";

}
