package com.manthalabs.portfoliomanager.analytics;

import java.util.List;
import java.util.ServiceLoader;

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

	public void runAnalysis() {

		List<Watchlist> ws = watchlistRepository.findAll();

		for (Watchlist w : ws) {

			WatchlistStockItemAnalysis analysisResults = new WatchlistStockItemAnalysis();

			ServiceLoader<WatchlistAnalysisRule> rules = ServiceLoader.load(WatchlistAnalysisRule.class);

			for (WatchlistAnalysisRule wa : rules) {
				wa.run(analysisResults, w);
			}

		}

		// process each stock
		List<WatchlistItem> watchlistItems = watchlistItemRepository.findAll();

		for (WatchlistItem wi : watchlistItems) {
			ServiceLoader<WatchlistItemAnalysisRule> watchlistItemRules = ServiceLoader
					.load(WatchlistItemAnalysisRule.class);
			WatchlistStockItemAnalysis analysisResults = new WatchlistStockItemAnalysis();
			analysisResults.setSymbol(wi.getStock());

			watchlistItemRepository.delete(wi.getStock());

			for (WatchlistItemAnalysisRule wia : watchlistItemRules) {
				wia.run(analysisResults, wi);
			}

			watchlistItemAnalysisRepository.save(analysisResults);
		}

	}

}
