package views;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.util.Date;


import models.ProjectDao;
import models.Project;
import models.User;
import controllers.ConverterService;
import controllers.DataService;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UpdateProjectPanel extends JPanel {
	
	private ProjectsTab parentPanel;
	
	private int activeProjectID = 0 ;   
	private JTextField endDateField;
	private JTextField startDateField;
	private JTextField projectField;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setEndDateField(String endDate){	
		endDateField.setText(endDate);
	}
	public void setStartDateField(String startDate){	
		startDateField.setText(startDate);
	}
	public void setProjectField(String projectFieldStr){	
		projectField.setText(projectFieldStr);
	}
	
	public void setActiveProject(int project)
	{
		activeProjectID = project;
	}
	
	
	/**
	 * Create the panel.
	 */
	public UpdateProjectPanel(ProjectsTab parentPanel) {
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		gbc_panel_5.gridy = 1;
		panel_2.add(panel_5, gbc_panel_5);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel label_1 = new JLabel("Project Name");
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
		
		projectField = new JTextField();
		projectField.setColumns(10);
		panel_4.add(projectField, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 3;
		panel_2.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblStartDate = new JLabel("Start Date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 0;
		panel_10.add(lblStartDate, gbc_lblStartDate);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 3;
		gbc_panel_11.gridy = 0;
		panel_10.add(panel_11, gbc_panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		startDateField = new JTextField();
		startDateField.setColumns(10);
		panel_11.add(startDateField, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 4;
		panel_2.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblNewLabel = new JLabel("End Date");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_8.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 3;
		gbc_panel_9.gridy = 0;
		panel_8.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		endDateField = new JTextField();
		panel_9.add(endDateField, BorderLayout.CENTER);
		endDateField.setColumns(10);
		
		//what happens when create button clicked
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{						
				try 
				{
					if(activeProjectID == 0)
					{
						JOptionPane.showMessageDialog(null, "You must select a Project to update!");
					}
					else
					{
						System.out.println(activeProjectID );
			        	Project projectToUpdate = new Project(activeProjectID, projectField.getText(),
			        			startDateField.getText(), endDateField.getText(), State.getStateInstance().getUser().getId());
			        	
						ProjectDao projectDao = new ProjectDao();
						projectDao.UpdateProject(projectToUpdate);
						
						JOptionPane.showMessageDialog(null, "The Project was updated succesfully! ");

						projectField.setText(projectToUpdate.getProjectName());
						startDateField.setText(ConverterService.DateToString(projectToUpdate.getStartDate()));
						endDateField.setText(ConverterService.DateToString(projectToUpdate.getEndDate()));
						activeProjectID = projectToUpdate.getId();
					}
					//parentPanel.refreshTable();
				}
				
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "An error occured while updating this project! Try again.");
					projectField.setText("");
					startDateField.setText("");
					endDateField.setText("");
					activeProjectID = 0;
					//projectField.setText("error!");
				}		
				finally
				{
					parentPanel.refreshTable();
					//startDateField.setText("");
					//endDateField.setText("");					
				}
			}
		});		

		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 6;
		panel_2.add(btnUpdate, gbc_btnCreate);
		
		projectField.setText("");
		startDateField.setText("yyyy-mm-dd");
		endDateField.setText("yyyy-mm-dd");
	}


}
