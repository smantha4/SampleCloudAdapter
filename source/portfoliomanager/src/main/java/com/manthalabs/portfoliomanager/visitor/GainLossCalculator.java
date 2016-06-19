package com.manthalabs.portfoliomanager.visitor;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;

public class GainLossCalculator implements WatchlistStockItemVisitor {

	private Quote q;

	public GainLossCalculator(Quote q) {
		this.q = q;
	}

	@Override
	public void visit(WatchlistItem w) {

	}
}
