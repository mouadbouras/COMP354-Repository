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

import daos.ActivityDependencyDao;
import daos.ProjectDao;
import daos.ResourceDao;
import factories.ResourcesTabEventFactory;
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
	
	public int row,col, currentlySelectedResource;
	
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
		
		this.removeResourceMenuItem = ResourcesTabEventFactory.removeResourceMenuItem(thisPanel);
		
		popup.add(removeResourceMenuItem);		
        
		this.createpopup = new JPopupMenu();
		
		this.newResourceMenuItem = ResourcesTabEventFactory.newResourceMenuItem(thisPanel);
		
		createpopup.add(newResourceMenuItem);		       
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
