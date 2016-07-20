package services_display;

public class ManagedMembersTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
	{
		String[] columns = 
			{
				"ProjectMember Id", 
				"First Name", 
				"Last Name"
				};
		
		return columns;	
	}

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
