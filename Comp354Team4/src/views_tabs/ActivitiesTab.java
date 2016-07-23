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

import daos.ActivityDao;
import daos.ActivityDependencyDao;
import daos.ProjectDao;
import daos.ResourceDao;
import factories.ActivitiesTabEventFactory;
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
import java.awt.Font;
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
	
	public int currentlySelectedActivity;
	public int row, col;
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
		this.updateActivityMenuItem = ActivitiesTabEventFactory.updateActivityMenuItem(thisPanel);	
		this.removeActivityMenuItem = ActivitiesTabEventFactory.removeActivityMenuItem(thisPanel);
		this.viewResourcesMenuItem = ActivitiesTabEventFactory.viewResourcesMenuItem(thisPanel);
		this.viewPropertiesMenuItem = ActivitiesTabEventFactory.viewPropertiesMenuItem(thisPanel);	
		this.addDependenciesMenuItem = ActivitiesTabEventFactory.addDependenciesMenuItem(thisPanel);
		this.removeDependenciesMenuItem = ActivitiesTabEventFactory.removeDependenciesMenuItem(thisPanel);	
		
		popup.add(updateActivityMenuItem);	
		popup.add(removeActivityMenuItem);		
		popup.add(viewResourcesMenuItem);
		popup.add(viewPropertiesMenuItem);
		popup.add(addDependenciesMenuItem);	
		popup.add(removeDependenciesMenuItem);		
		
		this.createpopup = new JPopupMenu();
		
		this.newActivityMenuItem = ActivitiesTabEventFactory.newActivityMenuItem(thisPanel);
		
		createpopup.add(newActivityMenuItem);				
	}
	
	public void refreshTable()
	{		
		table = JTableActivities();
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.getTableHeader().setPreferredSize(new Dimension(100,30));
		table.setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(table.getRowHeight()+12);
		table.setShowHorizontalLines(true);
		databaseTablePane.setViewportView(table);
	}
	
	private JTable JTableActivities()
	{
		Project currentProject = StateService.getStateInstance().project;
		DataService ds = new DataService();
		
		table = new JTable(ds.GetActivityTableData(currentProject.getId()), ds.GetActivityTableColumns());
		
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);			
		
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.getTableHeader().setPreferredSize(new Dimension(100,30));
		table.setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(table.getRowHeight()+12);
		table.setShowHorizontalLines(true);
		
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
