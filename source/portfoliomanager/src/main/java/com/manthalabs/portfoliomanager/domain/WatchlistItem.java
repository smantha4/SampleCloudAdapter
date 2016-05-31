package com.manthalabs.portfoliomanager.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "watchlistItem")
public class WatchlistItem extends AbstractAuditingEntity implements Serializable{
	
	@Id
	private String stock;
	
	private String user;

	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
