package views;
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
import javax.swing.UIManager.*;

public class Main 
{	
	public static void main(String[] args) 
	{		
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		        if ("Nimbus".equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} 
		catch (Exception e) {}
	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainController.login(); 
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}	
}

