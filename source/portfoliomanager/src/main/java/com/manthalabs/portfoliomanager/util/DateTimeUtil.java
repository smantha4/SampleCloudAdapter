package com.manthalabs.portfoliomanager.util;

import org.joda.time.DateTime;

public class DateTimeUtil {

	public static String getCurrentDateTime() {
		return DateTime.now().toString();
	}

}
