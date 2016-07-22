package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.*;
import services.ConverterService;

public class ProjectDao {

	private static String SelectProjectsGivenManagerId = "SELECT * FROM Project WHERE managerId = @userId AND isRemoved = 0";
	private static String getProjectByProjectId = "SELECT * FROM Project WHERE id = @id AND isRemoved = 0";
	private static String InsertProjects = "INSERT INTO Project(projectName, startDate, endDate, managerId) VALUES ('@projectName', '@startDate', '@endDate', '@managerId');";
	public static String CreateTable = "CREATE TABLE Project (id INTEGER PRIMARY KEY, projectName varchar(50),	"
			+ "startDate DateTime, endDate DateTime, managerId INTEGER, FOREIGN KEY(managerId) REFERENCES User(id));";

	public static String DeleteProjectGivenProjectId = "UPDATE Project SET isRemoved = '1' WHERE id = @id";
	public static String UpdateProjectGivenProjectId = "UPDATE Project SET projectName = '@projectName', startDate = '@startDate', endDate = '@endDate' WHERE id = @id ";

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private static ProjectDao dao = null;
	
	private ProjectDao(){}
	
	public static ProjectDao getInstance()
	{
		if (dao == null)
			dao = new ProjectDao();
		
		return dao;
	}
	
	public String[] GetProjectColumns() 
	{
		String[] columns = { "Project Id", "Project Name", "Start Date", "End Date", "Manager Id"};

		return columns;
	}

	public String[] returnDataRow(Project project) 
	{
		String[] temp = new String[] { 
				Integer.toString(project.getId()), 
				project.getProjectName(),
				ConverterService.DateToString(project.getStartDate()),
				ConverterService.DateToString(project.getEndDate()), 
				Integer.toString(project.getManagerId())
				};
		return temp;
	}

	// get a list of project ids managed by user id
	// returns null if there's no projects found
	public List<Project> GetProjectsGivenManagerId(int userId) 
	{		
		List<Project> projects = new ArrayList<Project>();
		
		String sql = ProjectDao.SelectProjectsGivenManagerId.replace("@userId", Integer.toString(userId)); // prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try 
		{
			while (rs.next()) 
			{
				Project temp = null;
				temp = mapResultSetToProject(rs);
				projects.add(temp);
			}
		}
		catch (Exception e)
		{
			System.out.println("GetProjectsGivenManagerId FAILED");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return projects;
	}

	public Project getProjectByProjectId(int Id)
	{
		String sql = ProjectDao.getProjectByProjectId.replace("@id", Integer.toString(Id)); // prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		Project temp = null;
		try 
		{
			if (rs.next()) 
			{
				temp = mapResultSetToProject(rs);
			}
		}
		catch (Exception e)
		{
			System.out.println("getProjectByProjectId FAILED");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return temp;
	}
	// insert project into database
	public boolean InsertProject(Project project) 	
	{
		String sql = ProjectDao.InsertProjects.
				replace("@projectName", project.getProjectName()).
				replace("@startDate", ConverterService.DateToString(project.getStartDate())). // format date to string
				replace("@endDate", ConverterService.DateToString(project.getEndDate())). // format date to string
				replace("@managerId", Integer.toString(project.getManagerId()));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	public boolean DeleteProject(int id) 
	{
		
		String sql = ProjectDao.DeleteProjectGivenProjectId.replace("@id", Integer.toString(id));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	public boolean UpdateProject(Project project) 
	{
		String sql = ProjectDao.UpdateProjectGivenProjectId.
				replace("@projectName", project.getProjectName()).
				replace("@startDate", ConverterService.DateToString(project.getStartDate())).
				replace("@endDate", ConverterService.DateToString(project.getEndDate())).
				replace("@id", Integer.toString(project.getId()));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	// map resultset from sqlite to User entity
	private Project mapResultSetToProject(ResultSet rs) 
	{
		Project temp = null; 

		try 
		{
			temp = new Project(rs.getInt("id"), rs.getString("projectName"), rs.getString("startDate"), rs.getString("endDate"), rs.getInt("managerId"));
		} 
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return temp;
	}
}
