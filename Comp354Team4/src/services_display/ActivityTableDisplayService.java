package services_display;

public class ActivityTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
	{
		String[] columns = 
			{
				"Activity Id", 
				"Activity Name", 
				"Description", 
				"Start Date", 
				"End Date", 
				"Duration", 
				"Project Id",
				"Depends On (Id)"
				};
		
		return columns;	
	}

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
