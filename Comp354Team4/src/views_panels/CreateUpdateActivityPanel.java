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

import controllers.ConverterService;
import dao.ActivityDao;
import dao.ProjectDao;

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
	private JTextField textField;
	

	
	/**
	 * Create the panel.
	 */
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
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 2;
		panel_2.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblStartDate = new JLabel("Start Date      ");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 0;
		panel_10.add(lblStartDate, gbc_lblStartDate);
		// Don't know about the formatter, but there it is...
		
		UtilDateModel modelstart = new UtilDateModel();
		Properties tempp1 = new Properties();
		tempp1.put("text.today", "Today");
		tempp1.put("text.month", "Month");
		tempp1.put("text.year", "Year");
		JDatePanelImpl datestartPanel = new JDatePanelImpl(modelstart, tempp1);
		JDatePickerImpl startdatePicker = new JDatePickerImpl(datestartPanel, new DateComponentFormatter());
		SpringLayout springstartLayout = (SpringLayout) startdatePicker.getLayout();
		springstartLayout.putConstraint(SpringLayout.WEST, startdatePicker.getJFormattedTextField(), 0, SpringLayout.WEST, startdatePicker);
		GridBagConstraints gbc_startdatePicker = new GridBagConstraints();
		gbc_startdatePicker.gridx = 3;
		gbc_startdatePicker.gridy = 0;
		gbc_startdatePicker.anchor = GridBagConstraints.EAST;
		panel_10.add(startdatePicker, gbc_startdatePicker);		
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 3;
		panel_2.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		UtilDateModel modelend = new UtilDateModel();
		Properties tempp2 = new Properties();
		tempp2.put("text.today", "Today");
		tempp2.put("text.month", "Month");
		tempp2.put("text.year", "Year");
		JDatePanelImpl enddatePanel = new JDatePanelImpl(modelend, tempp2);
		JDatePickerImpl datePickerend = new JDatePickerImpl(enddatePanel, new DateComponentFormatter());
		SpringLayout springLayoutend = (SpringLayout) datePickerend.getLayout();
		springLayoutend.putConstraint(SpringLayout.WEST, datePickerend.getJFormattedTextField(), 0, SpringLayout.WEST, datePickerend);
		GridBagConstraints gbc_datePickerend = new GridBagConstraints();
		gbc_datePickerend.gridx = 3;
		gbc_datePickerend.gridy = 0;
		gbc_datePickerend.anchor = GridBagConstraints.EAST;
		panel_8.add(datePickerend, gbc_datePickerend);
		
		startdatePicker.getJFormattedTextField().setText("yyyy-mm-dd");
		datePickerend.getJFormattedTextField().setText("yyyy-mm-dd");
		
		JLabel lblNewLabel = new JLabel("End Date       ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_8.add(lblNewLabel, gbc_lblNewLabel);
		datePickerend.getJFormattedTextField().setText("yyyy-mm-dd");		
		
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
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		panel_11.add(textField, BorderLayout.NORTH);
		
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
		//what happens when create button clicked
				btnCreate = new JButton("Create");
				btnCreate.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{						
						try 
						{
							Date startDate = (Date) startdatePicker.getModel().getValue();
							Date endDate = (Date) datePickerend.getModel().getValue();
							
							if(endDate.before(startDate))
				            {
				            	JOptionPane.showMessageDialog(null, "Please select the correct date!");
				            }
							else{
								Project activeProject = StateService.getStateInstance().getProject();
								Activity activity = new Activity(activityField.getText(), descriptionField.getText(), 
										startDate, endDate, activeProject.getId());
								
								ActivityDao activityDao = new ActivityDao();
								System.out.println("insert activity into project : " + activeProject.getId());
								activityDao.InsertActivity(activity);
								
								JOptionPane.showMessageDialog(null, "The Activity was created succesfully! ");
								
								dialog.dispose();
							}							
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
						if(activityID == 0)
						{
							JOptionPane.showMessageDialog(null, "You must select an Activity to update!");
						}
						else
						{
							//set up activity
							
							Date startDate = (Date) startdatePicker.getModel().getValue();
							Date endDate = (Date) datePickerend.getModel().getValue();
							
							Activity activityToUpdate = new Activity(activityField.getText(), descriptionField.getText(), startDate, endDate, StateService.getStateInstance().getProject().getId());
							activityToUpdate.setId(activityID);
							
							ActivityDao activityDao = new ActivityDao();
							activityDao.UpdateActivity(activityToUpdate);
							
							JOptionPane.showMessageDialog(null, "The Activity was updated succesfully! ");
							
							dialog.dispose();							
						}

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



