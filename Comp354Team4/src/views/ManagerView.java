package views;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import services.StateService;
import views_tabs.ActivitiesTab;
import views_tabs.MemberActivitiesTab;
import views_tabs.ProjectMemberTab;
import views_tabs.ProjectsTab;
import views_tabs.PropertiesTab;
import views_tabs.ResourcesTab;
import views_tabs.UserInformationTab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ManagerView extends JPanel {
	
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
	public JComponent membersView;	
	
	public JTabbedPane leftTabbedPane;
	/**
	 * Create the panel.
	 */
	public ManagerView()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE); 
		
        leftTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        leftTabbedPane.setBackground(Color.WHITE); 		

        SetupComponentsForTabbedPane();
	    
        managedUsers = new ProjectMemberTab();
	    userInformation = new UserInformationTab(); 

        
        leftTabbedPane.addTab("<html><body><table width='160'>Managed Members</table></body></html>", null, managedUsers, null); 
        
        UserPM = new JSplitPane(JSplitPane.VERTICAL_SPLIT,userInformation,leftTabbedPane);
      
		this.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setRightComponent(tabbedPane);
		splitPane.setLeftComponent(UserPM);
	}
	
	private void SetupComponentsForTabbedPane()
	{
	    StateService.getStateInstance().projectTab = new ProjectsTab();
	    projects = StateService.getStateInstance().projectTab;

	    StateService.getStateInstance().activityTab = new ActivitiesTab();
	    activities = StateService.getStateInstance().activityTab;
		
	    StateService.getStateInstance().resourceTab = new ResourcesTab();
	    resources = StateService.getStateInstance().resourceTab;
	    
	    StateService.getStateInstance().propertyTab = new PropertiesTab();
	    properties = StateService.getStateInstance().propertyTab; 
	    
	    StateService.getStateInstance().memberActivitiesTab = new MemberActivitiesTab();
	    membersView = StateService.getStateInstance().memberActivitiesTab; 
	    
        tabbedPane.addTab("<html><body><table width='120'>Projects</table></body></html>", null, projects, null);		
        tabbedPane.addTab("<html><body><table width='120'>Activities</table></body></html>", null, activities, null);        
        tabbedPane.addTab("<html><body><table width='120'>Resources</table></body></html>", null, resources, null);		
        tabbedPane.addTab("<html><body><table width='120'>Properties</table></body></html>", null, properties, null);    
        tabbedPane.addTab("<html><body><table width='120'>Member Activities</table></body></html>", null, membersView, null);
       // tp.addTab("<html><body><table width='150'>Name</table></body></html>",Componentobject)
	}
}
