package com.workpal.keypairmanagement.service;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

public interface KeyPairService {

	public KeyPair createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	public void generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
}
