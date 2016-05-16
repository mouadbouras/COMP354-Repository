package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MainView extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainView() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		add(splitPane, BorderLayout.CENTER);		
	
		JPanel panel = new LeftSideBar();
		panel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		splitPane.setRightComponent(tabbedPane);
		
		ImageIcon icon = null;	
        
        JComponent panel1 = new GenericDataTablePanel();
        tabbedPane.addTab("Projects", icon, panel1,
                 null);
	}
	
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }	

}
