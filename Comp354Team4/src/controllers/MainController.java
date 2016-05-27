package controllers;
import views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPasswordField;

import models.*;

 public final class MainController  
{
	private static JFrame frmCompProject;
	
	private MainController() {
		
	}
	
	public static void login(){
		frmCompProject = new JFrame();
		frmCompProject.setTitle("Snow Manager");
		frmCompProject.setBounds(100, 100, 556, 543);
		frmCompProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginController login = new LoginController(frmCompProject);
	}

	public static  void userLogged()
	{
		frmCompProject.getContentPane().removeAll();
		//frmCompProject.setBounds(100, 100, 800, 543);
		frmCompProject.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		ProjectsController projects = new ProjectsController(frmCompProject);


	}
	

}
