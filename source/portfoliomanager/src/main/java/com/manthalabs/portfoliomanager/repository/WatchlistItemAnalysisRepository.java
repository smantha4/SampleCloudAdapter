package com.manthalabs.portfoliomanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manthalabs.portfoliomanager.analytics.WatchlistStockItemAnalysis;

public interface WatchlistItemAnalysisRepository extends MongoRepository<WatchlistStockItemAnalysis, String> {

}
