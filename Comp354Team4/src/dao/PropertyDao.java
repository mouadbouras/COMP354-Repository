package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.*;


public class PropertyDao {
	
	private static String SelectPropertyGivenActivityId = "SELECT * From Property WHERE activityId = @activityId AND isRemoved = '0';";
	private static String SelectPropertyGivenPropertyId = "SELECT * From Property WHERE id = @id AND isRemoved = '0';";
	private static String InsertProperty = "INSERT INTO Property (activityId, propertyText, propertyName, propertyDescription) VALUES ('@activityId', '@propertyText', '@propertyName', '@propertyDescription');";
	private static String UpdateProperty = "UPDATE Property SET propertyName = '@propertyName', propertyDescription = '@propertyDescription', propertyText = '@propertyText' WHERE id = @id";
	private static String RemoveProperty = "UPDATE Property SET isRemoved = '1' WHERE id = @id";
	
	private static PropertyDao dao = null;
	
	private PropertyDao(){}
	
	public static PropertyDao getInstance()
	{
		if (dao == null)
			dao = new PropertyDao();
		
		return dao;
	}
	
	public String[] GetPropertyColumns() 
	{
		String[] columns = {"Id","ActivityId","Property Name","Property Description","Property Text"};
		return columns;
	}

	public String[] returnDataRow(Property property) 
	{
		String[] temp = {Integer.toString(property.id), Integer.toString(property.activityId), property.propertyName, property.propertyDescription, property.propertyText};
		return temp;
	}

	public List<Property> GetPropertyGivenActivityId(int activityId) 
	{
		List<Property> properties = new ArrayList<Property>();
		
		String sql = PropertyDao.SelectPropertyGivenActivityId.replace("@activityId", Integer.toString(activityId));
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		try 
		{
			while(rs.next())
			{
				Property temp = null;
				temp = mapResultSetToProperty(rs);
				properties.add(temp);
			}
		}
		catch (Exception e)
		{
			
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return properties;
	}	

	public boolean InsertProperty(Property property)
	{		
		String sql = PropertyDao.InsertProperty.
				replace("@activityId", Integer.toString(property.activityId)).
				replace("@propertyName", property.propertyName).
				replace("@propertyDescription", property.propertyDescription).
				replace("@propertyText", property.propertyText);	
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;		
	}

	public boolean DeleteProperty(int id) 
	{
		String sql = PropertyDao.RemoveProperty.replace("@id", Integer.toString(id));
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}

	public boolean UpdateProperty(Property property) 
	{
		String sql = PropertyDao.UpdateProperty.
				replace("@id", Integer.toString(property.id)).
				replace("@propertyName", property.propertyName).
				replace("@propertyDescription", property.propertyDescription).
				replace("@propertyText", property.propertyText);		
		
		SqliteSetup.GetInstance().ExecuteUpdate(sql);
		
		return true;
	}
	

	public Property GetPropertyGivenPropertyId(int id)
	{
		String sql = PropertyDao.SelectPropertyGivenPropertyId.replace("@id", Integer.toString(id));
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		Property temp = null;
		try 
		{
			if (rs.next())
			{
				temp = mapResultSetToProperty(rs);
			}
		}
		catch (Exception e)
		{
			
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		return temp;
	}

	private Property mapResultSetToProperty(ResultSet rs) 
	{
		Property temp = new Property(); 
		
		try 
		{
			temp.id = rs.getInt("id");
			temp.activityId = rs.getInt("activityId");
			temp.propertyName = rs.getString("propertyName");
			temp.propertyDescription = rs.getString("propertyDescription");
			temp.propertyText = rs.getString("propertyText");
		}
		catch(Exception e)
		{
			
		}
		return temp;
	}
}
