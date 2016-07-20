package views_tabs;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.RefineryUtilities;

import dao.ActivityDao;
import dao.ActivityDependencyDao;
import dao.ProjectDao;
import dao.ResourceDao;

import models.Activity;
import models.ActivityDependency;
import models.Project;
import models.User;

import services.DataService;
import services.ActivityDependencyService;
import services.StateService;

import views_panels.CreateUpdateActivityPanel;
import views_panels.CreateUpdateProjectPanel;
import views_panels.GanttPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

public class ActivitiesTab extends JPanel {
	private JTable table;
	private JScrollPane databaseTablePane;
	private CreateUpdateActivityPanel createActivity;
		
	private JPopupMenu popup;
	private JPopupMenu createpopup;
	
	private JMenuItem newActivityMenuItem;
	private JMenuItem updateActivityMenuItem;	
	private JMenuItem addDependenciesMenuItem;	
	private JMenuItem removeDependenciesMenuItem;	
	private JMenuItem removeActivityMenuItem;	
	private JMenuItem viewPropertiesMenuItem;	
	private JMenuItem viewResourcesMenuItem;
	
	private JMenuItem addResourceMenuItem;
	private JMenuItem addPropertyMenuItem;	
	
	int currentlySelectedActivity;
	int row, col;
	private ActivitiesTab thisPanel;
	/**
	 * Create the panel.
	 */
	public ActivitiesTab() 
	{		
		thisPanel = this;		
		setLayout(new BorderLayout(0, 0));	
		
		SetupPopup();		
		
		databaseTablePane = new JScrollPane();
		add(databaseTablePane, BorderLayout.CENTER);		
	}
	
	private void SetupPopup()
	{
		this.popup = new JPopupMenu();
		this.updateActivityMenuItem = new JMenuItem("UPDATE This Activity");		
		this.removeActivityMenuItem = new JMenuItem("DELETE This Activity");
		this.viewResourcesMenuItem = new JMenuItem("VIEW Resources");		
		this.viewPropertiesMenuItem = new JMenuItem("VIEW Properties");		
		this.addDependenciesMenuItem = new JMenuItem("ADD Dependencies");	
		this.removeDependenciesMenuItem = new JMenuItem("REMOVE Dependencies");	
		
		this.addResourceMenuItem=new JMenuItem("Add Recourse");
		this.addPropertyMenuItem=new JMenuItem("Add Properties");
		
		popup.add(updateActivityMenuItem);	
		popup.add(removeActivityMenuItem);		
		popup.add(viewResourcesMenuItem);
		popup.add(viewPropertiesMenuItem);
		popup.add(addDependenciesMenuItem);	
		popup.add(removeDependenciesMenuItem);			

		updateActivityMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	  
				JDialog updateActivity = new JDialog();	
				updateActivity.setTitle("UPDATE Activity");
				
				CreateUpdateActivityPanel updateActivitiesPanel = new CreateUpdateActivityPanel(thisPanel, updateActivity, false);

				Activity temp = ActivityDao.getInstance().GetActivitiesGivenActivityId(currentlySelectedActivity).get(0);
				updateActivitiesPanel.SetupActivityForUpdate(temp);				
				
				updateActivity.add(updateActivitiesPanel);
				updateActivity.pack();
				updateActivity.setVisible(true);
			}
		});	
		
		removeActivityMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	   				
				
				Project currentProject = StateService.getStateInstance().project;
	        	String[] activitySelection = new ActivityDependencyService().GetRemovableDependencies(currentProject.getId(), currentlySelectedActivity);   		        	
				
	        	ActivityDependencyDao.getInstance().DeleteActivityRemoveDependency(currentlySelectedActivity);	        	
	        	ActivityDao.getInstance().DeleteActivity(currentlySelectedActivity);
	        	refreshTable();
			}
		});	
				
		removeDependenciesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	        
				String []temp = null;
				
				Project currentProject = StateService.getStateInstance().project;
	        	String[] activitySelection = new ActivityDependencyService().GetRemovableDependencies(currentProject.getId(), currentlySelectedActivity);   	
	        	
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
		        	
		        	ActivityDependencyDao.getInstance().RemoveDependency(currentlySelectedActivity, removedId);
		        	
		        	refreshTable();
	        	}
	        	catch (Exception ex)
	        	{
	        		//closed
	        	}
			}
		});		
		
		addDependenciesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	      
				Project currentProject = StateService.getStateInstance().project;
				String[] activitySelection = new ActivityDependencyService().GetAddableDependencies(currentProject.getId(), currentlySelectedActivity);  
				
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
	        		ActivityDependencyDao.getInstance().AddDependency(currentlySelectedActivity, addedId);
	        		
		        	refreshTable();
	        	}
	        	catch (Exception ex)
	        	{

	        	}
			}
		});			
	
		viewResourcesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	        	
	        	Activity temp = new Activity();        	
	        	temp.setId(currentlySelectedActivity);
	        	StateService.getStateInstance().activity = temp;
	        	
	        	ResourcesTab tab = StateService.getStateInstance().resourceTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(2); //switches tabbed panes to the activity tab pane
			}
		});	
		
		viewPropertiesMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	        	
	        	Activity temp = new Activity();        	
	        	temp.setId(currentlySelectedActivity);
	        	StateService.getStateInstance().activity = temp;
	        	
	        	PropertiesTab tab = StateService.getStateInstance().propertyTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(3); //switches tabbed panes to the activity tab pane
			}
		});			
				
		this.createpopup = new JPopupMenu();
		
		this.newActivityMenuItem = new JMenuItem("CREATE New Activity");
		
		createpopup.add(newActivityMenuItem);	
		
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
		
	}
	
	public void refreshTable()
	{		
		table = JTableActivities();
		databaseTablePane.setViewportView(table);
	}
	
	private JTable JTableActivities()
	{
		Project currentProject = StateService.getStateInstance().project;
		DataService ds = new DataService();
		
		table = new JTable(ds.GetActivityTableData(currentProject.getId()), ds.GetActivityTableColumns());
		
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);			
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());    
	        	currentlySelectedActivity = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		        
		        if(SwingUtilities.isRightMouseButton(evt))
		        {
		        	popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		    }
		});
		
		table.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
			
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());    	        	
		        
		        if(SwingUtilities.isRightMouseButton(evt))
		        {
		        	createpopup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		    }
		});
		
		return table;
	}
}
