package com.systemedebons.bonification.Repository;

import com.systemedebons.bonification.Entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscationRepository extends MongoRepository<Transaction, String> {
}