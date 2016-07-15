package views_panels;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import models.Activity;
import models.Project;
import models.Property;
import models.User;
import services.DataService;
import services.StateService;
import views_tabs.ActivitiesTab;
import views_tabs.ProjectsTab;
import views_tabs.PropertiesTab;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.ActivityDao;
import dao.ProjectDao;
import dao.PropertyDao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CreateUpdatePropertyPanel extends JPanel {
	
	private PropertiesTab parentPanel;
	//private int projectID;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField propertyTextField;
	private JTextField propertyDescriptionField;
	private JTextField propertyNameField;
	private int activityId;
	private int id;
	private JDialog dialog;

	public void SetupPropertiesForUpdate(Property p)
	{
		id = p.id;
		propertyNameField.setText(p.propertyName);
		propertyDescriptionField.setText(p.propertyDescription);
		propertyTextField.setText(p.propertyText);
	}	
	 
	public CreateUpdatePropertyPanel(PropertiesTab parentPanel, JDialog dialog, boolean create) {
		this.parentPanel = parentPanel;
		this.dialog = dialog;
		
		activityId = StateService.getStateInstance().activity.getId();
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_2.add(panel_6, gbc_panel_6);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		panel_2.add(panel_5, gbc_panel_5);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 4;
		panel_2.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblPropertyName = new JLabel("Property Name         ");
		GridBagConstraints gbc_lblPropertyName = new GridBagConstraints();
		gbc_lblPropertyName.insets = new Insets(0, 0, 0, 5);
		gbc_lblPropertyName.gridx = 1;
		gbc_lblPropertyName.gridy = 0;
		panel_10.add(lblPropertyName, gbc_lblPropertyName);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 3;
		gbc_panel_11.gridy = 0;
		panel_10.add(panel_11, gbc_panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		propertyNameField = new JTextField();
		propertyNameField.setText("");
		propertyNameField.setColumns(10);
		panel_11.add(propertyNameField, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 5;
		panel_2.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblPropertyDescription = new JLabel("Property Description");
		GridBagConstraints gbc_lblPropertyDescription = new GridBagConstraints();
		gbc_lblPropertyDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblPropertyDescription.gridx = 1;
		gbc_lblPropertyDescription.gridy = 0;
		panel_8.add(lblPropertyDescription, gbc_lblPropertyDescription);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 3;
		gbc_panel_9.gridy = 0;
		panel_8.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		propertyDescriptionField = new JTextField();
		propertyDescriptionField.setText("");
		propertyDescriptionField.setColumns(10);
		panel_9.add(propertyDescriptionField, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 6;
		panel_2.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblDescription = new JLabel("Property Text          ");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 0;
		panel_7.add(lblDescription, gbc_lblDescription);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 3;
		gbc_panel_12.gridy = 0;
		panel_7.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		propertyTextField = new JTextField();
		propertyTextField.setText("");
		propertyTextField.setColumns(10);
		panel_12.add(propertyTextField, BorderLayout.CENTER);
		
		JButton btnCreate = null;
		
		if (create)
		{
			btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{						
					try 
					{
						Property property = new Property();
						property.activityId = activityId;
						property.propertyName = propertyNameField.getText();
						property.propertyDescription = propertyDescriptionField.getText();
						property.propertyText = propertyTextField.getText();
						
						new PropertyDao().InsertProperty(property);
						
						JOptionPane.showMessageDialog(null, "The Property was created succesfully! ");
						
						dialog.dispose();		
						
						parentPanel.refreshTable();
					}
					
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while creating this activity! Try again.");	
					}		
					finally
					{
	
					}
				}
			});		
		}
		else 
		{
			btnCreate = new JButton("Update");
			btnCreate.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{						
					try 
					{
						Property property = new Property();
						property.id = id;
						property.activityId = activityId;
						property.propertyName = propertyNameField.getText();
						property.propertyDescription = propertyDescriptionField.getText();
						property.propertyText = propertyTextField.getText();
						
						new PropertyDao().UpdateProperty(property);
						
						JOptionPane.showMessageDialog(null, "The Property was updated succesfully! ");
						
						dialog.dispose();		
						
						parentPanel.refreshTable();
						
					}
					
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while updating this activity! Try again.");	
					}		
					finally
					{
	
					}
				}
			});	
		}

		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 7;
		panel_2.add(btnCreate, gbc_btnCreate);
		propertyTextField.setText("");
			}
		
}



