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

public class CreateActivityPanel extends JPanel {
	
	private ActivitiesTab parentPanel;
	//private JTextField endDateField = new JTextField();
	//private JTextField startDateField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField durationField = new JTextField();
	//private int projectID;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField descriptionField = new JTextField();
	
	/**
	 * Create the panel.
	 */
	public CreateActivityPanel(ActivitiesTab parentPanel) {
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
		
		//what happens when create button clicked
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{						
				try 
				{
					Project activeProject = State.getStateInstance().getProject();
					Activity activity = new Activity(nameField.getText(), descriptionField.getText(), 
							Integer.parseInt(durationField.getText()), 
							"1111-11-11", "1111-11-11", activeProject.getId());
					
					ActivityDao activityDao = new ActivityDao();
					System.out.println("insert activity into project : " + activeProject.getId());
					activityDao.InsertActivity(activity);
					
					JOptionPane.showMessageDialog(null, "The Activity was created succesfully! ");
				}
				
				catch (Exception e)
				{
					System.err.println(e.toString());
					System.err.println(e.getCause());
					JOptionPane.showMessageDialog(null, "An error occured while creating this activity! Try again.");	
				}		
				finally
				{
					parentPanel.refreshTable();
					nameField.setText("");
					descriptionField.setText("");
					durationField.setText("");
					//startDateField.setText("yyyy-mm-dd");
					//endDateField.setText("yyyy-mm-dd");				
				}
			}
		});		

		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 6;
		activityInfoContainer.add(btnCreate, gbc_btnCreate);
		
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



