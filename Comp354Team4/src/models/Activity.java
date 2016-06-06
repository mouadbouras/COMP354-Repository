package models;

import java.text.ParseException;
import java.util.Date;

import controllers.ConverterService;

public class Activity {
	
	private int id;
	private String activityName;
	private String activityDescription;
	private Date startDate;
	private Date endDate;
	private int projectId;
	
	public Activity(String name, String description, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {	
			activityName = name;
			activityDescription = description;
			startDate = ConverterService.StringToDate(start);	
			endDate = ConverterService.StringToDate(end);
			projectId = projId;
		} else throw new IllegalArgumentException();
	}
	
	
	public Activity(int id, String name, String description, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {
			this.id = id;	
			activityName = name;
			activityDescription = description;
			startDate = ConverterService.StringToDate(start);	
			endDate = ConverterService.StringToDate(end);
			projectId = projId;
		} else throw new IllegalArgumentException();
	}
	
	public Activity(int id, String name, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {
			this.id = id;	
			activityName = name;
			activityDescription = "";
			startDate = ConverterService.StringToDate(start);	
			endDate = ConverterService.StringToDate(end);
			projectId = projId;
		} else throw new IllegalArgumentException();
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

	public void setActivityName(String name) {
		if (name != null && !name.isEmpty()) {
			activityName = name;
		} else throw new IllegalArgumentException();
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

	public void setStartDate(String start) throws ParseException {
		startDate = ConverterService.StringToDate(start);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String end) throws ParseException {
		endDate = ConverterService.StringToDate(end);
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
