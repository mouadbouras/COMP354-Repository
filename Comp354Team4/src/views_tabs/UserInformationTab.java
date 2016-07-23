package views_tabs;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import models.User;
import services.StateService;

public class UserInformationTab extends JPanel{

	private JTable Usertable;
	private JScrollPane databasetable;
	private JTable table;
	private JTextArea nameTab;
	private JLabel NameTab;

	private BufferedImage image;

	
	public UserInformationTab() 
	{	
		
		User temp = StateService.getStateInstance().user;
		
		setLayout(new BorderLayout());
		//nameTab = new JTextArea(2, 2);
		NameTab = DefaultComponentFactory.getInstance().createLabel("Welcome " + temp.getFirstName()+ " " + temp.getLastName()+ "!");
		NameTab.setFont (NameTab.getFont ().deriveFont (14.0f));

		setBorder(new EmptyBorder(20, 15, 20, 15));

		add(NameTab, BorderLayout.CENTER);
		
        try {
			image = ImageIO.read(new File("images/manager.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

