package com.manthalabs.portfoliomanager.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.service.YahooFinanceQuoteService;


@RestController
@RequestMapping("/api")
public class QuotesResource {
	
	@Autowired
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	  @RequestMapping(value = "/quote",
		        method = RequestMethod.GET,
		        produces = MediaType.APPLICATION_JSON_VALUE)
	public Quote quote(@RequestParam(value="query", required=true)  String symbol) {
		return yahooFinanceQuoteService.getQuote(symbol);
	}

}
