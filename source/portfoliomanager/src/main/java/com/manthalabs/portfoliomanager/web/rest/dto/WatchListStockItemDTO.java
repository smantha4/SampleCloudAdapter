package com.manthalabs.portfoliomanager.web.rest.dto;

import com.manthalabs.portfoliomanager.domain.Quote;

public class WatchListStockItemDTO {

	private String id;

	private Quote quote;

	private String qty = "0";

	private String gain = "0";

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

}
