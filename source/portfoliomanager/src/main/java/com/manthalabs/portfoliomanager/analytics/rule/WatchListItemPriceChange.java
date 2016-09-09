package com.manthalabs.portfoliomanager.analytics.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanager.analytics.Analysis;
import com.manthalabs.portfoliomanager.analytics.WatchlistItemAnalysisRule;
import com.manthalabs.portfoliomanager.analytics.WatchlistStockItemAnalysis;
import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.service.YahooFinanceQuoteService;

@Component
public class WatchListItemPriceChange implements WatchlistItemAnalysisRule {

	@Autowired
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	@Override
	public void run(WatchlistStockItemAnalysis analysisResults, Watchlist w, WatchlistItem wi) {

		Quote q = yahooFinanceQuoteService.getQuote(wi.getStock());

		if (q.getChangePercentage() > 2) {

			analysisResults.addAnalysis(new Analysis(wi.getStock() + " as risen more that 2% today"));
		}

		float currentPrice = q.getCurrentPriceFloat();

		if (wi.getValueWhenAdded() > 0) {
			double priceDiff = currentPrice - wi.getValueWhenAdded();
			double priceChngPerc = priceDiff / wi.getValueWhenAdded() * 100;

			if (Math.abs(priceChngPerc) > 10) {
				analysisResults.addAnalysis(
						new Analysis(wi.getStock() + " price changed 10% since it was added to watchlist"));

			}
		}

	}
}
