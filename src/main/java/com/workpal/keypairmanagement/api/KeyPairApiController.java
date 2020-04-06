package com.workpal.keypairmanagement.api;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;
import com.workpal.keypairmanagement.service.KeyPairService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController()
@CrossOrigin
@Api(value = "Keypair", description = "Set of endpoints for Creating, Retrieving of Keypairs.")
public class KeyPairApiController implements KeyPairApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairApiController.class);

	@Autowired
	private KeyPairService keyPairService;

	@Override
	@ApiOperation("Creates a new keypair.")
	public ResponseEntity<?> createKeyPair(
			@ApiParam("Request of create a new keypair ") @Valid @RequestBody KeyPairCreateRequest keyPairCreateRequest) {
		LOGGER.info("Create key pair {} ", keyPairCreateRequest);
		keyPairService.createKeyPair(keyPairCreateRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@ApiOperation("Generate a new keypair.")
	public ResponseEntity<?> generateKeyPair(
			@ApiParam("Request of generate a new keypair ") @Valid @RequestBody GenerateKeyPairRequest generateKeyPairRequest) {
		LOGGER.info("Generate key pair {} ", generateKeyPairRequest);
		var keyPair = keyPairService.generateKeyPair(generateKeyPairRequest);		
		return new ResponseEntity<>(Map.of("privateKey", keyPair), HttpStatus.OK);
	}

	@Override
	@ApiOperation("Returns list of all keypairs in the system.")
	public ResponseEntity<?> getAllKeyPair() {
		var keyPairs = keyPairService.getAllKeyPairs();
		return new ResponseEntity<>(keyPairs, HttpStatus.OK);
	}

	@Override
	@ApiOperation("Returns a specific keypair by their identifier. 404 if does not exist.")
	public ResponseEntity<?> getKeyPairById(
			@ApiParam("Id of the keypair to be obtained. Cannot be empty.") @PathVariable(value = "keyPairId") String keyPairId) {
		LOGGER.info("Key pair Id {} ", keyPairId);
		var keyPair = keyPairService.getKeyPairById(keyPairId);
		return new ResponseEntity<>(keyPair, HttpStatus.OK);
	}

}
