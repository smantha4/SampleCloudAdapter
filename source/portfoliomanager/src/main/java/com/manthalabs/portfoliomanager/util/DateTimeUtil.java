package com.manthalabs.portfoliomanager.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeUtil {

	public static String getCurrentDateTime() {
		return DateTime.now().toString(ISODateTimeFormat.basicDate());
	}

	public static String normalizeDate(DateTime d) {
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd MMM yyyy");

		return d.toString(dtfOut);
	}

	public static DateTime parseDate(String d) {
		try {
			if (d != null) {
				DateTimeFormatter dtfIn = ISODateTimeFormat.basicDate();
				DateTime dt = dtfIn.parseDateTime(d);
				return dt;
			}
		} catch (Exception e) {

		}
		return DateTime.now();
	}

	public static String getFormattedDate(String date) {
		try {
			DateTimeFormatter dtfIn = ISODateTimeFormat.basicDate();
			DateTime dt = dtfIn.parseDateTime(date);

			DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd MMM yyyy");

			return dt.toString(dtfOut);
		} catch (Exception e) {
			return null;
		}
	}

}
