package com.manthalabs.portfoliomanagement;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.manthalabs.portfoliomanagement.resource.QuotesResource;

@Component
public class RestEndpointConfig extends ResourceConfig  {

	public RestEndpointConfig() {
		packages("com.manthalabs.portfoliomanagement.resource");
	}

}
