package services_display;

public class ProjectTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
	{
		String[] columns = 
			{ 
				"Project Id", 
				"Project Name", 
				"Start Date", 
				"End Date", 
				"Manager Id"
				};

		return columns;
	}

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
