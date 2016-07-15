package views_panels;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import models.Activity;
import models.Project;
import services.DataService;
import services.GANTTService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;

public class GanttPanel extends JPanel {
	
	public GanttPanel(String title,Project temp) {
		
		System.out.println(temp.getProjectName());
		
		setLayout(new BorderLayout(0, 0));
		
        final IntervalCategoryDataset dataset = createDataset(temp);
        final JFreeChart chart = createChart(dataset,temp);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        //add(chartPanel, BorderLayout.CENTER);
        
        add(chartPanel,BorderLayout.CENTER);
        validate();        
    }
	
	public IntervalCategoryDataset createDataset(Project temp) {
		
		TaskSeriesCollection collection = new TaskSeriesCollection();
		
		GANTTService ganttGraphService = new GANTTService();
		
		List<Activity> activities = ganttGraphService.CalculateGanttChartActivityTimes(temp);
		
		TaskSeries ganttSeries = new TaskSeries(temp.getProjectName());
		
		for(Activity a : activities)
		{
			ganttSeries.add(new Task(a.getActivityName() + " (" + Integer.toString(a.getId()) + ")", new SimpleTimePeriod(a.getStartDate(),a.getEndDate())));
		}
        
		collection.add(ganttSeries);

        return collection;
	}	
	
	private JFreeChart createChart(final IntervalCategoryDataset dataset,Project temp) {
        final JFreeChart chart = ChartFactory.createGanttChart(
        	temp.getProjectName(),  // chart title
            "Activities",              // domain axis label
            "Start To End Date Range",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
        return chart;    
    }
}
