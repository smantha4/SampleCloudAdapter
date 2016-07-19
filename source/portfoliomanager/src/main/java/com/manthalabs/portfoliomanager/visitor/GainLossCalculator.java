package com.manthalabs.portfoliomanager.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.util.MiscUtil;

public class GainLossCalculator implements WatchlistStockItemVisitor {

	private Quote q;
	private Watchlist wl;

	private final Logger log = LoggerFactory.getLogger(GainLossCalculator.class);

	public GainLossCalculator(Quote q, Watchlist w) {
		this.q = q;
		this.wl = w;
	}

	@Override
	public void visit(WatchlistItem w) {
		double longTermGainloss = w.getQtyLineItems().stream().filter(i -> i.getWatchlist().equals(wl.getId()))
				.mapToDouble(i -> {

					if (i.isLongTerm()) {
						double purchasePrice = i.getPriceAtPurchase() * i.getQty();
						double currentMarketPrice = q.getCurrentPriceFloat() * i.getQty();
						return currentMarketPrice - purchasePrice;
					} else {
						return 0;
					}
				}).sum();

		log.info("long term gain for " + w.getStock() + " is " + longTermGainloss);

		w.setLongTemGainLoss(MiscUtil.RoundTo2Decimals(longTermGainloss));

		double shortTermGainLoss = w.getQtyLineItems().stream().filter(i -> i.getWatchlist().equals(wl.getId()))
				.mapToDouble(i -> {

					if (!i.isLongTerm()) {
						double purchasePrice = i.getPriceAtPurchase() * i.getQty();
						double currentMarketPrice = q.getCurrentPriceFloat() * i.getQty();
						return currentMarketPrice - purchasePrice;
					} else {
						return 0;
					}
				}).sum();

		log.info("long term gain for " + w.getStock() + " is " + shortTermGainLoss);

		w.setShortTermGainLoss(MiscUtil.RoundTo2Decimals(shortTermGainLoss));
		w.setQty(w.getQtyLineItems().stream().filter(i -> i.getWatchlist().equals(wl.getId()))
				.mapToDouble(i -> i.getQty()).sum());

	}
}
