package com.manthalabs.portfoliomanager.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.repository.WatchlistItemRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistRepository;
import com.manthalabs.portfoliomanager.util.MiscUtil;
import com.manthalabs.portfoliomanager.visitor.GainLossCalculator;
import com.manthalabs.portfoliomanager.visitor.PaulMerimanSuggestions;
import com.manthalabs.portfoliomanager.web.rest.dto.AddWatchlistStockItem;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchListStockItemDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistListDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistListDTO.WatchlistNameDTO;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

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
		wDTO.setWatchlistName(w.getName());
		wDTO.setIRA(w.isIRA());

		if (w == null || CollectionUtils.isEmpty(w.getStocks())) {
			return wDTO;
		}

		final PaulMerimanSuggestions p = new PaulMerimanSuggestions();

		List<WatchListStockItemDTO> watchlistItems = w.getStocks().parallelStream().map(s -> {
			WatchListStockItemDTO ws = new WatchListStockItemDTO();

			WatchlistItem wi = watchlistItemRepository.findOne(s);

			Quote q = yahooFinanceQuoteService.getQuote(s);

			GainLossCalculator gainLossCalculator = new GainLossCalculator(q, w);
			wi.accept(gainLossCalculator);

			ws.setQty(wi.getQty());
			ws.setTotalValue(MiscUtil.RoundTo2Decimals(wi.getQty() * q.getCurrentPriceFloat()));
			ws.setGain(wi.getOverallGainLoss());
			ws.setQuote(q);

			p.addSuggestion(ws);

			return ws;

		}).collect(Collectors.toList());

		wDTO.setTotalGainLoss(MiscUtil.RoundTo2Decimals(watchlistItems.stream().mapToDouble(i -> i.getGain()).sum()));
		wDTO.setTotalAccountValue(
				MiscUtil.RoundTo2Decimals(watchlistItems.stream().mapToDouble(i -> i.getTotalValue()).sum()));

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

			WatchlistItem wi = watchlistItemRepository.findOne(addWatchlistStockItem.getSymbol());

			if (wi == null) {
				wi = new WatchlistItem();
				wi.setStock(addWatchlistStockItem.getSymbol());
				wi.setQty(Double.valueOf(addWatchlistStockItem.getQty()));
				if (StringUtils.isEmpty(addWatchlistStockItem.getPriceBought())) {
					wi.setValueWhenAdded(yahooFinanceQuoteService.getQuote(addWatchlistStockItem.getSymbol())
							.getCurrentPriceFloat());
				}

				// Add new stock item keeping track of the watch list
				if (StringUtils.isNotEmpty(addWatchlistStockItem.getQty())) {
					wi.addQtyItem(watchlist, Float.valueOf(addWatchlistStockItem.getQty()), DateTime.now().toString(),
							wi.getValueWhenAdded());
				}

			} else {
				// Add a new entry
				if (StringUtils.isNotEmpty(addWatchlistStockItem.getQty())) {
					wi.addQtyItem(watchlist, Float.valueOf(addWatchlistStockItem.getQty()), DateTime.now().toString(),
							wi.getValueWhenAdded());
				}
			}
			watchlistItemRepository.save(wi);
		}

	}

}
