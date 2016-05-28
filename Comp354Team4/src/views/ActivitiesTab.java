package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
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

	/**
	 * Create the panel.
	 */
	public ActivitiesTab() {
		setLayout(new BorderLayout(0, 0));

		
		JPanel search = new JPanel();
		search.setBackground(Color.ORANGE);
		add(search, BorderLayout.NORTH);
		
		databaseTablePane = new JScrollPane();
		add(databaseTablePane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{						
				table = JTableActivities();
				databaseTablePane.setViewportView(table);
			}
		});
		search.add(btnNewButton);

		
	}
	
	public void UpdateActivityTable()
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
		        //column 5 is delete project
		        if (row >= 0 && col == 5) {
		        	System.out.println("Delete activity with id:" + table.getModel().getValueAt(row, 0));
		        	int activityId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		        	new ActivityDao().DeleteActivity(activityId);
		        }
		    }
		});
		
		return table;
	}
}
