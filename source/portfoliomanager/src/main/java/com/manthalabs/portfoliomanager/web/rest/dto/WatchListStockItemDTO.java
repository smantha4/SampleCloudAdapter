package com.manthalabs.portfoliomanager.web.rest.dto;

import com.manthalabs.portfoliomanager.domain.Quote;

public class WatchListStockItemDTO {

	private String id;

	private Quote quote;

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

}
