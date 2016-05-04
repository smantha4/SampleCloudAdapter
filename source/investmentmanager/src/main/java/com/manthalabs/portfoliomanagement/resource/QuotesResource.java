package com.manthalabs.portfoliomanagement.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanagement.model.Qoute;

@Component
@Path("/quotes")
public class QuotesResource {

	@GET
	@Produces("application/json")
	public Qoute quote() {
		Qoute q = new Qoute();
		q.setTicker("AMZN");
		q.setCurrentPrice("600");
		return q;
	}

}
