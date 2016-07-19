package com.manthalabs.portfoliomanager.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.manthalabs.portfoliomanager.visitor.WatchlistStockItemVisitor;

@Document(collection = "watchlistItem")
public class WatchlistItem extends AbstractAuditingEntity implements Serializable {

	@Id
	private String stock;

	private String user;

	private String timeAdded;

	private double valueWhenAdded;

	private double qty;

	private double shortTermGainLoss;

	private double longTemGainLoss;

	private String lastUpdatedDDTM;

	private double marketValue;

	private Set<WatchlistQtyLineItem> qtyLineItems = new HashSet<>();

	public Set<WatchlistQtyLineItem> getQtyLineItems() {
		return qtyLineItems;
	}

	public void addQtyItem(String watchlist, double qty, String date, double priceAtPurchase) {
		if (qty > 0) {
			WatchlistQtyLineItem w = new WatchlistQtyLineItem(qty, date, priceAtPurchase, watchlist);
			qtyLineItems.add(w);
		}
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public String getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(String timeAdded) {
		this.timeAdded = timeAdded;
	}

	public double getValueWhenAdded() {
		return valueWhenAdded;
	}

	public void setValueWhenAdded(double valueWhenAdded) {
		this.valueWhenAdded = valueWhenAdded;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getShortTermGainLoss() {
		return shortTermGainLoss;
	}

	public void setShortTermGainLoss(double shortTermGainLoss) {
		this.shortTermGainLoss = shortTermGainLoss;
	}

	public double getLongTemGainLoss() {
		return longTemGainLoss;
	}

	public void setLongTemGainLoss(double longTemGainLoss) {
		this.longTemGainLoss = longTemGainLoss;
	}

	public String getLastUpdatedDDTM() {
		return lastUpdatedDDTM;
	}

	public void setLastUpdatedDDTM(String lastUpdatedDDTM) {
		this.lastUpdatedDDTM = lastUpdatedDDTM;
	}

	public double getOverallGainLoss() {
		return longTemGainLoss + shortTermGainLoss;

	}

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

	public void accept(WatchlistStockItemVisitor watchlistStockItemVisitor) {
		watchlistStockItemVisitor.visit(this);
	}

	public static class WatchlistQtyLineItem {

		private double qty;

		private String dateBought;

		private double priceAtPurchase;

		private String watchlist;

		public WatchlistQtyLineItem(double qty, String dateBought, double priceAtPurchase, String watchlist) {
			this.qty = qty;
			this.dateBought = dateBought;
			this.priceAtPurchase = priceAtPurchase;
			this.watchlist = watchlist;
		}

		public double getQty() {
			return qty;
		}

		public boolean isLongTerm() {
			DateTime dt = DateTime.parse(getDateBought());
			return DateTime.now().minusYears(1).isAfter(dt);
		}

		public void setQty(double qty) {
			this.qty = qty;
		}

		public String getDateBought() {
			return dateBought;
		}

		public void setDateBought(String dateBought) {
			this.dateBought = dateBought;
		}

		public double getPriceAtPurchase() {
			return priceAtPurchase;
		}

		public void setPriceAtPurchase(double priceAtPurchase) {
			this.priceAtPurchase = priceAtPurchase;
		}

		public String getWatchlist() {
			return watchlist;
		}

		public void setWatchlist(String watchlist) {
			this.watchlist = watchlist;
		}

	}

}
