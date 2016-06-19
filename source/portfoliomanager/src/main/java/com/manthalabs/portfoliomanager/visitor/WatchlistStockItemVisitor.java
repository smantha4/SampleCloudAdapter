package com.manthalabs.portfoliomanager.visitor;

import com.manthalabs.portfoliomanager.domain.WatchlistItem;

public interface WatchlistStockItemVisitor {

	void visit(WatchlistItem w);

}
