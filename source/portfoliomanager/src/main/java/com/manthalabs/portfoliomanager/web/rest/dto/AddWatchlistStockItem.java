package com.manthalabs.portfoliomanager.web.rest.dto;

public class AddWatchlistStockItem {

	private String symbol;
	private String qty = "0";
	private String priceBought;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getPriceBought() {
		return priceBought;
	}

	public void setPriceBought(String priceBought) {
		this.priceBought = priceBought;
	}

}
