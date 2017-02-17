package com.prv.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 * Java's java.util.Calendar class is used to do date and time arithmetic. Whenever you have something slightly 
 * more advanced than just representing a date and time, this is the class to use.
 */
public class CalanderExample {

	public static void main(String[] args) {
		TimeZone timeZone = TimeZone.getDefault();

		TimeZone timeZone2 = TimeZone.getTimeZone("Europe/Copenhagen");
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(timeZone);

		int year       = calendar.get(Calendar.YEAR);
		int month      = calendar.get(Calendar.MONTH); 
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute     = calendar.get(Calendar.MINUTE);
		int second     = calendar.get(Calendar.SECOND);
		int millisecond= calendar.get(Calendar.MILLISECOND);
		
		System.out.println(year + month + dayOfMonth);
		
		SimpleDateFormat ldapDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -30);
		String dateString = ldapDateFormat.format(cal.getTime());
	}

}
