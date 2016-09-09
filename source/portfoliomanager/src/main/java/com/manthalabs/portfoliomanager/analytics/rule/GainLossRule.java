package com.manthalabs.portfoliomanager.analytics.rule;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanager.analytics.Analysis;
import com.manthalabs.portfoliomanager.analytics.WatchlistItemAnalysisRule;
import com.manthalabs.portfoliomanager.analytics.WatchlistStockItemAnalysis;
import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.service.YahooFinanceQuoteService;
import com.manthalabs.portfoliomanager.visitor.GainLossCalculator;

@Component
public class GainLossRule implements WatchlistItemAnalysisRule {

	@Inject
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	@Override
	public void run(WatchlistStockItemAnalysis analysisResults, Watchlist w, WatchlistItem wi) {

		Quote q = yahooFinanceQuoteService.getQuote(wi.getStock());
		GainLossCalculator gainLossCalculator = new GainLossCalculator(q, w);
		wi.accept(gainLossCalculator);

		if (wi.getOverallGainLossPerc() > 10) {
			analysisResults.addAnalysis(new Analysis(wi.getStock()
					+ " has a overall gain of more that 10%. Current gain is " + wi.getOverallGainLossPerc()));
		}

	}

}
