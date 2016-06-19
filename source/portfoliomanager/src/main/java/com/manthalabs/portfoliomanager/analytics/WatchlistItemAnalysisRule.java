package com.manthalabs.portfoliomanager.analytics;

import com.manthalabs.portfoliomanager.domain.WatchlistItem;

public interface WatchlistItemAnalysisRule {

	public void run(WatchlistStockItemAnalysis analysisResults, WatchlistItem w);

}
