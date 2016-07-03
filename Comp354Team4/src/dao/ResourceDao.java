package dao;

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
import controllers.ConverterService;

public class ResourceDao {

	private static String SelectResourcesGivenActivityId = "";
	private static String InsertResource = "";
	private static String UpdateResource = "";
	private static String RemoveResource = "";
	
	public String[] GetResourceColumns() 
	{
		String[] columns = null;
		return columns;
	}

	public String[] returnDataRow(Resource resource) 
	{
		String[] temp = null;
		return temp;
	}

	public List<Resource> GetResourcesActivityId(int activityId) 
	{
		List<Resource> resources = new ArrayList<Resource>();

		return resources;
	}	

	public boolean InsertResources(Resource resource)
	{
		return true;
	}

	public boolean DeleteResource(int id) 
	{
		return true;
	}

	public boolean UpdateResource(Resource resource) 
	{
		return true;
	}

	private Resource mapResultSetToResource(ResultSet rs) {
		Resource temp = null; 
		return temp;
	}
}
