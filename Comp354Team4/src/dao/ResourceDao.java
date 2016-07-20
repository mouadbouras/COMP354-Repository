package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.*;
import controllers.ConverterService;

public class ResourceDao {

	private static String SelectResourcesGivenActivityId = "SELECT R.*, U.firstname as firstname, U.lastname as lastname FROM Resource AS R INNER JOIN User as U on U.id = R.userId WHERE R.activityId = @activityId AND R.isRemoved = 0";
	private static String InsertResource = "INSERT INTO Resource (userId, activityId) VALUES (@memberId, @activityId)";
	private static String UpdateResource = "";
	private static String RemoveResource = "UPDATE Resource SET isRemoved = '1' WHERE id = @id;";
	
	private static ResourceDao dao = null;
	
	private ResourceDao(){}
	
	public static ResourceDao getInstance()
	{
		if (dao == null)
			dao = new ResourceDao();
		
		return dao;
	}
	
	public String[] GetResourceColumns() 
	{
		String[] columns =  {"Id", "Member Id", "Member First Name", "Member Last Name" ,"Activity Id"};
		return columns;
	}

	public String[] returnDataRow(Resource resource) 
	{
		String[] temp = {Integer.toString(resource.id), Integer.toString(resource.memberId), resource.firstname, resource.lastname, Integer.toString(resource.activityId)};
		return temp;
	}

	public List<Resource> GetResourcesByActivityId(int activityId) 
	{
		List<Resource> resources = new ArrayList<Resource>();
		
		String sql = ResourceDao.SelectResourcesGivenActivityId.replace("@activityId", Integer.toString(activityId));
		System.out.println(sql);
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		//do a try catch		
		try 
		{
			while(rs.next())
			{
				Resource temp = null;
				temp = mapResultSetToResource(rs);
				resources.add(temp);
			}
		}
		catch (Exception e)
		{
			System.out.println("GetResourcesByActivityId FAILED");
		}
		
		SqliteSetup.GetInstance().CloseQuery();

		return resources;
	}	

	public boolean InsertResources(Resource resource)
	{
		String sql = InsertResource.
				replace("@activityId", Integer.toString(resource.activityId)).
				replace("@memberId", Integer.toString(resource.memberId));
		
		System.out.println(sql);
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	public boolean DeleteResource(int id) 
	{
		String sql = ResourceDao.RemoveResource.replace("@id", Integer.toString(id));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	public boolean UpdateResource(Resource resource) 
	{
		return true;
	}

	private Resource mapResultSetToResource(ResultSet rs) throws SQLException 
	{
		Resource temp = new Resource(); 
		temp.id = rs.getInt("id");
		temp.memberId = rs.getInt("userId");
		temp.activityId = rs.getInt("activityId");
		temp.firstname = rs.getString("firstname");
		temp.lastname = rs.getString("lastname");
		return temp;
	}
}
