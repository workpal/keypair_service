package com.workpal.keypairmanagement.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

public interface KeyPairApi {

	@PostMapping(value = "/keypair/create", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	
	@PostMapping(value = "/keypair/generate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	
}
