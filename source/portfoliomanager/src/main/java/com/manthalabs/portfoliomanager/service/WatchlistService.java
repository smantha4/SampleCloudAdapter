package com.manthalabs.portfoliomanager.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.repository.WatchlistItemRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistRepository;
import com.manthalabs.portfoliomanager.util.DateTimeUtil;
import com.manthalabs.portfoliomanager.util.MiscUtil;
import com.manthalabs.portfoliomanager.visitor.GainLossCalculator;
import com.manthalabs.portfoliomanager.visitor.PaulMerimanSuggestions;
import com.manthalabs.portfoliomanager.web.rest.dto.AddWatchlistStockItem;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchListStockItemDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistListDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistListDTO.WatchlistNameDTO;

@Service
public class WatchlistService {

	@Inject
	private WatchlistRepository watchlistRepository;

	@Inject
	private WatchlistItemRepository watchlistItemRepository;

	@Inject
	private YahooFinanceQuoteService yahooFinanceQuoteService;

	/**
	 * get All watchlists
	 * 
	 * @return
	 */
	public WatchlistListDTO getWatchLists() {
		WatchlistListDTO wListDTO = new WatchlistListDTO();
		List<WatchlistNameDTO> watchLists = watchlistRepository.findAll().stream().map(wl -> {
			WatchlistNameDTO wm = new WatchlistNameDTO(wl.getName(), wl.getId());
			return wm;
		}).collect(Collectors.toList());
		wListDTO.setWatchLists(watchLists);
		return wListDTO;
	}

	/**
	 * Get stocks in watchlist
	 * 
	 * @param watchlist
	 * @return
	 */
	public WatchlistDTO getWatchlistStockItems(String watchlist) {

		WatchlistDTO wDTO = new WatchlistDTO();

		Watchlist w = watchlistRepository.findOne(watchlist);
		if (w == null) {
			return wDTO;
		}
		wDTO.setWatchlistName(w.getName());
		wDTO.setIRA(w.isIRA());

		if (w == null || CollectionUtils.isEmpty(w.getStocks())) {
			return wDTO;
		}

		final PaulMerimanSuggestions p = new PaulMerimanSuggestions();

		List<WatchListStockItemDTO> watchlistItems = w.getStocks().parallelStream()
				.map(s -> watchlistItemRepository.findOne(s)).filter(wi -> wi != null).map(wi -> {
					WatchListStockItemDTO ws = new WatchListStockItemDTO();

					Quote q = yahooFinanceQuoteService.getQuote(wi.getStock());

					GainLossCalculator gainLossCalculator = new GainLossCalculator(q, w);
					wi.accept(gainLossCalculator);

					ws.setQty(wi.getQty());
					ws.setTotalValue(MiscUtil.RoundTo2Decimals(wi.getQty() * q.getCurrentPriceFloat()));
					ws.setGain(wi.getOverallGainLoss());
					ws.setQuote(q);

					// Watch date is the time added
					if (wi.getTimeAdded() != null) {
						ws.setWatchDate(DateTimeUtil.getFormattedDate(wi.getTimeAdded()));
					}

					ws.setWatchPrice(String.valueOf(MiscUtil.RoundTo2Decimals(wi.getValueWhenAdded())));
					ws.setMarketValue(wi.getMarketValue());
					ws.setPurchaseValue(wi.getPurchaseValue());
					ws.setGainlossPerct(wi.getOverallGainLossPerc());

					p.addSuggestion(ws);

					return ws;

				}).collect(Collectors.toList());

		// Calculate the total gain/loss
		wDTO.setTotalGainLoss(MiscUtil.RoundTo2Decimals(watchlistItems.stream().mapToDouble(i -> i.getGain()).sum()));

		// Get the total account value
		wDTO.setTotalAccountValue(
				MiscUtil.RoundTo2Decimals(watchlistItems.stream().mapToDouble(i -> i.getTotalValue()).sum()));

		// Sets the account % for each stock in the account
		watchlistItems.stream().forEach(wi -> {
			if (wDTO.getTotalAccountValue() > 0 && wi.getTotalValue() > 0) {
				wi.setAccountPercentage(
						MiscUtil.RoundTo2Decimals(wi.getTotalValue() / wDTO.getTotalAccountValue() * 100));

			}
		});

		wDTO.setWatchLists(watchlistItems);

		return wDTO;
	}

	/**
	 * Add a stock to a watchlst
	 * 
	 * 
	 * @param watchlist
	 * @param addWatchlistStockItem
	 */
	public void addStockItemToWatchlist(String watchlist, AddWatchlistStockItem addWatchlistStockItem) {

		Watchlist w = watchlistRepository.findOne(watchlist);

		if (w == null) {
			throw new RuntimeException("Watchlist not found");
		}

		Validate.notNull(addWatchlistStockItem.getSymbol());

		if (!w.getStocks().contains(addWatchlistStockItem.getSymbol())) {
			w.getStocks().add(addWatchlistStockItem.getSymbol());
			watchlistRepository.save(w);
		}

		WatchlistItem wi = watchlistItemRepository.findOne(addWatchlistStockItem.getSymbol());

		if (wi == null) {
			wi = new WatchlistItem();
			wi.setStock(addWatchlistStockItem.getSymbol());
			wi.setValueWhenAdded(
					yahooFinanceQuoteService.getQuote(addWatchlistStockItem.getSymbol()).getCurrentPriceFloat());

			// Save the time the record was addede to watch list
			wi.setTimeAdded(DateTimeUtil.getCurrentDateTime());

			addNewStockLot(watchlist, addWatchlistStockItem, wi);

		} else {

			// Note time the date the stock was added if time was not
			// recorded earlier
			if (wi.getTimeAdded() == null) {
				wi.setTimeAdded(DateTimeUtil.getCurrentDateTime());
			}

			// Add a new entry
			addNewStockLot(watchlist, addWatchlistStockItem, wi);
		}
		watchlistItemRepository.save(wi);

	}

	private void addNewStockLot(String watchlist, AddWatchlistStockItem addWatchlistStockItem, WatchlistItem wi) {
		// Add new stock item keeping track of the watch list
		if (StringUtils.isNotEmpty(addWatchlistStockItem.getQty())) {

			String dateBought = StringUtils.isNotBlank(addWatchlistStockItem.getDateBought())
					? DateTimeUtil.normalizeDate(addWatchlistStockItem.getDateBoughtFormatted())
					: DateTimeUtil.getCurrentDateTime();

			double lotPrice = StringUtils.isNotBlank(addWatchlistStockItem.getPriceBought())
					? Double.valueOf(addWatchlistStockItem.getPriceBought()) : wi.getValueWhenAdded();

			wi.addQtyItem(watchlist, Float.valueOf(addWatchlistStockItem.getQty()), dateBought, lotPrice);
		}
	}

}
