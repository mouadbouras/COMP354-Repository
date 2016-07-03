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

public class PropertyDao {
	
	private static String SelectPropertyGivenActivityId = "";
	private static String InsertProperty = "";
	private static String UpdateProperty = "";
	private static String RemoveProperty = "";
	
	public String[] GetPropertyColumns() 
	{
		String[] columns = null;
		return columns;
	}

	public String[] returnDataRow(Property property) 
	{
		String[] temp = null;
		return temp;
	}

	public List<Property> GetPropertyGivenActivityId(int activityId) 
	{
		List<Property> property = new ArrayList<Property>();

		return property;
	}	

	public boolean InsertProperty(Property property)
	{
		return true;
	}

	public boolean DeleteProperty(int id) 
	{
		return true;
	}

	public boolean UpdateProperty(Property property) 
	{
		return true;
	}

	private Property mapResultSetToProperty(ResultSet rs) {
		Property temp = null; 
		return temp;
	}
}
