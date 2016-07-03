package views_tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import models.User;
import services.DataService;
import services.StateService;

public class UserInformationTab extends JPanel{

	private JTable Usertable;
	private JScrollPane databasetable;
	private JTable table;
	private JTextArea nameTab;
	private BufferedImage image;

	
	public UserInformationTab() 
	{	
		
		User temp = StateService.getStateInstance().getUser();
		
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

class IconPanel extends JPanel
{
	private BufferedImage image;
	
	public IconPanel()
	{
        try {
			image = ImageIO.read(new File("images/manager.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	protected void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    Dimension d = getSize();
	    g.drawImage(image, 0, 0, 200, 200, this);

	}
}
