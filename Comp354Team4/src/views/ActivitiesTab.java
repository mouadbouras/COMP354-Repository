package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.ProjectDao;
import models.ActivityDao;
import models.Project;
import models.User;
import controllers.DataService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActivitiesTab extends JPanel {
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
		Project temp = State.getStateInstance().getProject();
		DataService ds = new DataService();
		table = new JTable(ds.GetActivityTableData(temp.getId()), ds.GetActivityTableColumns());
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        
		        //column 6 is update project
		        if (row >= 0 && col == 6) {
		        	System.out.println("Update activity with id:" + table.getModel().getValueAt(row, 0));
		        	
		        	updateActivity.setActiveProject(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
		        	updateActivity.setProjectField(table.getModel().getValueAt(row, 1).toString());
		        	
		        	String description = (table.getModel().getValueAt(row, 2) == null) ? "" : table.getModel().getValueAt(row, 2).toString() ;
		        	updateActivity.setDescriptionField(description);
		        	updateActivity.setStartDateField(table.getModel().getValueAt(row, 3).toString());
		        	updateActivity.setEndDateField(table.getModel().getValueAt(row, 4).toString());
		        	
		        	
		        }
		        
		        //column 7 is delete project
		        if (row >= 0 && col == 7) {
		        	System.out.println("Delete activity with id:" + table.getModel().getValueAt(row, 0));
		        	int activityId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		        	new ActivityDao().DeleteActivity(activityId);
		        	refreshTable();
		        }
		    }
		});
		
		return table;
	}
}
