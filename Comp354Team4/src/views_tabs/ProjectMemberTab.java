package views_tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import daos.ProjectDao;
import models.Project;
import models.User;
import services.DataService;
import services.StateService;
import views_panels.GanttPanel;

public class ProjectMemberTab extends JPanel{
	
	private JTable Usertable;
	private JScrollPane databasetable;
	private JTable table;
	
	private JPopupMenu popup;	
	private JMenuItem viewProjectMemberMenuItem;
	
	private int row, col, currentlySelectedUserId;
	
	public ProjectMemberTab() {
		setLayout(new BorderLayout(0, 0));
		databasetable = new JScrollPane();
		add(databasetable, BorderLayout.CENTER);
		
		SetupPopup();
		
		table = this.JTableProject();
		databasetable.setViewportView(table);
	}
	
	public void SetupPopup()
	{
		popup = new JPopupMenu();
		
		viewProjectMemberMenuItem = new JMenuItem("VIEW Activities of This Project Member");
		
		popup.add(viewProjectMemberMenuItem);	
		
		viewProjectMemberMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				User temp = new User();
				temp.setId(currentlySelectedUserId);
				
				StateService.getStateInstance().memberUser = temp;
				
	        	MemberActivitiesTab tab = StateService.getStateInstance().memberActivitiesTab;
	        	tab.refreshTable();
	        	StateService.getStateInstance().managerView.tabbedPane.setSelectedIndex(4); //switches tabbed panes to the activity tab pane
			}
		});		
	}
	
	private JTable JTableProject(){
		User temp = StateService.getStateInstance().user;
		DataService ds = new DataService();
		table = new JTable(ds.GetUserTableData(temp), ds.GetUserTableColumns());
	
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		table.addMouseListener(new java.awt.event.MouseAdapter() 
		{			
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());         
		        currentlySelectedUserId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	popup.show(evt.getComponent(), evt.getX(), evt.getY());
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
