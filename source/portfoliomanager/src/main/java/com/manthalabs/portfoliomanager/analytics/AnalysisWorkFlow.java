package com.manthalabs.portfoliomanager.analytics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.repository.WatchlistItemAnalysisRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistItemRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistRepository;

@Component
public class AnalysisWorkFlow {
	@Autowired
	private WatchlistRepository watchlistRepository;

	@Autowired
	private WatchlistItemRepository watchlistItemRepository;

	@Autowired
	private WatchlistItemAnalysisRepository watchlistItemAnalysisRepository;

	@Autowired
	List<WatchlistItemAnalysisRule> watchlistItemAnalysisRules;

	public void runAnalysis(Watchlist w) {

		// process each stock
		List<WatchlistItem> watchlistItems = watchlistItemRepository.findAll();

		for (WatchlistItem wi : watchlistItems) {

			// Clear all the analysis
			try {
				watchlistItemAnalysisRepository.delete(wi.getStock());
			} catch (Exception e) {

			}

			// Re run all the rules
			WatchlistStockItemAnalysis analysisResults = new WatchlistStockItemAnalysis();
			analysisResults.setSymbol(wi.getStock());

			for (WatchlistItemAnalysisRule wia : watchlistItemAnalysisRules) {
				wia.run(analysisResults, w, wi);
			}

			watchlistItemAnalysisRepository.save(analysisResults);
		}

	}

}
