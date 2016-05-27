//importing java libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.imageio.ImageIO;

//importing local libraries 
import views.*;
import models.*;
import controllers.*;


public class Main {
	
	//public static JFrame frmCompProject;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController.login(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
}

