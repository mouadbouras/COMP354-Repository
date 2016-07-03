package views_tabs;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.User;
import services.DataService;
import services.StateService;


public class PMActivityTab extends JPanel{
	private JTable table;
	private JScrollPane databasetable;
	
	public PMActivityTab(){
		setLayout(new BorderLayout(0, 0));
		databasetable = new JScrollPane();
		add(databasetable, BorderLayout.CENTER);
		table = this.JTableProject();
		databasetable.setViewportView(table);
	}

	private JTable JTableProject(){
		
		User temp = StateService.getStateInstance().getUser();
		DataService ds = new DataService();
		return table;	
	}
}
