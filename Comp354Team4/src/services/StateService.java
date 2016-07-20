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
	public User memberUser;
	public Project project;
	public Activity activity;
	public ActivityDependency activityDependency;
	public Resource resource;
	public Property property;
	public MemberActivityInfo memberActivityInfo;

	public ManagerView managerView;

	public ProjectsTab projectTab;
	public ActivitiesTab activityTab;
	public MemberActivitiesTab memberActivitiesTab;
	public ResourcesTab resourceTab;
	public PropertiesTab propertyTab;
	public ProjectMemberTab PMtab;
	public UserInformationTab userInforTAB;

	public GanttPanel ganttview;

	private static StateService state;

	private StateService() {
	}

	public static StateService getStateInstance() {
		if (StateService.state == null)
			StateService.state = new StateService();

		return StateService.state;
	}
}
