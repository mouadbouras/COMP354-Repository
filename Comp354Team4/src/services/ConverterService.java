package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterService {

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static String DateToString(Date d) 
	{
		return df.format(d);
	}

	public static Date StringToDate(String s) throws ParseException 
	{
		return df.parse(s);
	}
}
