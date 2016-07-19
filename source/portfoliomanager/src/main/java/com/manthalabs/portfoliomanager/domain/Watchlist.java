package com.manthalabs.portfoliomanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "watchlist")
public class Watchlist extends AbstractAuditingEntity implements Serializable {

	@Id
	private String id;

	private String name;

	private String user;

	private boolean isIRA;

	private List<String> stocks = new ArrayList<>();;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<String> getStocks() {
		return stocks;
	}

	public void setStocks(List<String> stocks) {
		this.stocks = stocks;
	}

	public boolean isIRA() {
		return isIRA;
	}

	public void setIRA(boolean isIRA) {
		this.isIRA = isIRA;
	}

}
