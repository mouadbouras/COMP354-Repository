package ProjectTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import models.Project;
import models.ProjectDao;
import models.SqliteSetup;

public class TestUpdateProject {

@Test
public void UpdateProject() throws ParseException, SQLException, ClassNotFoundException{
		
	 	Connection c = null;
		Statement stmt = null;
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 
		Project project = new Project();
		System.out.println("Testing Creation of a Project with all its information");
		project.setProjectName("TestingUpdate");
		project.setStartDate(df.parse("2017-02-29"));
		project.setEndDate(df.parse("2018-02-29"));					
		project.setManagerId(1);
		
		ProjectDao projectDao = new ProjectDao();
		projectDao.InsertProject(project);*/
		
		//testing update of project above 
		System.out.println("Testing Update of a Project");
		Class.forName(SqliteSetup.sqliteClass);
	    c = DriverManager.getConnection(SqliteSetup.connection);
	    stmt = c.createStatement();
		String sql = ProjectDao.UpdateProjectGivenProjectId.replace("@projectName", "UPDATE").
	    		  replace("@startDate", ("2018-02-29")). 
	    		  replace("@endDate", "2020-02-29").  
	    		  replace("@id", Integer.toString(60));
				  stmt.executeUpdate(sql);
				  stmt.close();
				  c.close();
				  assertNotNull(sql);
				 

	} 

}
