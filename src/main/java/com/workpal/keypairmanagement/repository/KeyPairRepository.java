package com.workpal.keypairmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workpal.keypairmanagement.domain.KeyPair;

@Repository
public interface KeyPairRepository extends MongoRepository<KeyPair, String> {

}
