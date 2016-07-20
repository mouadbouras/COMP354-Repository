package views_tabs;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import dao.ActivityDependencyDao;
import dao.ProjectDao;
import dao.ResourceDao;
import models.Activity;
import models.Project;
import models.Resource;
import models.User;
import services.ActivityDependencyService;
import services.ResourceService;
import services.StateService;
import services.TableDataService;
import views_panels.CreateUpdateProjectPanel;
import views_panels.GanttPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourcesTab extends JPanel 
{
	private JTable table;
	private JScrollPane databasetable;	
	public int currentlySelectedProject;
	
	private JPopupMenu createpopup;	
	private JMenuItem newResourceMenuItem;
	
	private JPopupMenu popup;
	private JMenuItem removeResourceMenuItem;
	
	private ResourcesTab thisPanel;
	
	int row,col, currentlySelectedResource;
	
	/**
	 * Create the panel.
	 */
	public ResourcesTab() {			

		this.thisPanel = this;
		
		setLayout(new BorderLayout(0, 0));		
		
		databasetable = new JScrollPane();
		add(databasetable, BorderLayout.CENTER);
		
		SetupPopup();	
		
		table = this.JTableProject();
		databasetable.setViewportView(table);	
	}
	
	private void SetupPopup()
	{
		this.popup = new JPopupMenu();
		
		this.removeResourceMenuItem = new JMenuItem("REMOVE This Resource");
		
		popup.add(removeResourceMenuItem);		
		
		removeResourceMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//just remove;
				ResourceDao.getInstance().DeleteResource(currentlySelectedResource);
				refreshTable();
			}
		});
        
		this.createpopup = new JPopupMenu();
		
		this.newResourceMenuItem = new JMenuItem("CREATE New Resource");
		
		createpopup.add(newResourceMenuItem);		
		
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
		        	
		        	refreshTable();
	        	}
	        	catch (Exception ex)
	        	{
	        		//closed
	        		System.out.println("THIS FAILED");
	        	}

			}
		});
        
	}
	
	private JTable JTableProject()
	{
		Activity currentActivity = StateService.getStateInstance().activity;
		
		if (currentActivity == null) 
			return null;
		
		TableDataService tds = new TableDataService();
		
		table = new JTable(tds.GetResourceTableData(currentActivity.getId()), tds.GetResourceTableColumns());
				
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);			
		
		table.addMouseListener(new java.awt.event.MouseAdapter() 
		{			
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());         
	        	currentlySelectedResource  = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }	
		    }
		});
		
		table.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() 
		{			
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	createpopup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }			    	
		    }
		});
		
		return table;
	}
	
	public void refreshTable()
	{
		table = JTableProject();
		databasetable.setViewportView(table);
	}
}
