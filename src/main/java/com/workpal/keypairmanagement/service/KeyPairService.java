package com.workpal.keypairmanagement.service;

import java.util.List;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

public interface KeyPairService {

	public void createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	public String generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	
	public List<KeyPair> getAllKeyPairs();
	
	public KeyPair getKeyPairById(String keyPairId);
}
