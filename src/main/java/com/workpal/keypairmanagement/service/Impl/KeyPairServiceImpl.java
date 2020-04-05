package com.workpal.keypairmanagement.service.Impl;

import static com.workpal.keypairmanagement.enums.KeyCreationType.GENERATED;
import static com.workpal.keypairmanagement.enums.KeyCreationType.IMPORTED;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.crypto.util.OpenSSHPublicKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.exception.InternalServerErrorException;
import com.workpal.keypairmanagement.exception.KeyPairValidationException;
import com.workpal.keypairmanagement.exception.ResourceNotFoundException;
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
	public void createKeyPair(KeyPairCreateRequest keyPairCreateRequest) {
		ValidateKeyPair(keyPairCreateRequest.getKey().strip());

		var keyPair = new KeyPair(keyPairCreateRequest.getName(), keyPairCreateRequest.getDescription(),
				keyPairCreateRequest.getKey(), IMPORTED);
		LOGGER.info("Create keypair {} ", keyPair);
		keyPairRepository.save(keyPair);
	}

	@Override
	public com.jcraft.jsch.KeyPair generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest) {
		var keyPairMap = generateKeyPair();
		String publicKey = keyPairMap.get("publicKey").toString();
		var privateKey = (com.jcraft.jsch.KeyPair) keyPairMap.get("privateKey");
		var keyPair = new KeyPair(generateKeyPairRequest.getName(), generateKeyPairRequest.getDescription(), publicKey,
				GENERATED);
		LOGGER.info("Generate keypair {} ", keyPair);
		keyPairRepository.save(keyPair);
		return privateKey;
	}

	private Map<String, Object> generateKeyPair() {
		Map<String,Object> keyPairMap = new HashMap<String, Object>();
		String publicKeyContent = null;
		try {
			String comment = "ci-key-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			JSch jsch = new JSch();

			com.jcraft.jsch.KeyPair kpair = com.jcraft.jsch.KeyPair.genKeyPair(jsch, com.jcraft.jsch.KeyPair.RSA, 2048);

			byte[] pubblob = kpair.getPublicKeyBlob();
			byte[] pub = Base64.getEncoder().encode(pubblob);

			publicKeyContent = new String(pub, StandardCharsets.UTF_8);
			publicKeyContent = "ssh-rsa " + publicKeyContent + " " + comment;
			keyPairMap.put("privateKey", kpair);
			keyPairMap.put("publicKey", publicKeyContent);
			kpair.dispose();

		} catch (Exception e) {
			LOGGER.error("Error in keypair generation {}", e);
			throw new InternalServerErrorException("Internal Server Error");
		}

		return keyPairMap;
	}

	private void ValidateKeyPair(String key) {
		LOGGER.info("Key validation starts");
		String[] replacedString = null;
		if (key.startsWith("ssh-rsa ")) {
			replacedString = key.split("ssh-rsa ", 0);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < replacedString.length; i++) {
			sb.append(replacedString[i]);
		}
		String keyString = sb.toString();
		try {
			OpenSSHPublicKeyUtil.parsePublicKey(org.bouncycastle.util.encoders.Base64.decode(keyString));
		} catch (Exception e) {
			LOGGER.info("Validation failure - {} ", e.getMessage());
			var errMsg = String.format("Key pair validation is failure");
			LOGGER.info(errMsg);
			throw new KeyPairValidationException(errMsg);
		}
	}

	@Override
	public List<KeyPair> getAllKeyPairs() {
		LOGGER.info("Get all keypairs");
		return keyPairRepository.findAll();
	}

	@Override
	public KeyPair getKeyPairById(String keyPairId) {
		LOGGER.info("Get keypair by ID {} ", keyPairId);
		var keyPair = keyPairRepository.findById(keyPairId).orElseThrow(() -> {
			var errMsg = String.format("Keypair doesn't exists with : %s", keyPairId);
			LOGGER.error(errMsg);
			return new ResourceNotFoundException(errMsg);
		});	
		return keyPair;
	}

}
