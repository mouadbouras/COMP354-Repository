package factories;

import views_tabs.PropertiesTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import daos.PropertyDao;
import models.Property;
import views_panels.CreateUpdatePropertyPanel;


public class PropertiesTabEventFactory
{
	public static JMenuItem deletePropertyMenuItem(PropertiesTab thisPanel)
	{
		JMenuItem deletePropertyMenuItem = new JMenuItem("REMOVE This Property");
		
		deletePropertyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				//REMOVE
				PropertyDao.getInstance().DeleteProperty(thisPanel.currentlySelectedProperty);
				thisPanel.refreshTable();
			}
		});
		
		return deletePropertyMenuItem;
	}
	
	public static JMenuItem updatePropertyMenuItem(PropertiesTab thisPanel)
	{
		JMenuItem updatePropertyMenuItem = new JMenuItem("UPDATE This Property");
		
		updatePropertyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog updateProperty = new JDialog();		
				updateProperty.setTitle("Update Property");
				CreateUpdatePropertyPanel updatePropertiesPanel = new CreateUpdatePropertyPanel(thisPanel, updateProperty, false);
				
				Property p = PropertyDao.getInstance().GetPropertyGivenPropertyId(thisPanel.currentlySelectedProperty);
				
				updatePropertiesPanel.SetupPropertiesForUpdate(p);
			
				updateProperty.add(updatePropertiesPanel);
				updateProperty.pack();
				updateProperty.setVisible(true);	
			}
		});
		
		return updatePropertyMenuItem;
	}
	
	public static JMenuItem newPropertyMenuItem(PropertiesTab thisPanel)
	{
		JMenuItem newPropertyMenuItem = new JMenuItem("CREATE New Property");
		
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
		
		return newPropertyMenuItem;
	}
}
