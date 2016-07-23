package views;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import models.User;
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

public class MemberView extends JPanel {
	
	public JComponent managedUsers;
	public JComponent userInformation;
	
	public JSplitPane splitPane = new JSplitPane();
	public JSplitPane UserPM;
	public JPanel UserPMPanel = new JPanel(new BorderLayout());
	
	public JTabbedPane tabbedPane;
	
	public JComponent memberActivitiesTab;
	
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
        
		StateService.getStateInstance().memberUser = StateService.getStateInstance().user;
		
        SetupComponentsForTabbedPane();
	    
        managedUsers = new ProjectMemberTab();
	    userInformation = new UserInformationTab(); 
        
        //leftTabbedPane.addTab("Members", null, managedUsers, null); 
        
        UserPM = new JSplitPane(JSplitPane.VERTICAL_SPLIT,userInformation,leftTabbedPane);
      
		this.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setRightComponent(tabbedPane);
		splitPane.setLeftComponent(UserPM);
	}
	
	private void SetupComponentsForTabbedPane()
	{
	    StateService.getStateInstance().memberActivitiesTab = new MemberActivitiesTab();
	    memberActivitiesTab = StateService.getStateInstance().memberActivitiesTab;
	    
        tabbedPane.addTab("<html><body><table width='120'>Member Activities</table></body></html>", null, memberActivitiesTab, null);	  
	}
}
