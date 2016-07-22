package views_tabs;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;

import models.Activity;
import models.Project;
import models.User;
import services.DataService;
import services.StateService;
import views_panels.CreateUpdateProjectPanel;
import views_panels.GanttPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSplitPane;

import org.jfree.ui.RefineryUtilities;

import daos.ProjectDao;
import daos.PropertyDao;
import factories.ProjectsTabEventFactory;

public class ProjectsTab extends JPanel {
	private JTable table;
	private JScrollPane databasetable;
	
	private JPopupMenu popup;
	private JPopupMenu createpopup;
	
	private JMenuItem viewChartMenuItem;
	private JMenuItem newProjectMenuItem;
	private JMenuItem updateProjectMenuItem;	
	private JMenuItem deleteProjectMenuItem;	
	private JMenuItem viewActivitiesMenuItem;	
	
	public int currentlySelectedProject;
	public int row, col;
	private ProjectsTab thisPanel;
	
	/**
	 * Create the panel.
	 */
	
	public ProjectsTab() {			

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
		
		this.viewChartMenuItem = ProjectsTabEventFactory.viewChartMenuItem(thisPanel);
		this.updateProjectMenuItem = ProjectsTabEventFactory.updateProjectMenuItem(thisPanel);
		this.deleteProjectMenuItem = ProjectsTabEventFactory.deleteProjectMenuItem(thisPanel);
		this.viewActivitiesMenuItem = ProjectsTabEventFactory.viewActivitiesMenuItem(thisPanel);
		
		this.popup.add(updateProjectMenuItem);	
		this.popup.add(deleteProjectMenuItem);		
		this.popup.add(viewActivitiesMenuItem);	
		this.popup.add(viewChartMenuItem);		
        
		this.createpopup = new JPopupMenu();
        
		this.newProjectMenuItem = ProjectsTabEventFactory.newProjectMenuItem(thisPanel);
		
		this.createpopup.add(newProjectMenuItem);	

	}
	
	private JTable JTableProject()
	{
		User temp = StateService.getStateInstance().user;
		DataService ds = new DataService();
		
		table = new JTable(ds.GetProjectTableData(temp.getId()), ds.GetProjectTableColumns());
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.LIGHT_GRAY);		    
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {		    	
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());         
	        	currentlySelectedProject  = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }	
		    }
		});		
		
		// listener
		table.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {  	  	
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	createpopup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }	
		    }
		});	
		
		return table;		
	}
	
	public void refreshTable(){
		table = JTableProject();
		databasetable.setViewportView(table);
	}
}
