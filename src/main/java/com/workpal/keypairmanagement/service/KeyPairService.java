package com.workpal.keypairmanagement.service;

import org.springframework.stereotype.Service;

import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

@Service
public interface KeyPairService {

	public void saveKeyPair(KeyPairCreateRequest keyPairCreateRequest);
}
