package views;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ProjectsView extends JPanel {
	public JComponent Tab1,Tab2;
	public JTabbedPane tabbedPane;
	/**
	 * Create the panel.
	 */
	public ProjectsView() {
		
		//JPanel ProjectsTab = new ProjectsTab();
		
//		JPanel panel = new LeftSideBar();
//		panel.setBackground(Color.WHITE);
//		setLeftComponent(panel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE); 
		add(tabbedPane);
		//setRightComponent(tabbedPane);
		
		
	    ImageIcon icon1 = null;	
	    State.getStateInstance().setProjectTab(new ProjectsTab());
        Tab1 = State.getStateInstance().getProjectTab();
        tabbedPane.addTab("Projects", icon1, Tab1, null);
		
	    ImageIcon icon2 = null;	
	    State.getStateInstance().setActivityTab(new ActivitiesTab());
        Tab2 = State.getStateInstance().getActivityTab();
        tabbedPane.addTab("Activies", icon2, Tab2, null);

        
//		add(CreateProjectPanel);
//	      JComponent panel1 = new GenericDataTablePanel();
//	      tabbedPane.addTab("Projects", icon, panel1,
//	               null);
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBackground(Color.WHITE);
//		splitPane.setRightComponent(tabbedPane);
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
