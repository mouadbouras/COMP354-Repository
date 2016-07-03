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

import dao.ProjectDao;

public class ResourcesTab extends JPanel {
	private JTable table;
	private JScrollPane databasetable;
	
	private JPopupMenu popup;
	private JMenuItem viewChartMenuItem;
	private JMenuItem newProjectMenuItem;
	private JMenuItem updateProjectMenuItem;	
	private JMenuItem deleteProjectMenuItem;	
	private JMenuItem viewActivitiesMenuItem;	
	
	public int currentlySelectedProject;
	
	private ResourcesTab thisPanel;
	
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
		
	}
	
	private JTable JTableProject()
	{
		return null;
	}
	
	public void refreshTable(){
		table = JTableProject();
		databasetable.setViewportView(table);
	}
}
