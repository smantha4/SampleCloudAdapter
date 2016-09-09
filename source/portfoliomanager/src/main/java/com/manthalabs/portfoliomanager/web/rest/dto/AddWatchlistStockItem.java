package com.manthalabs.portfoliomanager.web.rest.dto;

import java.beans.Transient;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class AddWatchlistStockItem {

	private String symbol;
	private String qty = "0";
	private String priceBought;

	private String watchDate;

	private String dateBought;

	public String getDateBought() {
		return dateBought;
	}

	@Transient
	public DateTime getDateBoughtFormatted() {
		DateTimeFormatter dtfIn = DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss'.000Z'");
		// 2016-07-03T05:00:00.000Z
		// yyyy-MM-dd'T'hh:mm:ss'.000Z'
		DateTime dt = dtfIn.parseDateTime(dateBought);
		return dt;
	}

	public void setDateBought(String dateBought) {
		this.dateBought = dateBought;
	}

	public String getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(String watchDate) {
		this.watchDate = watchDate;
	}

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
