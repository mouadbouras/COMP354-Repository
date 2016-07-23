package views_tabs;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;

import models.Activity;
import models.Project;
import models.Property;
import models.Resource;
import models.User;
import services.DataService;
import services.ResourceService;
import services.StateService;
import services.TableDataService;
import views_panels.CreateUpdateProjectPanel;
import views_panels.CreateUpdatePropertyPanel;
import views_panels.GanttPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSplitPane;

import org.jfree.ui.RefineryUtilities;

import daos.ProjectDao;
import daos.PropertyDao;
import daos.ResourceDao;
import factories.PropertiesTabEventFactory;

public class PropertiesTab extends JPanel {
	private JTable table;
	private JScrollPane databasetable;
	
	private JPopupMenu createpopup;	
	private JMenuItem newPropertyMenuItem;
	
	private JPopupMenu popup;
	private JMenuItem updatePropertyMenuItem;
	private JMenuItem deletePropertyMenuItem;	

	public int row, col, currentlySelectedProperty;
	
	private PropertiesTab thisPanel;	

	/**
	 * Create the panel.
	 */
	public PropertiesTab() {			

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
		
		this.deletePropertyMenuItem = PropertiesTabEventFactory.deletePropertyMenuItem(thisPanel);
		this.updatePropertyMenuItem = PropertiesTabEventFactory.updatePropertyMenuItem(thisPanel);
		
		popup.add(deletePropertyMenuItem);		
		popup.add(updatePropertyMenuItem);		
		        
		this.createpopup = new JPopupMenu();
		
		this.newPropertyMenuItem = PropertiesTabEventFactory.newPropertyMenuItem(thisPanel);
		
		createpopup.add(newPropertyMenuItem);		
	}
	
	private JTable JTableProject()
	{
		Activity currentActivity = StateService.getStateInstance().activity;
		
		if (currentActivity == null) 
			return null;
		
		TableDataService tds = new TableDataService();
		table = new JTable(tds.GetPropertyTableData(currentActivity.getId()), tds.GetPropertyTableColumns());
		
		
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.getTableHeader().setPreferredSize(new Dimension(100,30));
		table.setFont(new Font("Helvetica", Font.PLAIN , 15));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(table.getRowHeight()+12);
		table.setShowHorizontalLines(true);

		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() 
		{			
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {
		        row = table.rowAtPoint(evt.getPoint());
		        col = table.columnAtPoint(evt.getPoint());         
		        currentlySelectedProperty = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	
		        if(SwingUtilities.isRightMouseButton(evt)){
		        	popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }	
		    }
		});
		
		table.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() 
		{			
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
