package com.manthalabs.portfoliomanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manthalabs.portfoliomanager.domain.WatchlistItem;

public interface WatchlistItemRepository extends MongoRepository<WatchlistItem, String> {

}
