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
	
	private int duration;
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	private int optimisticDuration;
	private int pessimisticDuration;
	private int earnedValue;
	
	public int getOptimisticDuration() {
		return optimisticDuration;
	}

	public void setOptimisticDuration(int optimisticDuration) {
		this.optimisticDuration = optimisticDuration;
	}

	public int getPessimisticDuration() {
		return pessimisticDuration;
	}

	public void setPessimisticDuration(int pessimisticDuration) {
		this.pessimisticDuration = pessimisticDuration;
	}

	public int getEarnedValue() {
		return earnedValue;
	}

	public void setEarnedValue(int earnedValue) {
		this.earnedValue = earnedValue;
	}

	public int getPlannedValue() {
		return plannedValue;
	}

	public void setPlannedValue(int plannedValue) {
		this.plannedValue = plannedValue;
	}

	public int getActualCost() {
		return actualCost;
	}

	public void setActualCost(int actualCost) {
		this.actualCost = actualCost;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private int plannedValue;
	private int actualCost;
	
	public Activity()
	{
		
	}	
	
	public Activity(String name, String description, String start, String end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {	
			activityName = name;
			activityDescription = description;
			startDate = ConverterService.StringToDate(start);	
			endDate = ConverterService.StringToDate(end);
			projectId = projId;
		} else throw new IllegalArgumentException();
	}
	
	public Activity(String name, String description, Date start, Date end, int projId) throws ParseException, IllegalArgumentException{
		if (name != null && !name.isEmpty()) {	
			activityName = name;
			activityDescription = description;
			startDate = start;	
			endDate = end;
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
}
