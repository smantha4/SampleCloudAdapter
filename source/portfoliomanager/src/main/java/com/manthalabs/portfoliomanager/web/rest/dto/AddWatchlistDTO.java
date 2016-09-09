package com.manthalabs.portfoliomanager.web.rest.dto;

public class AddWatchlistDTO {

	private String name;
	private boolean isIRA;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIRA() {
		return isIRA;
	}

	public void setIRA(boolean isIRA) {
		this.isIRA = isIRA;
	}

}
