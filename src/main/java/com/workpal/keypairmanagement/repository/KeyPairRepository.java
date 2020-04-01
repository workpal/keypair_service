package com.workpal.keypairmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.workpal.keypairmanagement.domain.KeyPair;

@Component
public interface KeyPairRepository extends MongoRepository<KeyPair, String> {

}
