package com.manthalabs.portfoliomanager.analytics;

import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;

public interface WatchlistItemAnalysisRule {

	public void run(WatchlistStockItemAnalysis analysisResults, Watchlist w, WatchlistItem wi);

}
