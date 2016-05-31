package com.manthalabs.portfoliomanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manthalabs.portfoliomanager.domain.Watchlist;

public interface WatchlistRepository extends MongoRepository<Watchlist, String> {

}
