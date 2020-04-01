package com.workpal.keypairmanagement.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.repository.KeyPairRepository;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;
import com.workpal.keypairmanagement.service.KeyPairService;

@Service
public class KeyPairServiceImpl implements KeyPairService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairServiceImpl.class);

	@Autowired
	private KeyPairRepository keyPairRepository;

	@Override
	public KeyPair createKeyPair(KeyPairCreateRequest keyPairCreateRequest) {
		var keyPair = new KeyPair(keyPairCreateRequest);
		LOGGER.info("KEYPAIR {} ",keyPair);
		var savedKeyPair = keyPairRepository.save(keyPair);
		return savedKeyPair;
	}

	@Override
	public void generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest) {

	}

}
