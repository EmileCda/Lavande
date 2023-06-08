package fr.lotus.utils;

import java.util.Date;

public interface IUtils {

	public final String DATE_FORMAT= "dd/MM/yyyy";
	// convert SQL date to java Date
	public static Date toJavaDate(java.sql.Date sqlDate) {return null;} ;

	// convert java Date to SQL date
	public static java.sql.Date toSqlDate(Date myDate) {return null;} 
	
	// convert a string into a date by using date format defined in IConstant (DATE_FORMAT= "dd/MM/yyyy";)
	public static Date string2Date(String dateString)  {return null;} ;
	
	// convert a date into string  by using date format defined in IConstant (DATE_FORMAT= "dd/MM/yyyy";)
	public static String date2String(Date date){return null;} ;

	// 	random value	
	public static int randInt(int minValue, int maxValue) { return 0; };
	public static int randFloat(int minValue, int maxValue) { return 0; };


	// display debug information with filename and line number
	public static void trace(String patern, Object... arguments) {};
	public  static void setVerboseMode(boolean verboseMode) {}; // set verbose trace or not 

}
