package com.manthalabs.portfoliomanagement.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanagement.model.Quote;
import com.manthalabs.portfoliomanagement.service.YahooFinanceQuoteService;

@Component
@Path("/quote")
public class QuotesResource {
	
	@Autowired
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	@GET
	@Produces("application/json")
	public Quote quote(@QueryParam("symbol") String symbol) {
		return yahooFinanceQuoteService.getQuote(symbol);
	}

}
