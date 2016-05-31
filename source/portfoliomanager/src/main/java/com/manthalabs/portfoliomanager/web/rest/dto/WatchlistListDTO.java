package com.manthalabs.portfoliomanager.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class WatchlistListDTO {

	private List<WatchlistNameDTO> watchLists = new ArrayList<WatchlistNameDTO>();

	public WatchlistListDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<WatchlistNameDTO> getWatchLists() {
		return watchLists;
	}

	public void setWatchLists(List<WatchlistNameDTO> watchLists) {
		this.watchLists = watchLists;
	}

	public static class WatchlistNameDTO {
		private String name;
		private String id;

		public WatchlistNameDTO() {
		}

		public WatchlistNameDTO(String name, String id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

}
