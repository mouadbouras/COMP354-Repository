package controllers;
import views.*;
import controllers.MainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPasswordField;

import models.*;
import services.DataService;
import services.StateService;

public class LoginController
{
	boolean loginStatus = false;
	
	public LoginController(JFrame frmCompProject) {
		LoginView view = new LoginView();
		
		view.initialize();
		
		view.txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				view.errorLabel.setText("");

				if(view.txtUserName.getText().trim().equals("Enter Username")){
					view.txtUserName.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(view.txtUserName.getText().trim().equalsIgnoreCase("") )
				{
					view.txtUserName.setText("Enter Username");
				}
			}
		});
	
		view.txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				view.txtPassword.setEchoChar(new JPasswordField().getEchoChar());
				view.errorLabel.setText("");

				if(new String (view.txtPassword.getPassword()).trim().equals("Enter Password")){
					view.txtPassword.setText("");
				}			
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String (view.txtPassword.getPassword()).trim().equalsIgnoreCase("") )
				{
					view.txtPassword.setEchoChar((char) 0);
					view.txtPassword.setText("Enter Password");
				}				
			}
		});

		view.loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				DataService userService = new DataService();	
				
				try 
				{
					String username = view.txtUserName.getText();
					String password = new String (view.txtPassword.getPassword());
					
					System.out.println("username : " + username);
					System.out.println("password : " + password);

					if(userService.GetUser(username,password) == null){
						
						//  FOLLOWING LINE MUST BE UNCOMENTED BEFORE DEMO!!
						//throw new Exception("invalid username/password");
						
						//CODE FOR DEV ENVIRONMENT ONLY. BYPASSES LOGIN.
						User user = userService.GetUser("mouad","password");
						
						System.out.println("login successful");						
						StateService.getStateInstance().setUser(user);	
						MainController.userLogged();						
					}
					
					else
					{
						User user = userService.GetUser(username,password);
						
						System.out.println("login successful");						
						StateService.getStateInstance().setUser(user);					
						
						MainController.userLogged();
						

					}					
				}

				catch (Exception e){
					System.out.println(e.getMessage());
					view.errorLabel.setText("Invalid Username and/or Password");
					setLoginStatus(false);

				}
				//use this to do login;				
			}
		});
		
		frmCompProject.getContentPane().add(view, BorderLayout.CENTER);
		view.addToPanel();
		frmCompProject.getRootPane().setDefaultButton(view.loginButton);	

		frmCompProject.setVisible(true);

	}
	
	
	public boolean getLoginStatus()
	{
		return loginStatus;
	}
	public void setLoginStatus(boolean login)
	{
		loginStatus = login;
	}


}
