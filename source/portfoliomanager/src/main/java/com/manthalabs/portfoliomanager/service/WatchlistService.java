package com.manthalabs.portfoliomanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import com.manthalabs.portfoliomanager.domain.Quote;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.domain.WatchlistItem;
import com.manthalabs.portfoliomanager.repository.WatchlistItemRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistRepository;
import com.manthalabs.portfoliomanager.web.rest.dto.AddWatchlistStockItem;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchListStockItemDTO;
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
	public List<WatchListStockItemDTO> getWatchlistStockItems(String watchlist) {
		Watchlist w = watchlistRepository.findOne(watchlist);

		if (w == null || CollectionUtils.isEmpty(w.getStocks())) {
			return new ArrayList<>();
		}

		return w.getStocks().parallelStream().map(s -> {
			WatchListStockItemDTO ws = new WatchListStockItemDTO();
			Quote q = yahooFinanceQuoteService.getQuote(s);
			ws.setQuote(q);
			return ws;

		}).collect(Collectors.toList());
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
			} else {
				// Lets merge the QTy
			}
			watchlistItemRepository.save(wi);
		}

	}

}
