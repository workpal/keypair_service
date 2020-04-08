package com.workpal.keypairmanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workpal.keypairmanagement.domain.KeyPair;
import com.workpal.keypairmanagement.enums.KeyCreationType;
import com.workpal.keypairmanagement.exception.KeyPairValidationException;
import com.workpal.keypairmanagement.exception.ResourceNotFoundException;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;
import com.workpal.keypairmanagement.service.KeyPairService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeyPairApiControllerTest {

	private static final String RESOURCE_URL = "/keypair";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private KeyPairService keyPairService;

	@Test
	public void createKeyPair_whenEmptyRequest_thenReturnStatus400() throws Exception {
		var httpHeaders = new HttpHeaders();
		var keyPairRequest = new KeyPairCreateRequest();
		keyPairRequest.setName("");
		keyPairRequest.setDescription("");
		keyPairRequest.setKey("");
		var keyPairReq = convertToJsonString(keyPairRequest);
		mockMvc.perform(post(RESOURCE_URL + "/create").headers(httpHeaders)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(keyPairReq).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.error_messages").isArray());
	}

	@Test
	public void createKeyPair_thenReturnStatus200() throws Exception {
		var httpHeaders = new HttpHeaders();
		var keyPairRequest = new KeyPairCreateRequest();
		keyPairRequest.setName("testkeypair");
		keyPairRequest.setDescription("testkeypair");
		keyPairRequest.setKey(
				"ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAH4A2KFIgLhQNmR82VQn5zGzHURA03JVzNL/U3P19hpIv3CQr1PETAj0e30/mid2ESwoYgSsYyKusD0DEeqG9joYzfdMO14zbd6nJNwmd38C+ltEI4mG2K+/7MZdJO6WXpfs6citVSy2tB6hucXemUqn441ghO88SxjrxrOCtU8=");
		var keyPairReq = convertToJsonString(keyPairRequest);

		mockMvc.perform(post(RESOURCE_URL + "/create").headers(httpHeaders)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(keyPairReq).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void generateKeyPair_whenEmptyRequest_thenReturnStatus400() throws Exception {
		var httpHeaders = new HttpHeaders();
		var generateKeyPairReq = new GenerateKeyPairRequest();
		generateKeyPairReq.setName("");
		generateKeyPairReq.setDescription("");
		var generateKeyRequest = convertToJsonString(generateKeyPairReq);
		mockMvc.perform(post(RESOURCE_URL + "/generate").headers(httpHeaders)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(generateKeyRequest).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.error_messages").isArray());
	}

	@Test
	public void generateKeyPair__thenReturnStatus200() throws Exception {
		var httpHeaders = new HttpHeaders();
		var generateKeyPairReq = new GenerateKeyPairRequest();
		generateKeyPairReq.setName("generatekeypair");
		generateKeyPairReq.setDescription("Generate key pair test description");
		var generateKeyRequest = convertToJsonString(generateKeyPairReq);
		when(keyPairService.generateKeyPair(generateKeyPairReq)).thenReturn("sdasdqeqwe324dsad");
		mockMvc.perform(post(RESOURCE_URL + "/generate").headers(httpHeaders)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(generateKeyRequest).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAllKeyPairs__thenReturnStatus200() throws Exception {
		var httpHeaders = new HttpHeaders();
		var keyPair = new KeyPair("keypair name", "keypair description", "ssh-rsa asdas", KeyCreationType.IMPORTED);
		List<KeyPair> keyPairList = List.of(keyPair);
		when(keyPairService.getAllKeyPairs()).thenReturn(keyPairList);
		mockMvc.perform(get(RESOURCE_URL).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getKeyPairById_whenKeyPairDoesnotExists_thenReturnStatus400() throws Exception {
		String keyPairId = "sd";
		when(keyPairService.getKeyPairById(keyPairId)).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(get(RESOURCE_URL + "/{keyPairId}", keyPairId)).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void getKeyPairById__thenReturnStatus200() throws Exception {
		var keyPair = new KeyPair("keypair name", "keypair description", "ssh-rsa asdas", KeyCreationType.IMPORTED);
		when(keyPairService.getKeyPairById("5e5517d216b7bc278b05037d")).thenReturn(keyPair);
		mockMvc.perform(get(RESOURCE_URL + "/{keyPairId}", "5e5517d216b7bc278b05037d")).andDo(print())
				.andExpect(status().isOk());
	}

	private String convertToJsonString(Object request) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(request);
	}

}
