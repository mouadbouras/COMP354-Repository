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
	
	private int optimisticDuration;
	private int pessimisticDuration;
	private int normalDuration;
	private int budgetPlanned;
	private int actualCost;
	private int progress;
	
	public Activity(int id, String name, String description, int nDuration, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {
			this.id = id;	
			activityName = name;
			activityDescription = description;
			startDate = ConverterService.StringToDate(start);	
			endDate = ConverterService.StringToDate(end);
			projectId = projId;
		} else throw new IllegalArgumentException();
		
		if (nDuration < 0)
			throw new IllegalArgumentException("Duration needs to be greater than zero!");
		normalDuration = nDuration;
	}
	
	public Activity(String name, String description, int nDuration, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		this (0, name, description, nDuration, start, end, projId);
	}	
	
	public Activity(int id, String name, int nDuration, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		this (id, name, "", nDuration, start, end, projId);
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
	
	public int getOptimisticDuration() {
		return optimisticDuration;
	}


	public void setOptimisticDuration(int duration) {
		if (duration <= 0)
			throw new IllegalArgumentException("Duration needs to be greater than zero!");
		optimisticDuration = duration;
	}


	public int getPessimisticDuration() {
		return pessimisticDuration;
	}


	public void setPessimisticDuration(int duration) {
		if (duration <= 0)
			throw new IllegalArgumentException("Duration needs to be greater than zero!");
		pessimisticDuration = duration;
	}


	public int getNormalDuration() {
		return normalDuration;
	}


	public void setNormalDuration(int duration) {
		if (duration <= 0)
			throw new IllegalArgumentException("Duration needs to be greater than zero!");
		normalDuration = duration;
	}


	public int getBudgetPlanned() {
		return budgetPlanned;
	}


	public void setBudgetPlanned(int budget) {
		if (budget < 0)
			throw new IllegalArgumentException("Budget needs to be positive!");
		budgetPlanned = budget;
	}


	public int getActualCost() {
		return actualCost;
	}


	public void setActualCost(int cost) {
		if (cost < 0)
			throw new IllegalArgumentException("Cost needs to be positive!");
		actualCost = cost;
	}


	public double getProgress() {
		return progress;
	}


	public void setProgress(int prog) {
		if (prog < 0 || prog > 100)
			throw new IllegalArgumentException("Progress needs to be a percentage value between 0 and 100!");
		this.progress = prog;
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
