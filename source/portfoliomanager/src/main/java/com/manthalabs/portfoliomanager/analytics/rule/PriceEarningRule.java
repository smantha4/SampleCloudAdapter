package com.manthalabs.portfoliomanager.analytics.rule;

import org.apache.commons.lang3.StringUtils;
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
public class PriceEarningRule implements WatchlistItemAnalysisRule {

	@Autowired
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	@Override
	public void run(WatchlistStockItemAnalysis analysisResults, Watchlist w, WatchlistItem wi) {
		// TODO Auto-generated method stub
		Quote q = yahooFinanceQuoteService.getQuote(wi.getStock());

		String peString = q.getPeRatio();

		if (StringUtils.isNotBlank(peString)) {

			double pe = Double.valueOf(peString);

			if (pe > 30) {
				new Analysis(wi.getStock() + " has high pe of " + pe);

			}

			if (pe < 10) {
				new Analysis(wi.getStock() + " has low pe of " + pe);

			}
		}

	}

}
