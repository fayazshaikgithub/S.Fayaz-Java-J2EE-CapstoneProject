package com.example.dashboard.repository;


import com.example.dashboard.model.AccountSummary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountSummaryRepository extends ReactiveMongoRepository<AccountSummary, String> {
}

