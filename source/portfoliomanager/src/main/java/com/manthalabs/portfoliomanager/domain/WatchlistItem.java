package com.manthalabs.portfoliomanager.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.manthalabs.portfoliomanager.visitor.WatchlistStockItemVisitor;

@Document(collection = "watchlistItem")
public class WatchlistItem extends AbstractAuditingEntity implements Serializable {

	@Id
	private String stock;

	private String user;

	private String timeAdded;

	private String valueWhenAdded;

	private String qty;

	private float shortTermGainLoss;

	private float longTemGainLoss;

	private String lastUpdatedDDTM;

	private Set<WatchlistQtyLineItem> qtyLineItems = new HashSet<>();

	public Set<WatchlistQtyLineItem> getQtyLineItems() {
		return qtyLineItems;
	}

	public void addQtyItem(String watchlist, float qty, String date, float priceAtPurchase) {
		if (qty > 0) {
			WatchlistQtyLineItem w = new WatchlistQtyLineItem(qty, date, priceAtPurchase, watchlist);
			qtyLineItems.add(w);
		}
	}

	public String getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(String timeAdded) {
		this.timeAdded = timeAdded;
	}

	public String getValueWhenAdded() {
		return valueWhenAdded;
	}

	public void setValueWhenAdded(String valueWhenAdded) {
		this.valueWhenAdded = valueWhenAdded;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public float getShortTermGainLoss() {
		return shortTermGainLoss;
	}

	public void setShortTermGainLoss(float shortTermGainLoss) {
		this.shortTermGainLoss = shortTermGainLoss;
	}

	public float getLongTemGainLoss() {
		return longTemGainLoss;
	}

	public void setLongTemGainLoss(float longTemGainLoss) {
		this.longTemGainLoss = longTemGainLoss;
	}

	public String getLastUpdatedDDTM() {
		return lastUpdatedDDTM;
	}

	public void setLastUpdatedDDTM(String lastUpdatedDDTM) {
		this.lastUpdatedDDTM = lastUpdatedDDTM;
	}

	public float getOverallGainLoss() {
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

		private float qty;

		private String dateBought;

		private float priceAtPurchase;

		private String watchlist;

		public WatchlistQtyLineItem(float qty, String dateBought, float priceAtPurchase, String watchlist) {
			this.qty = qty;
			this.dateBought = dateBought;
			this.priceAtPurchase = priceAtPurchase;
			this.watchlist = watchlist;
		}

		public float getQty() {
			return qty;
		}

		public void setQty(float qty) {
			this.qty = qty;
		}

		public String getDateBought() {
			return dateBought;
		}

		public void setDateBought(String dateBought) {
			this.dateBought = dateBought;
		}

		public float getPriceAtPurchase() {
			return priceAtPurchase;
		}

		public void setPriceAtPurchase(float priceAtPurchase) {
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
