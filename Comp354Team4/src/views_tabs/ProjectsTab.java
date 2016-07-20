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

import dao.ProjectDao;
import dao.PropertyDao;

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
		
		this.viewChartMenuItem = new JMenuItem("VIEW GANTT Chart");
		this.updateProjectMenuItem = new JMenuItem("UPDATE This Project");		
		this.deleteProjectMenuItem = new JMenuItem("DELETE This Project");
		this.viewActivitiesMenuItem = new JMenuItem("VIEW Activities");		
		
		popup.add(updateProjectMenuItem);	
		popup.add(deleteProjectMenuItem);		
		popup.add(viewActivitiesMenuItem);	
		popup.add(viewChartMenuItem);		
		
        viewChartMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
	        	Project temp = ProjectDao.getInstance().getProjectByProjectId(currentlySelectedProject);
	        	System.out.println(currentlySelectedProject);	 
	        	JPanel ganttPanel = new GanttPanel("Gantt Chart",temp);
	        	
				JDialog ganttGraph = new JDialog();				
				ganttGraph.add(ganttPanel);
				ganttGraph.pack();
				ganttGraph.setVisible(true);
			}
		});
        
        updateProjectMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog updateProject = new JDialog();		
				updateProject.setTitle("UPDATE Project");
				
				CreateUpdateProjectPanel updateProjectPanel = new CreateUpdateProjectPanel(thisPanel, updateProject, false);	
				
				Project p = new Project(currentlySelectedProject);
				updateProjectPanel.SetupProjectForUpdate(p);
				
				updateProject.add(updateProjectPanel);
				updateProject.pack();
				updateProject.setVisible(true);
			}
		});
        
        deleteProjectMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
	        	int projectId = currentlySelectedProject;
	        	ProjectDao.getInstance().DeleteProject(projectId);
	        	refreshTable();
			}
		});
        
        viewActivitiesMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	        	
	        	//get data for activities;
	        	int projectId = currentlySelectedProject;
	        	Project temp = new Project(projectId);        	
	        	StateService.getStateInstance().project = temp;
	        	
	        	ActivitiesTab tab = StateService.getStateInstance().activityTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(1); //switches tabbed panes to the activity tab pane
			}
		});		
        
		this.createpopup = new JPopupMenu();
        
		this.newProjectMenuItem = new JMenuItem("CREATE New Project");
		
		createpopup.add(newProjectMenuItem);	
        
        newProjectMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog newProject = new JDialog();		
				newProject.setTitle("CREATE Project");
				JPanel createProjectPanel = new CreateUpdateProjectPanel(thisPanel, newProject, true);
			
				newProject.add(createProjectPanel);
				newProject.pack();
				newProject.setVisible(true);
			}
		});        
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
