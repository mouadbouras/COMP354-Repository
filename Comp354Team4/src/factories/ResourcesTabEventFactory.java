package factories;

import views_tabs.PropertiesTab;
import views_tabs.ResourcesTab;
import daos.PropertyDao;
import daos.ResourceDao;
import models.Activity;
import models.Resource;
import models.User;
import services.ResourceService;
import services.StateService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class ResourcesTabEventFactory
{
	public static JMenuItem removeResourceMenuItem(ResourcesTab thisPanel)
	{
		JMenuItem removeResourceMenuItem = new JMenuItem("REMOVE This Resource");
		
		removeResourceMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//just remove;
				ResourceDao.getInstance().DeleteResource(thisPanel.currentlySelectedResource);
				thisPanel.refreshTable();
			}
		});
		
		return removeResourceMenuItem;
	}
	
	public static JMenuItem newResourceMenuItem(ResourcesTab thisPanel)
	{
		JMenuItem newResourceMenuItem = new JMenuItem("ADD New Resource");
		
		newResourceMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Activity currentActivity = StateService.getStateInstance().activity;
				User currentManager = StateService.getStateInstance().user;
				
	        	String[] resourceSelection = new ResourceService().getAddableResources(currentManager.getId(), currentActivity.getId());	
	        	
	        	if (resourceSelection == null || resourceSelection.length == 0)
	        	{
					JOptionPane.showMessageDialog(null, "No Members Managed By You Can Be Added!");	   
					return;
	        	}
	        	
	        	String s = (String)JOptionPane.showInputDialog(
	        	                    null,
	        	                    "Add Resource",
	        	                    "Resource Box",
	        	                    JOptionPane.PLAIN_MESSAGE,
	        	                    null,
	        	                    resourceSelection,
	        	                    "Select Member To Add to Activity");     	
	        	

	        	try 
	        	{
	        		String memberSelected = s.substring((int)(s.indexOf("{") + 1), s.indexOf("}"));
	        		int memberId = Integer.parseInt(memberSelected);
	        		Resource temp = new Resource();
	        		temp.activityId = currentActivity.getId();
	        		temp.memberId = memberId;       	
		        	
	        		ResourceDao.getInstance().InsertResources(temp);
		        	
		        	thisPanel.refreshTable();
	        	}
	        	catch (Exception ex)
	        	{
	        		//closed
	        		System.out.println("THIS FAILED");
	        	}

			}
		});
		
		return newResourceMenuItem;
	}
	
}
