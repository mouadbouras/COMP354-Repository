package models;

import java.util.Date;
import java.util.List;

public class Project {
	private int id;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int managerId;
	private int activityCount;
	private List<Activity> activities;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}	
	
	public boolean addActivity(int id, String name, String description, Date start, Date end, int projId){
		activities.add(new Activity(id, name,description, start, end, projId));
		return true; //addition was a success
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
