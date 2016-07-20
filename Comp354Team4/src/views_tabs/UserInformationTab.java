package views_tabs;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import models.User;
import services.StateService;

public class UserInformationTab extends JPanel{

	private JTable Usertable;
	private JScrollPane databasetable;
	private JTable table;
	private JTextArea nameTab;
	private BufferedImage image;

	
	public UserInformationTab() 
	{	
		
		User temp = StateService.getStateInstance().user;
		
		setLayout(new BorderLayout());
		nameTab = new JTextArea(2, 2);
		nameTab.insert("Welcome " + temp.getFirstName()+ " " + temp.getLastName()+ "!" , 0);
		add(nameTab, BorderLayout.EAST);
		
        try {
			image = ImageIO.read(new File("images/manager.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

