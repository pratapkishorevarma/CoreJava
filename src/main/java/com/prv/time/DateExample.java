package com.prv.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * The biggest difference between java.sql.Date and java.sql.Timestamp is that the java.sql.Date only keeps the date, 
 * not the time, of the date it represents. So, for instance, if you create a java.sql.Date using the date and time 2009-12-24 23:20,
 *  then the time (23:20) would be cut off. If you use a java.sql.Timestamp then the time is kept.
 */
public class DateExample {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();

		System.out.println(date.getTime());
		System.out.println(System.currentTimeMillis());

		Date date1 = new Date();
		Date date2 = new Date();

		System.out.println(date1.compareTo(date2));
		
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

		System.out.println(timestamp);
		timestamp.setNanos(123456);
		int nanos = timestamp.getNanos(); // nanos = 123456

		java.sql.Date sqlDate = new java.sql.Date(time);
		// use sql date and timestamp with JDBC APIs
		// SQLDate doesn't store timestamp like util.Date
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = format.format( new Date());
		Date date3 = format.parse ( "2009-12-31" );    
		
		SimpleDateFormat ldapTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		String ldaptime = ldapTimeFormat.format( new Date());
		System.out.println(ldaptime);
		
	}

}
