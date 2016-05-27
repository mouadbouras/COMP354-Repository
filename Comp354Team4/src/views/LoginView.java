package views;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginView extends JPanel {
	
	public JTextField txtUserName;
	public JPasswordField txtPassword;
	public JLabel errorLabel , logo ,userLabel, passLabel ;
	public JButton loginButton;
	public JPanel panel;


	public void initialize() {
		
	    try {                
	    	BufferedImage img = ImageIO.read(new File("./logo.jpg"));
	    	logo = new JLabel(new ImageIcon(img));	
	    	logo.setBounds(136, 64, 300, 180);
	    } 
	    catch (IOException ex) {
	    	System.out.println(ex.getMessage());
	    }

		//panel = new JPanel();
		setToolTipText("");
		setBackground(Color.WHITE);
		
		
		userLabel = DefaultComponentFactory.getInstance().createLabel("Username :");
		userLabel.setBounds(174, 293, 80, 16);
		
		passLabel = DefaultComponentFactory.getInstance().createLabel("Password :");
		passLabel.setBounds(174, 339, 80, 16);
		
		errorLabel = DefaultComponentFactory.getInstance().createLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(174, 372, 242, 16);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(266, 285, 150, 34);
		txtUserName.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtUserName.setToolTipText("Login");
		txtUserName.setText("Enter Username");
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(266, 331, 150, 34);
		txtPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtPassword.setText("Enter Password");
		txtPassword.setToolTipText("Password");
		txtPassword.setEchoChar((char) 0);

		
		loginButton = new JButton("LOG IN");
		loginButton.setBounds(190, 400, 150, 34);
		loginButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		

	}
	
	public void addToPanel() {
		add(userLabel);
		add(errorLabel);
		add(passLabel);

		add(txtUserName);		
		add(txtPassword);

		add(logo); 
		setLayout(null);
		add(loginButton);
		
	}


}
