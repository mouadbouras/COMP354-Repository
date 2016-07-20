package controllers;
import views.*;
import controllers.MainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import models.*;
import services.StateService;

public class ManagerController
{
	boolean loginStatus = false;
	
	public ManagerController(JFrame frmCompProject) {
		
		ManagerView view = new ManagerView();
		view.setLayout(new GridLayout(1,2));
		StateService.getStateInstance().managerView = view;
		
		frmCompProject.getContentPane().add(view, BorderLayout.CENTER);
		frmCompProject.setVisible(true);					
		frmCompProject.repaint();
	}
}
