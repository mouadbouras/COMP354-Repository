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

import data.ActivityDao;
import data.SqliteSetup;
import models.User;
import services.DataService;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.swing.ImageIcon;



public class Main {

	private JFrame frmCompProject;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JLabel errorLabel ;
	private JLabel logo;
	private JButton loginButton;
	
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
		frmCompProject.setTitle("Snow Manager");
		frmCompProject.setBounds(100, 100, 556, 543);
		frmCompProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      try {                
	    	  BufferedImage img = ImageIO.read(new File("./logo.jpg"));
	    	  logo = new JLabel(new ImageIcon(img));	
	    	  logo.setBounds(136, 64, 300, 180);

	      } 
	      catch (IOException ex) {
	    	  System.out.println(ex.getMessage());
	      }

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(Color.WHITE);
		frmCompProject.getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.add(logo); 
  

		
		JLabel userLabel = DefaultComponentFactory.getInstance().createLabel("Username :");
		userLabel.setBounds(174, 293, 80, 16);
		panel.add(userLabel);
		
		JLabel passLabel = DefaultComponentFactory.getInstance().createLabel("Password :");
		passLabel.setBounds(174, 339, 80, 16);
		panel.add(passLabel);
		
		JLabel errorLabel = DefaultComponentFactory.getInstance().createLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(174, 372, 242, 16);
		panel.add(errorLabel);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(266, 285, 150, 34);
		txtUserName.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				errorLabel.setText("");

				if(txtUserName.getText().trim().equals("Enter Username")){
					txtUserName.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUserName.getText().trim().equalsIgnoreCase("") )
				{
					txtUserName.setText("Enter Username");
				}
			}
		});
		txtUserName.setToolTipText("Login");
		txtUserName.setText("Enter Username");
		txtUserName.setColumns(10);
		panel.add(txtUserName);
		
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(266, 331, 150, 34);
		txtPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtPassword.setText("Enter Password");
		txtPassword.setToolTipText("Password");
		txtPassword.setEchoChar((char) 0);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPassword.setEchoChar(new JPasswordField().getEchoChar());
				errorLabel.setText("");

				if(new String (txtPassword.getPassword()).trim().equals("Enter Password")){
					txtPassword.setText("");
				}			
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String (txtPassword.getPassword()).trim().equalsIgnoreCase("") )
				{
					txtPassword.setEchoChar((char) 0);
					txtPassword.setText("Enter Password");
				}				
			}
		});
		
		panel.add(txtPassword);
		
		loginButton = new JButton("LOG IN");
		loginButton.setBounds(190, 400, 150, 34);
		loginButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		

		
		loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				DataService userService = new DataService();	
				
				try 
				{
					String username = txtUserName.getText();
					String password = new String (txtPassword.getPassword());
					
					System.out.println("username : " + username);
					System.out.println("password : " + password);

					if(userService.GetUser(username,password) == null){
						//  FOLLOWING LINE MUST BE UNCOMENTED BEFORE DEMO!!
						//throw new Exception("invalid username/password");
						
						//**** CODE FOR DEV ENVIRONMENT ONLY. BYPASSES LOGIN.*****
						User user = userService.GetUser("mouad","password");
						State.getStateInstance().setUser(user);					
						
						frmCompProject.getContentPane().removeAll();					
						frmCompProject.getContentPane().add(new MainView());
						frmCompProject.setExtendedState(JFrame.MAXIMIZED_BOTH); 
						frmCompProject.setVisible(true);					
						frmCompProject.repaint();
						
						//******************************************************** 
						
					}
					else
					{
						User user = userService.GetUser(username,password);
						
						System.out.println("login successful");
						
						State.getStateInstance().setUser(user);					
						
						frmCompProject.getContentPane().removeAll();					
						frmCompProject.getContentPane().add(new MainView());
						frmCompProject.setExtendedState(JFrame.MAXIMIZED_BOTH); 
						frmCompProject.setVisible(true);					
						frmCompProject.repaint();
					}
					
					

					
				}

				catch (Exception e)
				{
					System.out.println(e.getMessage());
					errorLabel.setText("Invalid Username and/or Password");
				}
				//use this to do login;				
			}
		});
		
		panel.setLayout(null);
		panel.add(loginButton);
		frmCompProject.getRootPane().setDefaultButton(loginButton);

	
	

		
	}
}

