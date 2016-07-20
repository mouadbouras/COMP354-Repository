package services_display;

public class PropertyTableDisplayService implements ITableDisplayService {

	@Override
	public String[] GetDataTableColumns() 
	{
		String[] columns = 
			{
				"Id",
				"ActivityId",
				"Property Name",
				"Property Description",
				"Property Text"
				};
		return columns;
	}

	@Override
	public String[][] GetDataTableValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
