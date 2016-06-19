package com.manthalabs.portfoliomanager.analytics;

import com.manthalabs.portfoliomanager.domain.Watchlist;

public interface WatchlistAnalysisRule {

	public void run(WatchlistStockItemAnalysis analysisResults, Watchlist w);

}
