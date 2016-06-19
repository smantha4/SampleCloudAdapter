package com.manthalabs.portfoliomanager.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Alert")
public class Alert extends AbstractAuditingEntity implements Serializable {

	public enum AlertType {
		SPECIFIC_PRICE, PRICE_CHANGE_52Week;
	}

	private String symbol;
	private String price;
	private AlertType alertType;
	private float priceChange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public float getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(float priceChange) {
		this.priceChange = priceChange;
	}

}
