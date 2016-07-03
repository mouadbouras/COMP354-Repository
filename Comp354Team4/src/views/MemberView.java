package views;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import services.StateService;
import views_tabs.ActivitiesTab;
import views_tabs.ProjectMemberTab;
import views_tabs.ProjectsTab;
import views_tabs.PropertiesTab;
import views_tabs.ResourcesTab;
import views_tabs.UserInformationTab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class MemberView extends JPanel {
	
	public JComponent managedUsers;
	public JComponent userInformation;
	
	public JSplitPane splitPane = new JSplitPane();
	public JSplitPane UserPM;
	public JPanel UserPMPanel = new JPanel(new BorderLayout());
	
	public JTabbedPane tabbedPane;
	
	public JComponent projects;
	public JComponent activities;	
	public JComponent resources;
	public JComponent properties;	
	
	
	public JTabbedPane leftTabbedPane;
	/**
	 * Create the panel.
	 */
	public MemberView()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE); 
		
        leftTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        leftTabbedPane.setBackground(Color.WHITE); 		

        SetupComponentsForTabbedPane();
	    
        managedUsers = new ProjectMemberTab();
	    userInformation = new UserInformationTab(); 

        
        leftTabbedPane.addTab("Members", null, managedUsers, null); 
        
        UserPM = new JSplitPane(JSplitPane.VERTICAL_SPLIT,userInformation,leftTabbedPane);
      
		this.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setRightComponent(tabbedPane);
		splitPane.setLeftComponent(UserPM);
	}
	
	private void SetupComponentsForTabbedPane()
	{
	    StateService.getStateInstance().setProjectTab(new ProjectsTab());
	    projects = StateService.getStateInstance().getProjectTab();

	    StateService.getStateInstance().setActivityTab(new ActivitiesTab());
	    activities = StateService.getStateInstance().getActivityTab();
		
	    StateService.getStateInstance().setResourceTab(new ResourcesTab());
	    resources = StateService.getStateInstance().getResourceTab();
	    
	    StateService.getStateInstance().setPropertyTab(new PropertiesTab());
	    properties = StateService.getStateInstance().getPropertyTab();	    
	    
        tabbedPane.addTab("Projects", null, projects, null);		
        tabbedPane.addTab("Activities", null, activities, null);        
        tabbedPane.addTab("Resources", null, resources, null);		
        tabbedPane.addTab("Properties", null, properties, null);    
	}
}
