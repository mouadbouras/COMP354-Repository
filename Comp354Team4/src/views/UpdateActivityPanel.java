package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import models.ProjectDao;
import models.Activity;
import models.ActivityDao;
import models.Project;
import models.User;
import controllers.ConverterService;
import controllers.DataService;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class UpdateActivityPanel extends JPanel {
	
	private ActivitiesTab parentPanel;
	
	private int activityID = 0 ;   
	private JTextField endDateField = new JTextField();
	private JTextField startDateField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField durationField = new JTextField();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField descriptionField = new JTextField();
	
	
	public void setDescriptionField(String description){	
		descriptionField.setText(description);
	}
	public void setDurationField(int duration){	
		durationField.setText(duration + "");
	}
	public void setEndDateField(String endDate){	
		endDateField.setText(endDate);
	}
	public void setStartDateField(String startDate){	
		startDateField.setText(startDate);
	}
	public void setProjectField(String projectFieldStr){	
		nameField.setText(projectFieldStr);
	}
	
	public void setActiveProject(int activityID)
	{
		this.activityID = activityID;
	}
	
	
	/**
	 * Create the panel.
	 */
	public UpdateActivityPanel(ActivitiesTab parentPanel) {
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JPanel activityInfoContainer = new JPanel();
		add(activityInfoContainer, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		activityInfoContainer.setLayout(gbl_panel_2);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		activityInfoContainer.add(panel_6, gbc_panel_6);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		activityInfoContainer.add(panel_5, gbc_panel_5);
		
		JPanel activityNamePanel = createActivityPropertyPanel(activityInfoContainer, nameField, 2, "Activity Name", "");
		JPanel durationPanel = createActivityPropertyPanel(activityInfoContainer, durationField, 3, "Duration", "");
		//JPanel startDatePanel = createActivityPropertyPanel(activityInfoContainer, startDateField, 3, "Start date", "yyyy-mm-dd");
		//JPanel endDatePanel = createActivityPropertyPanel(activityInfoContainer, endDateField, 4, "End date", "yyyy-mm-dd");
		JPanel descriptionPanel = createActivityPropertyPanel(activityInfoContainer, descriptionField, 4, "Description", "");
		
		//what happens when update button is clicked
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{						
				try 
				{
					if(activityID == 0)
					{
						JOptionPane.showMessageDialog(null, "You must select an Activity to update!");
					}
					else
					{
//						System.out.println(activeProjectID );
//			        	Project projectToUpdate = new Project();
//			        	projectToUpdate.setId(activeProjectID);	
//			        	projectToUpdate.setProjectName(projectField.getText());
//			        	projectToUpdate.setStartDate(ConverterService.StringToDate(startDateField.getText()));
//			        	projectToUpdate.setEndDate(ConverterService.StringToDate(endDateField.getText()));
						
						Activity activityToUpdate = ActivityDao.GetActivitiesGivenActivityId(activityID).get(0);
			        	
						Activity activityToWrite = new Activity(activityID, nameField.getText(), descriptionField.getText(), 
								Integer.parseInt(durationField.getText()), ConverterService.DateToString(activityToUpdate.getStartDate()), 
								ConverterService.DateToString(activityToUpdate.getEndDate()), 
								State.getStateInstance().getProject().getId());
						
						ActivityDao activityDao = new ActivityDao();
						activityDao.UpdateActivity(activityToWrite);
						
						JOptionPane.showMessageDialog(null, "The Activity was updated succesfully! ");

						nameField.setText(activityToWrite.getActivityName());
						descriptionField.setText(activityToWrite.getActivityDescription());
						//startDateField.setText(ConverterService.DateToString(activityToUpdate.getStartDate()));
						//endDateField.setText(ConverterService.DateToString(activityToUpdate.getEndDate()));
						activityID = activityToWrite.getId();
						durationField.setText(activityToWrite.getNormalDuration() + "");
							
					}

				}

				catch (Exception e)
				{
					System.err.println(e.getClass());
					System.err.println(e.getMessage());
					
					JOptionPane.showMessageDialog(null, "An error occured while updating this activity! Try again.");
					nameField.setText("");
					descriptionField.setText("");
					//startDateField.setText("");
					//endDateField.setText("");
					durationField.setText("");
					activityID = 0;	
					}		
				finally
				{
					parentPanel.refreshTable();				
				}
			}
		});		
		

		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 6;
		activityInfoContainer.add(btnUpdate, gbc_btnCreate);

		nameField.setText("");
		descriptionField.setText("");
		durationField.setText("");
		//startDateField.setText("yyyy-mm-dd");
		//endDateField.setText("yyyy-mm-dd");
		
			
	}
	
	private JPanel createActivityPropertyPanel(JPanel parentPanel, JTextField propertyTextField, int position, String propertyName, String defaultValue) {
		JPanel containerPanel = new JPanel();
		GridBagConstraints containerConstraints = new GridBagConstraints();
		containerConstraints.insets = new Insets(0, 0, 5, 0);
		containerConstraints.fill = GridBagConstraints.BOTH;
		containerConstraints.gridx = 0;
		containerConstraints.gridy = position;
		parentPanel.add(containerPanel, containerConstraints);
		GridBagLayout containerLayout = new GridBagLayout();
		containerLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		containerLayout.rowHeights = new int[]{0, 0};
		containerLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		containerLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		containerPanel.setLayout(containerLayout);
		
		JLabel propertyLabel = new JLabel(propertyName);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.insets = new Insets(0, 0, 0, 5);
		labelConstraints.gridx = 1;
		labelConstraints.gridy = 0;
		containerPanel.add(propertyLabel, labelConstraints);
		
		JPanel textFieldPanel = new JPanel();
		GridBagConstraints textFieldConstraints = new GridBagConstraints();
		textFieldConstraints.fill = GridBagConstraints.BOTH;
		textFieldConstraints.gridx = 3;
		textFieldConstraints.gridy = 0;
		containerPanel.add(textFieldPanel, textFieldConstraints);
		textFieldPanel.setLayout(new BorderLayout(0, 0));
		
		propertyTextField.setColumns(10);
		textFieldPanel.add(propertyTextField, BorderLayout.CENTER);
		propertyTextField.setText(defaultValue);
		return containerPanel;
	}


}
