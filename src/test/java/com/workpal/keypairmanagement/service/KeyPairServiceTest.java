package com.workpal.keypairmanagement.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.exception.KeyPairValidationException;
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

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Create key pair")
	@Test
	void testCreateKeyPair() {
		var keyPairCreateRequest = new KeyPairCreateRequest();
		keyPairCreateRequest.setName("Create key pair test");
		keyPairCreateRequest.setDescription("Create key pair test description");
		keyPairCreateRequest.setKey(
				"ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAH4A2KFIgLhQNmR82VQn5zGzHURA03JVzNL/U3P19hpIv3CQr1PETAj0e30/mid2ESwoYgSsYyKusD0DEeqG9joYzfdMO14zbd6nJNwmd38C+ltEI4mG2K+/7MZdJO6WXpfs6citVSy2tB6hucXemUqn441ghO88SxjrxrOCtU8=");

		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(new KeyPair());
		keyPairService.createKeyPair(keyPairCreateRequest);

	}

	@DisplayName("whenWrongKeyGiven_throwKeyPairValidationFailureException")
	@Test
	void testCreateKeyPairThrowException() {
		var keyPairCreateRequest = new KeyPairCreateRequest();
		keyPairCreateRequest.setName("Create key pair test");
		keyPairCreateRequest.setDescription("Create key pair test description");
		keyPairCreateRequest.setKey("ssh-rsa fdsfsdfsrreter");

		Exception exception = assertThrows(KeyPairValidationException.class, () -> {
			keyPairService.createKeyPair(keyPairCreateRequest);
		});
		assertTrue(exception.getMessage().contains("Key pair validation is failure"));
	}

	@DisplayName("Generate key pair")
	@Test
	public void testGenerateKeyPair() {
		var generateKeyPairRequest = new GenerateKeyPairRequest();
		generateKeyPairRequest.setName("Generate key pair test");
		generateKeyPairRequest.setDescription("Generate key pair test description");
		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(new KeyPair());
		keyPairService.generateKeyPair(generateKeyPairRequest);
	}

}
