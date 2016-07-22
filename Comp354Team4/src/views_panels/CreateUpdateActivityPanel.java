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
import models.User;
import services.ConverterService;
import services.DataService;
import services.StateService;
import views_tabs.ActivitiesTab;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import daos.ActivityDao;
import daos.ProjectDao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CreateUpdateActivityPanel extends JPanel {
	
	private ActivitiesTab parentPanel;
	private JDialog dialog;
	private JTextField activityField;
	private int activityID = 0 ;   
	//private int projectID;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField descriptionField;
	private JTextField durationField;
	
	/**
	 * Create the panel.
	 */
	
	public void SetupActivityForUpdate(Activity a)
	{
		activityID = a.getId();
		durationField.setText(Integer.toString(a.getDuration()));			
		activityField.setText(a.getActivityName());		
		descriptionField.setText(a.getActivityDescription());
	}
	
	public CreateUpdateActivityPanel(ActivitiesTab parentPanel, JDialog dialog, boolean create) {
		this.parentPanel = parentPanel;
		this.dialog = dialog;

		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_2.add(panel_6, gbc_panel_6);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel label_1 = new JLabel("Activity Name");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 0;
		panel_3.add(label_1, gbc_label_1);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 3;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		activityField = new JTextField();
		activityField.setColumns(10);
		panel_4.add(activityField, BorderLayout.CENTER);
		
		activityField.setText("");
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 4;
		panel_2.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblDescription = new JLabel("Description   ");
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
		
		descriptionField = new JTextField();
		descriptionField.setText("");
		descriptionField.setColumns(10);
		panel_12.add(descriptionField, BorderLayout.CENTER);
		descriptionField.setText("");
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 5;
		panel_2.add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JLabel lblDuration = new JLabel("Duration        ");
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.insets = new Insets(0, 0, 0, 5);
		gbc_lblDuration.gridx = 1;
		gbc_lblDuration.gridy = 0;
		panel_9.add(lblDuration, gbc_lblDuration);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 3;
		gbc_panel_11.gridy = 0;
		panel_9.add(panel_11, gbc_panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		durationField = new JTextField();
		durationField.setText("");
		durationField.setColumns(10);
		panel_11.add(durationField, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 6;
		panel_2.add(panel_5, gbc_panel_5);
		
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
						int duration = Integer.parseInt(durationField.getText());	
						if (duration < 1)
						{
							JOptionPane.showMessageDialog(null, "Please enter the correct number of days (more than 0)");
							return;
						}							
						
						Project activeProject = StateService.getStateInstance().project;
						Activity activity = new Activity(activityField.getText(), descriptionField.getText(), duration , activeProject.getId());
						
						System.out.println("insert activity into project : " + activeProject.getId());
						ActivityDao.getInstance().InsertActivity(activity);
						
						JOptionPane.showMessageDialog(null, "The Activity was created succesfully! ");
						
						dialog.dispose();						
					}
					
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Please enter an integer value for Duration");	
					}
					
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while creating this activity! Try again.");	
					}
					
					finally
					{
						parentPanel.refreshTable();
					}
					
				}
			});	
		}
		
		else 
		{
			//what happens when update button clicked
			btnCreate = new JButton("Update");
			btnCreate.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{						
					try 
					{
						int duration = Integer.parseInt(durationField.getText());	
						if (duration < 1)
						{
							JOptionPane.showMessageDialog(null, "You must select an Activity to update!");
						}
										
						Activity activityToUpdate = new Activity(activityField.getText(), descriptionField.getText(), duration, StateService.getStateInstance().project.getId());
						activityToUpdate.setId(activityID);
						
						ActivityDao.getInstance().UpdateActivity(activityToUpdate);
						
						JOptionPane.showMessageDialog(null, "The Activity was updated succesfully! ");
						
						dialog.dispose();							

					}

					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while updating this activity! Try again.");
					}
					
					finally
					{
						parentPanel.refreshTable();				
					}
				}
			});					
		}		
		
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 11;
		panel_2.add(btnCreate, gbc_btnCreate);
	}		
}



