package com.workpal.keypairmanagement.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

public interface KeyPairApi {

	@PostMapping(value = "/keypair/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	
	@PostMapping(value = "/keypair/generate", consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	
	@GetMapping(value = "/keypair", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAllKeyPair();
	
	@GetMapping(value = "/keypair/{keyPairId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getKeyPairById(String keyPairId);
	
}
