package com.manthalabs.portfoliomanager.util;

import java.text.DecimalFormat;

public class MiscUtil {

	public static double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}

}
