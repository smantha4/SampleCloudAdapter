package com.manthalabs.portfoliomanager.analytics.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanager.analytics.Analysis;
import com.manthalabs.portfoliomanager.analytics.WatchlistStockItemAnalysis;
import com.manthalabs.portfoliomanager.analytics.WatchlistItemAnalysisRule;
import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.service.YahooFinanceQuoteService;

@Component
public class WatchListItemPriceChange implements WatchlistItemAnalysisRule {

	@Autowired
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	@Override
	public void run(WatchlistStockItemAnalysis analysisResults, WatchlistItem w) {

		Quote q = yahooFinanceQuoteService.getQuote(w.getStock());

		if (Float.valueOf(q.getChange()) > 10) {

			analysisResults.addAnalysis(new Analysis(w.getStock() + " as risen more that 10% today"));
		}
	}
}
