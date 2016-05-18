package com.manthalabs.portfoliomanager.domain;

public class Quote {
	private String name;
	private String ticker;
	private String currentPrice;
	private String change;
	private String daysLow;
	private String daysHigh;
	private String yearLow;
	private String yearHigh;
	private String marketCapitalization;
	private String dividendYield;
	private String peRatio;
	
	public String getTicker() {
		return ticker;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getDaysLow() {
		return daysLow;
	}
	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}
	public String getDaysHigh() {
		return daysHigh;
	}
	public void setDaysHigh(String daysHigh) {
		this.daysHigh = daysHigh;
	}
	public String getYearLow() {
		return yearLow;
	}
	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}
	public String getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}
	public String getMarketCapitalization() {
		return marketCapitalization;
	}
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}
	public String getDividendYield() {
		return dividendYield;
	}
	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}
	public String getPeRatio() {
		return peRatio;
	}
	public void setPeRatio(String peRatio) {
		this.peRatio = peRatio;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	

}
