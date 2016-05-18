package com.manthalabs.portfoliomanager.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY) 
public class YFResponse {

	Query query;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY) 
	static class Query {
		String count;
		String created;
		String lang;
		Results results;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY) 
	static class Results {
		Quote quote;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY) 
	static class Quote {
		String Ask;
		String PERatio;
		String BookValue;
		String EarningsShare;
		String DaysLow;
		String DaysHigh;
		String YearLow;
		String YearHigh;
		String Name;
		String ExDividendDate;
		String OneyrTargetPrice;
		String  DividendYield;
		String Change;
		String MarketCapitalization;
	}
}
