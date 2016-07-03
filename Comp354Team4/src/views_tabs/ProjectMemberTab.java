package views_tabs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import models.User;
import services.DataService;
import services.StateService;

public class ProjectMemberTab extends JPanel{
	
	private JTable Usertable;
	private JScrollPane databasetable;
	private JTable table;
	
	public ProjectMemberTab() {
		setLayout(new BorderLayout(0, 0));
		databasetable = new JScrollPane();
		add(databasetable, BorderLayout.CENTER);
		
		//table = this.JTableProject(createPanel,updatePanel);
		table = this.JTableProject();
		databasetable.setViewportView(table);
	}
	
	private JTable JTableProject(){
		User temp = StateService.getStateInstance().getUser();
		DataService ds = new DataService();
		table = new JTable(ds.GetUserTableData(temp), ds.GetUserTableColumns());
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);
		//table.removeColumn(table.getColumnModel().getColumn(0));
		return table;
	}
	
	public void refreshTable(){
		table = JTableProject();
		databasetable.setViewportView(table);
	}
	
}
