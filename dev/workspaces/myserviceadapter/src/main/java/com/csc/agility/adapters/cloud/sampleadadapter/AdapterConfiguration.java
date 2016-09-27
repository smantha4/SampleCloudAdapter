package com.csc.agility.adapters.cloud.sampleadadapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Provides the configuration defined for the Adapter
 * 
 * @author srirammantha
 *
 */
@Component
@Import(AppConfig.class)
public class AdapterConfiguration {

	public @Value("${adapter.name}") String adapterName ;
	public @Value("${adapter.version}") String adapterVersion;
	public @Value("${adapter.cloudtype}") String cloudType;
	public @Value("${adapter.description}") String description ;
	
	
	public String getAdapterName() {
		return adapterName;
	}
	public String getAdapterVersion() {
		return adapterVersion;
	}
	public String getCloudType() {
		return cloudType;
	}
	
	public String getDescription() {
		return description;
	}

	
}
