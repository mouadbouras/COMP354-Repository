package models;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import controllers.ConverterService;

public class Project {
	private int id;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int managerId;
	private int activityCount;
	private List<Activity> activities;
	
	public Project(int projId) {
		id = projId;
	}
	
	public Project(String name, String start, String end, int manaId) throws ParseException, IllegalArgumentException {
		if (name != null && !name.isEmpty()) {
			projectName = name;
			startDate = ConverterService.StringToDate(start);
			System.out.println(startDate);
			endDate = ConverterService.StringToDate(end);
			managerId = manaId;
		}
		else throw new IllegalArgumentException();
	}
	
	public Project(String name, Date start, Date end, int manaId) throws ParseException, IllegalArgumentException {
		if (name != null && !name.isEmpty()) {
			projectName = name;
			startDate = start;
			System.out.println(startDate);
			endDate = end;
			managerId = manaId;
		}
		else throw new IllegalArgumentException();
	}
	
	public Project(int projId, String name, String start, String end, int manaId) throws ParseException, IllegalArgumentException {
		if (name != null && !name.isEmpty()) {
			id = projId;
			projectName = name;
			startDate = ConverterService.StringToDate(start);
			endDate = ConverterService.StringToDate(end);
			managerId = manaId;
		}
		else throw new IllegalArgumentException();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String name) throws IllegalArgumentException{
		if (name != null && !name.isEmpty()) {
			this.projectName = name;
		} else throw new IllegalArgumentException();
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

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}	
	
	public boolean addActivity(int id, String name, String description,int duration, String start, String end, int projId) throws ParseException{
		activities.add(new Activity(id, name,description,duration, start, end, projId));
		return true; //addition was a success
	}
	
	public List<Activity> getActivity(){
		return this.activities;
	}
	public boolean deleteActivity(int index){
		/*if(index < 0 || index > activities.size()-1){
			return false; //deletion was a failure
		}
		else{
			activities[index] = null;
			return true; //deletion was a success
		}*/
		return false;
	}
	
}
