package com.manthalabs.portfoliomanager.visitor;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.manthalabs.portfoliomanager.web.rest.dto.WatchListStockItemDTO;

public class PaulMerimanSuggestions {

	private static Map<String, Recommendations> recommendations = new HashMap<>();

	public static final class Recommendations {
		String stock;
		String aggressive;
		String moderate;
		String conservative;
	}

	static {

		try (Stream<String> stream = Files
				.lines(java.nio.file.Paths.get(ClassLoader.getSystemResource("IRA/schwab-suggestions.txt").toURI()))) {

			stream.forEach(l -> {
				String[] values = StringUtils.split(l, ",");
				Recommendations r = new Recommendations();
				r.stock = values[0];
				r.aggressive = values[1];
				r.moderate = values[2];
				r.conservative = values[3];

				recommendations.put(values[0], r);

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSuggestion(WatchListStockItemDTO w) {
		Recommendations r = recommendations.get(w.getQuote().getTicker());

		if (r != null) {
			w.setRecPecAggr(r.aggressive);
			w.setRecPecConservative(r.conservative);
			w.setRecPecModerate(r.moderate);
		}
	}

}
