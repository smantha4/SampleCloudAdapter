package com.manthalabs.portfoliomanager.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class WatchlistDTO {

	private String watchlistName;

	private double totalGainLoss;

	private double totalAccountValue;
	private boolean isIRA;

	private List<WatchListStockItemDTO> watchLists = new ArrayList<>();

	public List<WatchListStockItemDTO> getWatchLists() {
		return watchLists;
	}

	public void setWatchLists(List<WatchListStockItemDTO> watchLists) {
		this.watchLists = watchLists;
	}

	public String getWatchlistName() {
		return watchlistName;
	}

	public void setWatchlistName(String watchlistName) {
		this.watchlistName = watchlistName;
	}

	public void setTotalGainLoss(double totalGainLoss) {
		this.totalGainLoss = totalGainLoss;
	}

	public double getTotalGainLoss() {
		return totalGainLoss;
	}

	public double getTotalAccountValue() {
		return totalAccountValue;
	}

	public void setTotalAccountValue(double totalAccountValue) {
		this.totalAccountValue = totalAccountValue;
	}

	public void setIRA(boolean isIRA) {
		this.isIRA = isIRA;
	}

	public boolean isIRA() {
		return isIRA;
	}

}
