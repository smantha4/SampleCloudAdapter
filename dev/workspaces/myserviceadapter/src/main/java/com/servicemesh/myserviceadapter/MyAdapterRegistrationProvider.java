package com.servicemesh.myserviceadapter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.RegistrationRequest;

/**
 * Provides registration details for the adapter
 */
@Component
public class MyAdapterRegistrationProvider {

	@Autowired
	private MyAdapterConfiguration myAdapterConfiguration;
	
	private static final Logger logger = Logger.getLogger(MyAdapterRegistrationProvider.class);


	public RegistrationRequest getRegistrationRequest() {

		logger.debug("building the registration request..");
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setName(myAdapterConfiguration.getAdapterName());
		registrationRequest.setVersion(myAdapterConfiguration.getAdapterVersion());
		registrationRequest.setDescription(myAdapterConfiguration.getDescription());

		// TODO:Add custom configuration to register the adapter
		logger.debug("Registration request " + registrationRequest);
		return registrationRequest;

	}

}
