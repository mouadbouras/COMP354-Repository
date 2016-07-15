package services;

import models.*;
import views.ManagerView;
import views_panels.GanttPanel;
import views_tabs.ActivitiesTab;
import views_tabs.MemberActivitiesTab;
import views_tabs.ProjectMemberTab;
import views_tabs.ProjectsTab;
import views_tabs.PropertiesTab;
import views_tabs.ResourcesTab;
import views_tabs.UserInformationTab;

public class StateService {
	
	public User user;
	public Project project;
	public Activity activity;
	
	public ManagerView projectsView;
	
	public ProjectsTab projectTab;
	public ActivitiesTab activityTab;
	public MemberActivitiesTab memberActivitiesTab;
	
	public User memberUser;
	
	
	public ResourcesTab getResourceTab() {
		return resourceTab;
	}

	public void setResourceTab(ResourcesTab resourceTab) {
		this.resourceTab = resourceTab;
	}

	public PropertiesTab getPropertyTab() {
		return propertyTab;
	}

	public void setPropertyTab(PropertiesTab propertyTab) {
		this.propertyTab = propertyTab;
	}

	public ResourcesTab resourceTab;
	public PropertiesTab propertyTab;	
	public ProjectMemberTab PMtab;
	public UserInformationTab userInforTAB;
	public GanttPanel ganttview;
	
	public ManagerView getProjectsView() {
		return projectsView;
	}

	public void setProjectsView(ManagerView projectsView) {
		this.projectsView = projectsView;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	private static StateService state;	
	
	private StateService(){}
	
	public static StateService getStateInstance()
	{
		if (StateService.state == null)
			StateService.state = new StateService();
		
		return StateService.state;		
	}

	public ProjectsTab getProjectTab() {
		return projectTab;
	}

	public void setProjectTab(ProjectsTab projectTab) {
		this.projectTab = projectTab;
	}

	public ActivitiesTab getActivityTab() {
		return activityTab;
	}

	public void setActivityTab(ActivitiesTab activityTab) {
		this.activityTab = activityTab;
	}	

	public ProjectMemberTab getProjectMemberTab() {
		return PMtab;
	}
	
	public GanttPanel getGanttViewTab() {
		return ganttview;
	}
	
	public void setProjectMemberTab(ProjectMemberTab PmTab) {
		this.PMtab = PmTab;
	}
	
	public UserInformationTab getUserinfor() {
		return this.userInforTAB;
	}
	
	public void setUserinfor(UserInformationTab userTab) {
		this.userInforTAB = userTab;
	}
}
