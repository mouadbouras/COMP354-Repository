package services_display;

public class ResourceTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
	{
		String[] columns =  
			{
					"Id", 
					"Member Id", 
					"Member First Name", 
					"Member Last Name",
					"Activity Id"
					};
		return columns;
	}

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
