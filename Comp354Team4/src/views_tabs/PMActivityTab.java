package views_tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

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
		
		
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.getTableHeader().setPreferredSize(new Dimension(100,30));
		table.setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(table.getRowHeight()+12);
		table.setShowHorizontalLines(true);
	
		
		databasetable.setViewportView(table);
	}

	private JTable JTableProject(){
		
		User temp = StateService.getStateInstance().user;
		DataService ds = new DataService();
		return table;	
	}
}
