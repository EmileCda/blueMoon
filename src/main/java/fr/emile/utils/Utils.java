package fr.emile.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Utils {
	public static String date2String(Date date, String dateFormat) {

		String returnValue = "";
		if (date != null)
			returnValue = new SimpleDateFormat(dateFormat).format(date);

		return returnValue;
	}

	// display debug information with filename and line number

	// -----------------------------------------------------------------------
	public static String firstToUpper(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

	}

	public static List<String> JsonFile2List(String filename, String key) {

		return null;
	}

	// convert java Date to SQL date

	// -----------------------------------------------------------------------
	// fonction random qui renvoie une fonction entre minValue et maxValue
	// attention dans le cas de l'utilisation pour indexer un tableau (maxIndex =
	// length)
	public static int randInt(int minValue, int maxValue) {
		// minvalue er maxValue inclus
		return (int) (Math.random() * (maxValue + 1 - minValue) + minValue);
	}

	// create date from a string format
	public static Date string2Date(String dateString, String dateFormat) {

		Date returnDate = null;
		try {
			returnDate = new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return returnDate;

	}

	// create string from date with format

	// convert SQL date to java Date
	public static Date toJavaDate(java.sql.Date sqlDate) {
		if (sqlDate != null)
			return new Date(sqlDate.getTime());
		return null;
	}

	public static java.sql.Date toSqlDate(Date myDate) {
		if (myDate != null)
			return new java.sql.Date(myDate.getTime());
		return null;
	}

	public static Date localDate2Date(LocalDate myLocalDate) {
		if (myLocalDate != null)
			return  Date.from(myLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return null;
	}
//-------------------------------------------------------------------------------------------------
	public static LocalDate date2localDate(Date myDate) {
		if (myDate != null)
			return myDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return null;
	}
//-------------------------------------------------------------------------------------------------

	//-------------------------------------------------------------------------------------------------
		public static String stripAccent(String inputString) 
		{
			inputString = Normalizer.normalize(inputString, Normalizer.Form.NFD);
			inputString = inputString.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		    return inputString;
		}
		

	public static void trace(String msg) {
		Throwable sys = new Throwable();

		System.out.printf("=>[%s]:%d : %s\n", sys.getStackTrace()[1].getFileName(),
				sys.getStackTrace()[1].getLineNumber(), msg);

	}
}