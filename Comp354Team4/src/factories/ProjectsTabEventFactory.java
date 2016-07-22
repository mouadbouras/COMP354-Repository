package factories;
import views.*;
import views_panels.CreateUpdateProjectPanel;
import views_panels.GanttPanel;
import views_tabs.ActivitiesTab;
import views_tabs.ProjectsTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import daos.ProjectDao;
import models.*;
import services.StateService;


public class ProjectsTabEventFactory
{
	public static JMenuItem viewChartMenuItem(ProjectsTab thisPanel)
	{				
		JMenuItem temp = new JMenuItem("VIEW GANTT Chart");
		
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
		    	Project temp = ProjectDao.getInstance().getProjectByProjectId(thisPanel.currentlySelectedProject);
		    	System.out.println(thisPanel.currentlySelectedProject);	 
		    	JPanel ganttPanel = new GanttPanel("Gantt Chart",temp);
		    	
				JDialog ganttGraph = new JDialog();				
				ganttGraph.add(ganttPanel);
				ganttGraph.pack();
				ganttGraph.setVisible(true);
			}
		});
    	
		return temp;
	}
	
	public static JMenuItem updateProjectMenuItem(ProjectsTab thisPanel)
	{
		JMenuItem temp = new JMenuItem("UPDATE This Project");	
		
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog updateProject = new JDialog();		
				updateProject.setTitle("UPDATE Project");
				
				CreateUpdateProjectPanel updateProjectPanel = new CreateUpdateProjectPanel(thisPanel, updateProject, false);	
				
				Project p = new Project(thisPanel.currentlySelectedProject);
				updateProjectPanel.SetupProjectForUpdate(p);
				
				updateProject.add(updateProjectPanel);
				updateProject.pack();
				updateProject.setVisible(true);
			}
		});        
	
		return temp;
	}
	
	public static JMenuItem deleteProjectMenuItem(ProjectsTab thisPanel)
	{
		JMenuItem temp = new JMenuItem("DELETE This Project");
		
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
		    	ProjectDao.getInstance().DeleteProject(thisPanel.currentlySelectedProject);
		    	thisPanel.refreshTable();
			}
		});
		
		return temp;
	}
	
	public static JMenuItem viewActivitiesMenuItem(ProjectsTab thisPanel)
	{		
		JMenuItem temp = new JMenuItem("VIEW Activities");
		
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{	   	
		    	//get data for activities;
		    	Project temp = new Project(thisPanel.currentlySelectedProject);        	
		    	StateService.getStateInstance().project = temp;    	
		    	StateService.getStateInstance().activityTab.refreshTable();
		    	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(1); //switches tabbed panes to the activity tab pane
			}
		});		
    	
		return temp;
	}
	
	public static JMenuItem newProjectMenuItem(ProjectsTab thisPanel)
	{
		JMenuItem temp = new JMenuItem("CREATE New Project");
		
		temp.addActionListener(new ActionListener(){
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
		
		return temp;
	}
}
