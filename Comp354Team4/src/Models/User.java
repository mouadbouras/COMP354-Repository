package Models;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private int role; //let Project Manager = 0; Project Member = 1;
	
	public User(String first, String last, int role)
	{
		this.firstName = first;
		this.lastName = last;
		this.role = role;		
	}
	
	public static String tableName = "User";
	
	public static String createTable = 	"CREATE TABLE " + tableName
			+ " ("
			+ "id INTEGER PRIMARY KEY,"
			+ "firstName CHAR(50),"
			+ "lastName CHAR(50),"			
			+ "role INTEGER"		
			+ ");";
	
	public static String[] insert(User[] users)
	{
		String[] insertUsers = new String[users.length];
		
		for (int i = 0; i < users.length; ++i)
		{
			User temp = users[i];
			
			String sql = "INSERT INTO " + tableName + " (firstName,lastName,role) VALUES "
					+ "("
					+ "'" + temp.firstName + "',"
					+ "'" + temp.lastName + "',"
					+ "'" + temp.role + "'"
					+ ")";		
			
			insertUsers[i] = sql;
		}		
		
		return insertUsers;
	}
}
