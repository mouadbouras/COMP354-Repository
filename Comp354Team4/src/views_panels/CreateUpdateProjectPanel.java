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

import models.Project;
import models.User;
import services.DataService;
import services.StateService;
import views_tabs.ProjectsTab;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controllers.ConverterService;
import dao.ProjectDao;

import javax.swing.SpringLayout;


public class CreateUpdateProjectPanel extends JPanel {
	
	private ProjectsTab parentPanel;
	private JTextField projectField;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JDialog dialog;
	private int activeProjectID;
	
	/**
	 * Create the panel.
	 */
	public CreateUpdateProjectPanel(ProjectsTab parentPanel, JDialog dialog, boolean create) {
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
		
		//what happens when create button clicked
		JButton btnCreate = null;
		if (create){
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
				            Project project = new Project(projectField.getText(), startDate, 
				            		endDate, StateService.getStateInstance().getUser().getId());					
							
							ProjectDao projectDao = new ProjectDao();
							
							if (projectDao.InsertProject(project)) {					
								
								JOptionPane.showMessageDialog(null, "The project was created succesfully!");
								
								dialog.dispose();
								
							} else {
								JOptionPane.showMessageDialog(null, "An error occured while creating this project! Try again.");
							}
			            }
			            
					}
					
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while creating this project! Try again.");
					}		
					finally
					{
						parentPanel.refreshTable();
						projectField.setText("");
						startdatePicker.getJFormattedTextField().setText("yyyy-mm-dd");
						datePickerend.getJFormattedTextField().setText("yyyy-mm-dd");
					}
				}
			});		
		} 
		else 
		{
			//what happens when create button clicked
			btnCreate = new JButton("Update");
			btnCreate.addActionListener(new ActionListener() 
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
							Date startDate = (Date) startdatePicker.getModel().getValue();
							Date endDate = (Date) datePickerend.getModel().getValue();	
							
							System.out.println(activeProjectID);
				        	Project projectToUpdate = new Project(projectField.getText(),
				        			startDate, endDate, StateService.getStateInstance().getUser().getId());
				        	projectToUpdate.setId(activeProjectID);
				        	
							ProjectDao projectDao = new ProjectDao();
							projectDao.UpdateProject(projectToUpdate);
							
							JOptionPane.showMessageDialog(null, "The Project was updated succesfully! ");
							
							dialog.dispose();
						}
						//parentPanel.refreshTable();
					}
					
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "An error occured while updating this project! Try again.");
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
		gbc_btnCreate.gridy = 6;
		panel_2.add(btnCreate, gbc_btnCreate);
		
		projectField.setText("");
	}


	private String getDate(Date date){
		String temp = date+"";
		String year=null;
		String monthtemp=null;
		String month=null;
		String montharray[]={"Jan","Feb","Mar","Apr","May","Jun","Jul",
				"Aug","Sep","Oct","Nov","Dec"};
		String day=null;
		if(temp==null)
			System.out.println("error");
		else{
			year=temp.substring(temp.length()-4, temp.length());
			
			monthtemp=temp.substring(4, 7);
			for(int i=0;i<12;i++){
				if(monthtemp.equals(montharray[i]))
					{
					if(i>=9)
						month=i+"";
					else 
						month="0"+i;
					}
			}
			day=temp.substring(8, 10);
			
			temp=year+"-"+month+"-"+day;
		}
		return temp;
	}
}
