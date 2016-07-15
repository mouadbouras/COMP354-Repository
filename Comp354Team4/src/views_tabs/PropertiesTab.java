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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSplitPane;

import org.jfree.ui.RefineryUtilities;

import dao.ProjectDao;
import dao.PropertyDao;
import dao.ResourceDao;

public class PropertiesTab extends JPanel {
	private JTable table;
	private JScrollPane databasetable;
	
	private JPopupMenu createpopup;	
	private JMenuItem newPropertyMenuItem;
	
	private JPopupMenu popup;
	private JMenuItem updatePropertyMenuItem;
	private JMenuItem deletePropertyMenuItem;	

	private int row, col, currentlySelectedProperty;
	
	private PropertiesTab thisPanel;
	
	private PropertyDao propertyDao = new PropertyDao();
	
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
		
		this.deletePropertyMenuItem = new JMenuItem("REMOVE This Property");
		this.updatePropertyMenuItem = new JMenuItem("UPDATE This Property");
		
		popup.add(deletePropertyMenuItem);		
		popup.add(updatePropertyMenuItem);		
		
		deletePropertyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				//REMOVE
				propertyDao.DeleteProperty(currentlySelectedProperty);
				refreshTable();
			}
		});
		
		updatePropertyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog updateProperty = new JDialog();		
				updateProperty.setTitle("Update Property");
				CreateUpdatePropertyPanel updatePropertiesPanel = new CreateUpdatePropertyPanel(thisPanel, updateProperty, false);
				
				Property p = new PropertyDao().GetPropertyGivenPropertyId(currentlySelectedProperty);
				
				updatePropertiesPanel.SetupPropertiesForUpdate(p);
			
				updateProperty.add(updatePropertiesPanel);
				updateProperty.pack();
				updateProperty.setVisible(true);	
			}
		});
		
        
		this.createpopup = new JPopupMenu();
		
		this.newPropertyMenuItem = new JMenuItem("CREATE New Property");
		
		createpopup.add(newPropertyMenuItem);		
		
		newPropertyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog newProperty = new JDialog();		
				newProperty.setTitle("CREATE Property");
				JPanel createPropertiesPanel = new CreateUpdatePropertyPanel(thisPanel, newProperty, true);
			
				newProperty.add(createPropertiesPanel);
				newProperty.pack();
				newProperty.setVisible(true);			
				
			}
		});
	}
	
	private JTable JTableProject()
	{
		Activity currentActivity = StateService.getStateInstance().getActivity();
		
		if (currentActivity == null) 
			return null;
		
		TableDataService tds = new TableDataService();
		table = new JTable(tds.GetPropertyTableData(currentActivity.getId()), tds.GetPropertyTableColumns());
	
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
