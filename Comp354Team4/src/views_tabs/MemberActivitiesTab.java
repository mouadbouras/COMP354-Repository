package views_tabs;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import daos.ActivityDependencyDao;
import daos.ProjectDao;
import daos.ResourceDao;
import models.Activity;
import models.Project;
import models.Resource;
import models.User;
import services.ActivityDependencyService;
import services.ResourceService;
import services.StateService;
import services.TableDataService;
import views_panels.CreateUpdateProjectPanel;
import views_panels.GanttPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberActivitiesTab extends JPanel 
{
	private JTable table;
	private JScrollPane databasetable;	
	private MemberActivitiesTab thisPanel;
	
	int row,col, currentlySelectedResource;
	
	/**
	 * Create the panel.
	 */
	public MemberActivitiesTab() {			

		this.thisPanel = this;
		
		setLayout(new BorderLayout(0, 0));		
		
		databasetable = new JScrollPane();
		add(databasetable, BorderLayout.CENTER);		
		
		table = this.JTableProject();
		databasetable.setViewportView(table);	
	}
	
	private JTable JTableProject()
	{
		User member = StateService.getStateInstance().memberUser;
		
		if (member == null)
			return null;
		
		int memberId = member.getId();
		
		TableDataService tds = new TableDataService();
		
		table = new JTable(tds.GetMemberActivitiesTableData(memberId), tds.GetMemberActivitiesTableColumns());

		
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.getTableHeader().setPreferredSize(new Dimension(100,30));
		table.setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(table.getRowHeight()+12);
		table.setShowHorizontalLines(true);
	
		
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);		
		
		return table;
	}
	
	public void refreshTable()
	{
		table = JTableProject();
		databasetable.setViewportView(table);
	}
}