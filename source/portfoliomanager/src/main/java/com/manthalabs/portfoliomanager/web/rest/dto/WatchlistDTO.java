package com.manthalabs.portfoliomanager.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class WatchlistDTO {
	
	private List<WatchListStockItemDTO> watchLists = new ArrayList<>();
	
	public List<WatchListStockItemDTO> getWatchLists() {
		return watchLists;
	}
	
	public void setWatchLists(List<WatchListStockItemDTO> watchLists) {
		this.watchLists = watchLists;
	}

}
