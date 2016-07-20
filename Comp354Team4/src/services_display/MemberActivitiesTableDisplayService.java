package services_display;

public class MemberActivitiesTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
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

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
