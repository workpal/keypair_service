package com.workpal.keypairmanagement.service;

import org.springframework.stereotype.Service;

import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

@Service
public interface KeyPairService {

	public void createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	public void generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
}
