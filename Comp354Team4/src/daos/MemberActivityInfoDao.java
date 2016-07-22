package daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.MemberActivityInfo;


public class MemberActivityInfoDao 
{
	private static String SelectMemberActivityGivenUserId = "SELECT U.id, U.firstName as firstName, U.lastName as lastName,  P.id as projectId, P.projectName, A.id as activityId, A.activityName FROM User AS U INNER JOIN Resource AS R ON R.userId = U.id INNER JOIN Activity AS A ON A.id = R.activityId INNER JOIN Project AS P ON P.id = A.projectId WHERE U.id = @userId AND U.role = '1' AND A.isRemoved = '0' AND R.isRemoved = '0' AND P.isRemoved = '0';";
	
	private static MemberActivityInfoDao dao = null;
	
	private MemberActivityInfoDao(){}
	
	public static MemberActivityInfoDao getInstance()
	{
		if (dao == null)
			dao = new MemberActivityInfoDao();
		
		return dao;
	}
	
	public List<MemberActivityInfo> GetMemberActivityGivenUserId(int userId)
	{
		List<MemberActivityInfo> memberActivities = new ArrayList<MemberActivityInfo>();
		
		String sql = MemberActivityInfoDao.SelectMemberActivityGivenUserId.replace("@userId", Integer.toString(userId)); //prepare sql
		
		ResultSet rs = SqliteSetup.GetInstance().ExecuteQuery(sql);
		
		MemberActivityInfo temp = null;		
		
		try 		
		{
			while ( rs.next() ) 
			{
				temp = mapResultSetToUser(rs);
				memberActivities.add(temp);
			}
		}
		catch (Exception e)
		{
			System.out.println("Result set failed");
		}
		
		SqliteSetup.GetInstance().CloseQuery();
		
		return memberActivities;		
	}		
	
	private MemberActivityInfo mapResultSetToUser(ResultSet rs)
	{
		MemberActivityInfo temp = new MemberActivityInfo();
		
		try 
		{
			temp.userId = rs.getInt("id");
			temp.userName =  rs.getString("firstName") + " " +  rs.getString("lastName");
			
			temp.projectId = rs.getInt("projectId");
			temp.projectName = rs.getString("projectName");
			
			temp.activityId = rs.getInt("activityId");
			temp.activityName =  rs.getString("activityName");
		}
		catch (Exception e)
		{			
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
		}
		return temp;
	}
	
	public String[] returnDataRow(MemberActivityInfo memberActivity)
	{		
		String[] temp = 
			{
				Integer.toString(memberActivity.userId), 
				memberActivity.userName, 
				Integer.toString(memberActivity.projectId), 
				memberActivity.projectName, 
				Integer.toString(memberActivity.activityId), 
				memberActivity.activityName
				};
		return temp;		
	}
	
	public String[] GetMemberActivityInfoColumns()
	{
		String[] columns = {
				"User Id",
				"User Name",
				"Project Id",
				"Project Name",
				"Activity Id", 
				"Activity Name"
				};
		
		return columns;		
	}
}
