package com.workpal.keypairmanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.repository.KeyPairRepository;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;
import com.workpal.keypairmanagement.service.Impl.KeyPairServiceImpl;

@SpringBootTest
public class KeyPairServiceTest {

	@Mock
	private KeyPairRepository keyPairRepo;

	@InjectMocks
	private KeyPairService keyPairService = new KeyPairServiceImpl();
	

	
	
	@DisplayName("CreateKeyPair")
	@Test
	void testCreateKeyPair() {
		var keyPairCreateRequest = new KeyPairCreateRequest();
		keyPairCreateRequest.setName("Uthra-key_pair-test");
		keyPairCreateRequest.setDescription("Description");
		keyPairCreateRequest.setKey("AAAAB3NzaC1yc2EAAAADAQABAAAAgQDz0OVfMEQMk fKPDL8c25HBM27Rum12e5G6td+CW/3NEW4dvQk/fC5i4zdV/ Zg8R5zY12DBdjiUqCkZa/EPZzC9Mef+4IgyIlkJdN/Av1zCDb x6QfEhGHd5PufI44NBkPJdi5kTjCL25TWHe00KQ3hF6ui45w QPhFMyIP/sQjqYQ==");
		var savedKeyPair = keyPairService.createKeyPair(keyPairCreateRequest);
		var keyPair = createKeyPair(keyPairCreateRequest);
		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(keyPair);
		assertEquals(keyPair.getName(), savedKeyPair.getName());
	}
	
	private KeyPair createKeyPair(KeyPairCreateRequest keyPairCreateRequest) {
		return new KeyPair(keyPairCreateRequest);
	}
}
