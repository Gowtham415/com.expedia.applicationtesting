package com.expedia.utilities;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatesUtility {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");;
	static Calendar date;

	public static String getCurrentDate() {
		date = Calendar.getInstance();
		return dateFormat.format(date.getTime());
	}
	
	private static Calendar getFutureDate() {
		date = Calendar.getInstance();
		date.setTime(new Date()); // Now use today date.
		date.add(Calendar.DATE, 15); // Adds 15 days by default
		return date;
	}
	
	private static Calendar getFutureDate(int daysAfterCurrentDate) {
		date = Calendar.getInstance();
		date.setTime(new Date()); // Now use today date.
		date.add(Calendar.DATE, daysAfterCurrentDate); // Adds 15 days
		return date;
	}
	
	public static String getMonthStringForIndex(int m) {
	    String month = "invalid";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (m >= 0 && m <= 11 ) {
	        month = months[m];
	    }
	    return month;
	}
	
// Application Specific
	
	// dd/MM/yyyy format
	public static String getFutureDateAsString(int daysAfterCurrentDate) {
		String baseDate = dateFormat.format(getFutureDate(daysAfterCurrentDate).getTime());
		String[] dateArry = baseDate.split("-");	
		return dateArry[0]+"/"+dateArry[1]+"/"+dateArry[2];	
	}
	
	public static String getFutureDateAsString() {
		String baseDate = dateFormat.format(getFutureDate().getTime());
		String[] dateArry = baseDate.split("-");	
		return dateArry[0]+"/"+dateArry[1]+"/"+dateArry[2];	
	}
	
	public static void main(String[] args) {
		System.out.println(getFutureDateAsString(5));
		System.out.println(getFutureDateAsString());
	}

}
