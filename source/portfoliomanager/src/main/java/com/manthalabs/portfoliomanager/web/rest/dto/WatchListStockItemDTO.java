package com.manthalabs.portfoliomanager.web.rest.dto;

import com.manthalabs.portfoliomanager.domain.Quote;

public class WatchListStockItemDTO {

	private String id;

	private Quote quote;

	private double qty = 0;

	private double gain = 0;

	private double totalValue = 0;

	private double accountPercentage = 0;

	private String recPecAggr;
	private String recPecModerate;
	private String recPecConservative;

	public double getAccountPercentage() {
		return accountPercentage;
	}

	public void setAccountPercentage(double accountPercentage) {
		this.accountPercentage = accountPercentage;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

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

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public String getRecPecAggr() {
		return recPecAggr;
	}

	public void setRecPecAggr(String recPecAggr) {
		this.recPecAggr = recPecAggr;
	}

	public String getRecPecModerate() {
		return recPecModerate;
	}

	public void setRecPecModerate(String recPecModerate) {
		this.recPecModerate = recPecModerate;
	}

	public String getRecPecConservative() {
		return recPecConservative;
	}

	public void setRecPecConservative(String recPecConservative) {
		this.recPecConservative = recPecConservative;
	}

}
