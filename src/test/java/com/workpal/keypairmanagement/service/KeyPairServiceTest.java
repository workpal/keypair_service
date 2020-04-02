package com.workpal.keypairmanagement.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.repository.KeyPairRepository;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;
import com.workpal.keypairmanagement.service.Impl.KeyPairServiceImpl;

@SpringBootTest

public class KeyPairServiceTest {

	@Mock
	private KeyPairRepository keyPairRepo;

	@InjectMocks
	private KeyPairService keyPairService = new KeyPairServiceImpl();

	@DisplayName("Create key pair")
	@Test
	void testCreateKeyPair() {
		var keyPairCreateRequest = new KeyPairCreateRequest();
		keyPairCreateRequest.setName("Create key pair test");
		keyPairCreateRequest.setDescription("Create key pair test description");
		keyPairCreateRequest.setKey(
				"AAAAB3NzaC1yc2EAAAADAQABAAAAgQDz0OVfMEQMk fKPDL8c25HBM27Rum12e5G6td+CW/3NEW4dvQk/fC5i4zdV/ Zg8R5zY12DBdjiUqCkZa/EPZzC9Mef+4IgyIlkJdN/Av1zCDb x6QfEhGHd5PufI44NBkPJdi5kTjCL25TWHe00KQ3hF6ui45w QPhFMyIP/sQjqYQ==");

		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(new KeyPair(keyPairCreateRequest));
		keyPairService.createKeyPair(keyPairCreateRequest);

	}

	@DisplayName("Generate Key pair")
	@Test
	public void testGenerateKeyPair() {
		var generateKeyPairRequest = new GenerateKeyPairRequest();
		generateKeyPairRequest.setName("Generate key pair test");
		generateKeyPairRequest.setDescription("Generate key pair test description");
		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(new KeyPair());
		keyPairService.generateKeyPair(generateKeyPairRequest);
	}

}
