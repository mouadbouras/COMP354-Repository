package views;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.ProjectDao;
import models.Activity;
import models.ActivityDao;
import models.ActivityDependencyDao;
import models.Project;
import models.User;
import controllers.DataService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import javax.swing.JButton;

public class ActivitiesTab extends JPanel {
	private static final int ADD_DEPENDENCY = 10;
	private static final int DELETE_ACTIVITY = 8;
	private static final int UPDATE_ACTIVITY = 7;
	private JTable table;
	private JScrollPane databaseTablePane;
	private CreateActivityPanel createActivity;
	private UpdateActivityPanel updateActivity;

	/**
	 * Create the panel.
	 */
	public ActivitiesTab() {
		setLayout(new BorderLayout(0, 0));

		
//		JPanel search = new JPanel();
//		search.setBackground(Color.ORANGE);
//		add(search, BorderLayout.NORTH);
//		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.SOUTH);
		
		createActivity = new CreateActivityPanel(this);
		updateActivity = new UpdateActivityPanel(this);
		
		splitPane.setLeftComponent(createActivity);
		splitPane.setRightComponent(updateActivity);
		
		splitPane.setResizeWeight(.5d);
		
		databaseTablePane = new JScrollPane();
		add(databaseTablePane, BorderLayout.CENTER);
		
//		JButton btnNewButton = new JButton("Refresh");
//		btnNewButton.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent arg0) 
//			{						
//				table = JTableActivities();
//				databaseTablePane.setViewportView(table);
//			}
//		});
//		search.add(btnNewButton);

		
	}
	
	public void refreshTable()
	{		
		table = JTableActivities();
		databaseTablePane.setViewportView(table);
	}
	
	private JTable JTableActivities()
	{
		Project currentProject = State.getStateInstance().getProject();
		DataService ds = new DataService();
		table = new JTable(ds.GetActivityTableData(currentProject.getId()), ds.GetActivityTableColumns());
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        
		        if (row >= 0 && col == UPDATE_ACTIVITY) {
		        	System.out.println("Update activity with id:" + table.getModel().getValueAt(row, 0));
		        	
		        	updateActivity.setActiveProject(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
		        	updateActivity.setProjectField(table.getModel().getValueAt(row, 1).toString());
		        	
		        	String description = (table.getModel().getValueAt(row, 2) == null) ? "" : table.getModel().getValueAt(row, 2).toString() ;
		        	updateActivity.setDescriptionField(description);
		        	updateActivity.setStartDateField(table.getModel().getValueAt(row, 3).toString());
		        	updateActivity.setEndDateField(table.getModel().getValueAt(row, 4).toString());
		        }
		        
		        if (row >= 0 && col == DELETE_ACTIVITY) {
		        	System.out.println("Delete activity with id:" + table.getModel().getValueAt(row, 0));
		        	int activityId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		        	new ActivityDao().DeleteActivity(activityId);
		        	refreshTable();
		        }
		        
		        if (row >= 0 && col == ADD_DEPENDENCY) {
		        	String [][] tmptable = ds.GetActivityTableData(currentProject.getId());
		        	String[] activitySelection = new String[tmptable.length];
		        	Map<String,Integer> activitySelectionIds = new HashMap<String,Integer>(); 
		        	
		        	activitySelection[0] = "Select Activity";
		        	activitySelectionIds.put("Select Activity",0);
		        	int index = 1;
		        	for (int i = 0 ; i< tmptable.length ; i++)
		        	{
		        		if (i != row)
		        		{
		        			activitySelection[index] = tmptable[i][1];
		        			activitySelectionIds.put(tmptable[i][1], Integer.parseInt(tmptable[i][0]));
		        			index++;
		        		}
		        	}
		        	String s = (String)JOptionPane.showInputDialog(
		        	                    null,
		        	                    "Select the dependency you'd like to add",
		        	                    "Dependency Box",
		        	                    JOptionPane.PLAIN_MESSAGE,
		        	                    null,
		        	                    activitySelection,
		        	                    "Select Activity");	
		        	
		        	if( activitySelectionIds.get(s) != 0)
		        	{
			        	int currentActivityId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
			        	boolean dependencyExists = (new ActivityDependencyDao()).CheckDependencyExists(currentActivityId,activitySelectionIds.get(s)) ;
			        	if(!dependencyExists)
			        	{
			        		(new ActivityDependencyDao()).InsertDependency(currentActivityId,activitySelectionIds.get(s));
			        		System.out.println("does it exist? : " + (new ActivityDependencyDao()).CheckDependencyExists(currentActivityId,activitySelectionIds.get(s)));
			        	}
			        	else{
							JOptionPane.showMessageDialog(null, "The selected Dependency Already Exists! ");
			        	}
		        	} 

		        	refreshTable();
		        }
		    }
		});
		
		return table;
	}
}
