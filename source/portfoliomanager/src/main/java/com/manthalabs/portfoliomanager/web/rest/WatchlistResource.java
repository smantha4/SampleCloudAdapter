package com.manthalabs.portfoliomanager.web.rest;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manthalabs.portfoliomanager.analytics.WatchlistStockItemAnalysis;
import com.manthalabs.portfoliomanager.domain.Watchlist;
import com.manthalabs.portfoliomanager.repository.WatchlistItemAnalysisRepository;
import com.manthalabs.portfoliomanager.repository.WatchlistRepository;
import com.manthalabs.portfoliomanager.service.WatchlistService;
import com.manthalabs.portfoliomanager.web.rest.dto.AddWatchlistStockItem;
import com.manthalabs.portfoliomanager.web.rest.dto.MessagesDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.MessagesDTO.MessageDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistDTO;
import com.manthalabs.portfoliomanager.web.rest.dto.WatchlistListDTO;

@RestController
@RequestMapping("/api")
public class WatchlistResource {

	@Autowired
	private WatchlistService watchListService;

	@Autowired
	private WatchlistRepository watchlistRepository;

	@Autowired
	private WatchlistItemAnalysisRepository watchlistItemAnalysisRepository;

	/**
	 * Get list of watchlists
	 * 
	 * @return
	 */
	@RequestMapping(value = "/watchlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WatchlistListDTO getWatchLists() {

		return watchListService.getWatchLists();

	}

	/**
	 * Get Watch detail
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/watchlist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WatchlistDTO getWatchList(@PathVariable String id) {

		WatchlistDTO w = new WatchlistDTO();
		w.setWatchLists(watchListService.getWatchlistStockItems(id));
		return w;

	}

	/**
	 * Add stock to watchlist
	 * 
	 * @param id
	 * @param item
	 */
	@RequestMapping(value = "/watchlist/{id}/watchliststockitem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addStockItemToWatchlist(@PathVariable String id, @RequestBody AddWatchlistStockItem item) {
		watchListService.addStockItemToWatchlist(id, item);
	}

	@RequestMapping(value = "/watchlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createWatchlist(@RequestBody String name) {
		Validate.notNull(name, "Watchlistname cannot be null");
		Watchlist w = new Watchlist();
		w.setName(name);
		watchlistRepository.save(w);
	}

	/**
	 * Delete stock from watchlist
	 * 
	 * @param id
	 * @param symbol
	 * @return
	 */
	@RequestMapping(value = "/watchlist/{id}/watchliststockitem/{symbol}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteWatchlistStockItem(@PathVariable String id,
			@PathVariable String symbol) {

		Watchlist w = watchlistRepository.findOne(id);

		if (w == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		w.getStocks().remove(symbol);

		watchlistRepository.save(w);

		return new ResponseEntity<String>("Success", HttpStatus.OK);

	}

	/**
	 * delete watchlist
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/watchlist/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteWatchlist(@PathVariable String id) {

		watchlistRepository.delete(id);

		return new ResponseEntity<String>("Success", HttpStatus.OK);

	}

	/**
	 * delete watchlist
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/watchlist/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MessagesDTO getMessagesForWatchlist(@RequestParam("watchlistid") String watchlistid) {

		Watchlist w = watchlistRepository.findOne(watchlistid);

		List<String> stocks = w.getStocks();

		MessagesDTO ms = new MessagesDTO();

		stocks.stream().forEach(s -> {
			WatchlistStockItemAnalysis wsa = watchlistItemAnalysisRepository.findOne(s);
			if (wsa != null && wsa.getResults() != null) {
				wsa.getResults().stream().forEach(a -> ms.getMessages().add(new MessageDTO(a.getMessage())));
			}
		});

		return ms;
	}

}
