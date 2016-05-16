package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import models.User;
import services.DataService;

import javax.swing.JTable;
import javax.swing.JList;

public class Main {

	private JFrame frmCompProject;
	private JTextField txtUserName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCompProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frmCompProject = new JFrame();
		frmCompProject.setTitle("COMP354 Project");
		frmCompProject.setBounds(100, 100, 556, 543);
		frmCompProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(Color.WHITE);
		frmCompProject.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("LOG IN");
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				DataService userService = new DataService();	
				
				try 
				{
					int userId = Integer.parseInt(txtUserName.getText());
					User user = userService.GetUser(userId);
					State.getStateInstance().setUser(user);					
				
					frmCompProject.getContentPane().removeAll();					
					frmCompProject.getContentPane().add(new MainView());
					frmCompProject.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frmCompProject.setVisible(true);					
					frmCompProject.repaint();
					
				}
				catch (Exception e)
				{
					txtUserName.setText("User Id Is Not Correct");
				}
				//use this to do login;				
			}
		});
		
		btnNewButton.setBounds(188, 253, 150, 34);
		panel.add(btnNewButton);
		
		txtUserName = new JTextField();
		txtUserName.setText("Enter User Id");
		txtUserName.setToolTipText("User Name");
		txtUserName.setBounds(188, 194, 150, 34);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
	}
}

