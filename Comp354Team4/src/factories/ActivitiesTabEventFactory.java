package factories;

import views_tabs.ActivitiesTab;
import views_tabs.PropertiesTab;
import views_tabs.ResourcesTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import daos.ActivityDao;
import daos.ActivityDependencyDao;
import models.Activity;
import models.Project;
import services.ActivityDependencyService;
import services.StateService;
import views_panels.CreateUpdateActivityPanel;


public class ActivitiesTabEventFactory
{
	public static JMenuItem updateActivityMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem updateActivityMenuItem = new JMenuItem("UPDATE This Activity");
		
		updateActivityMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	  
				JDialog updateActivity = new JDialog();	
				updateActivity.setTitle("UPDATE Activity");
				
				CreateUpdateActivityPanel updateActivitiesPanel = new CreateUpdateActivityPanel(thisPanel, updateActivity, false);

				Activity temp = ActivityDao.getInstance().GetActivitiesGivenActivityId(thisPanel.currentlySelectedActivity).get(0);
				updateActivitiesPanel.SetupActivityForUpdate(temp);				
				
				updateActivity.add(updateActivitiesPanel);
				updateActivity.pack();
				updateActivity.setVisible(true);
			}
		});	
		
		return updateActivityMenuItem;
	}
	
	public static JMenuItem removeActivityMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem removeActivityMenuItem = new JMenuItem("DELETE This Activity");
		
		removeActivityMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	   				
				
				Project currentProject = StateService.getStateInstance().project;
	        	String[] activitySelection = new ActivityDependencyService().GetRemovableDependencies(currentProject.getId(), thisPanel.currentlySelectedActivity);   		        	
				
	        	ActivityDependencyDao.getInstance().DeleteActivityRemoveDependency(thisPanel.currentlySelectedActivity);	        	
	        	ActivityDao.getInstance().DeleteActivity(thisPanel.currentlySelectedActivity);
	        	thisPanel.refreshTable();
			}
		});			
		
		return removeActivityMenuItem;
	}
	
	public static JMenuItem removeDependenciesMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem removeDependenciesMenuItem = new JMenuItem("REMOVE Dependencies");
		
		removeDependenciesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	        
				String []temp = null;
				
				Project currentProject = StateService.getStateInstance().project;
	        	String[] activitySelection = new ActivityDependencyService().GetRemovableDependencies(currentProject.getId(), thisPanel.currentlySelectedActivity);   	
	        	
	        	if (activitySelection == null || activitySelection.length == 0)
	        	{
					JOptionPane.showMessageDialog(null, "No Dependencies Available To REMOVE");	   
					return;
	        	}
	        	
	        	String s = (String)JOptionPane.showInputDialog(
	        	                    null,
	        	                    "Delete Dependency",
	        	                    "Dependency Box",
	        	                    JOptionPane.PLAIN_MESSAGE,
	        	                    null,
	        	                    activitySelection,
	        	                    "Select Activity");

	        	try 
	        	{
		        	int removedId = Integer.parseInt(s);
		        	
		        	ActivityDependencyDao.getInstance().RemoveDependency(thisPanel.currentlySelectedActivity, removedId);
		        	
		        	thisPanel.refreshTable();
	        	}
	        	catch (Exception ex)
	        	{
	        		//closed
	        	}
			}
		});		
		
		return removeDependenciesMenuItem;
	}
	
	public static JMenuItem addDependenciesMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem addDependenciesMenuItem = new JMenuItem("ADD Dependencies");
		
		addDependenciesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	      
				Project currentProject = StateService.getStateInstance().project;
				String[] activitySelection = new ActivityDependencyService().GetAddableDependencies(currentProject.getId(), thisPanel.currentlySelectedActivity);  
				
	        	if (activitySelection == null || activitySelection.length == 0)
	        	{
					JOptionPane.showMessageDialog(null, "No Dependencies Available To ADD");	 
					return;
	        	}
	        	
				//calculate this
	        	String s = (String)JOptionPane.showInputDialog(
	        	                    null,
	        	                    "ADD Dependency",
	        	                    "Dependency Box",
	        	                    JOptionPane.PLAIN_MESSAGE,
	        	                    null,
	        	                    activitySelection,
	        	                    "Select Activity");	
	        	
	        	try 
	        	{
	        		int addedId = Integer.parseInt(s);	        	
	        		//add dependency;
	        		ActivityDependencyDao.getInstance().AddDependency(thisPanel.currentlySelectedActivity, addedId);
	        		
		        	thisPanel.refreshTable();
	        	}
	        	catch (Exception ex)
	        	{

	        	}
			}
		});		
		
		return addDependenciesMenuItem;
		
	}
	
	public static JMenuItem viewResourcesMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem viewResourcesMenuItem = new JMenuItem("VIEW Resources");
		
		viewResourcesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	        	
	        	Activity temp = new Activity();        	
	        	temp.setId(thisPanel.currentlySelectedActivity);
	        	StateService.getStateInstance().activity = temp;
	        	
	        	ResourcesTab tab = StateService.getStateInstance().resourceTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(2); //switches tabbed panes to the activity tab pane
			}
		});		
		
		return viewResourcesMenuItem;
	}
	
	public static JMenuItem viewPropertiesMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem viewPropertiesMenuItem = new JMenuItem("VIEW Properties");
		
		viewPropertiesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	        	
	        	Activity temp = new Activity();        	
	        	temp.setId(thisPanel.currentlySelectedActivity);
	        	StateService.getStateInstance().activity = temp;
	        	
	        	PropertiesTab tab = StateService.getStateInstance().propertyTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(3); //switches tabbed panes to the activity tab pane
			}
		});	
		
		return viewPropertiesMenuItem;		
	}
	
	public static JMenuItem newActivityMenuItem(ActivitiesTab thisPanel)
	{
		JMenuItem newActivityMenuItem = new JMenuItem("CREATE New Activity");
		
		newActivityMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	      
				JDialog newActivity = new JDialog();	
				JPanel createActivityPanel = new CreateUpdateActivityPanel(thisPanel, newActivity, true);				
				newActivity.setTitle("CREATE Activity");
				newActivity.add(createActivityPanel);
				newActivity.pack();
				newActivity.setVisible(true);
			}
		});			
		
		return newActivityMenuItem;		
	}
	
	
}
