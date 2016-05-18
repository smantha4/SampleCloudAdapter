package com.manthalabs.portfoliomanager.service;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.manthalabs.portfoliomanager.domain.Quote;


@Component
public class YahooFinanceQuoteService {
	
	private RestTemplate restTemplate  = new RestTemplate();
	
	
	public Quote getQuote(String symbol){
		
		YFResponse response = 
				restTemplate.getForObject("http://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.quotes where symbol in (\""+ symbol +"\")&env=http://datatables.org/alltables.env&format=json", YFResponse.class);
		
		
		Quote q = new Quote();
		q.setTicker(symbol);
		q.setCurrentPrice(response.query.results.quote.Ask);
		q.setChange(response.query.results.quote.Change);
		q.setDaysHigh(response.query.results.quote.DaysHigh);
		q.setDaysLow(response.query.results.quote.DaysLow);
		q.setName(response.query.results.quote.Name);
		q.setDividendYield(response.query.results.quote.DividendYield);
		q.setMarketCapitalization(response.query.results.quote.MarketCapitalization);
		return q;
		
		
	}

}
