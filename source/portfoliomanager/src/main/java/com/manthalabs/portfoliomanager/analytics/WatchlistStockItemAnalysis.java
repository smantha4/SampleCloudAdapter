package com.manthalabs.portfoliomanager.analytics;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.manthalabs.portfoliomanager.util.DateTimeUtil;

@Document(collection = "WatchlistStockItemAnalysis")
public class WatchlistStockItemAnalysis {

	@Id
	private String symbol;

	public Set<Analysis> results = new TreeSet<>();

	public Set<Analysis> getResults() {
		return results;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void addAnalysis(Analysis a) {

		Optional<Analysis> m = results.stream().filter(t -> t.getMessage().equals(a.getMessage())).findAny();

		if (!m.isPresent()) {
			a.setOriginalTime(DateTimeUtil.getCurrentDateTime());
			results.add(a);
		}
	}

}
