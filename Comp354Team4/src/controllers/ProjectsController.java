package controllers;
import views.*;
import controllers.MainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import models.*;

public class ProjectsController
{
	boolean loginStatus = false;
	
	public ProjectsController(JFrame frmCompProject) {
		
		ProjectsView view = new ProjectsView () ;
		//frmCompProject.setLayout(new BorderLayout(0, 0));
		State.getStateInstance().setProjectsView(view);

		
		frmCompProject.getContentPane().add(view);
		frmCompProject.setVisible(true);					
		frmCompProject.repaint();
	}

}
