package com.workpal.keypairmanagement.domain;

import static com.workpal.keypairmanagement.enums.KeyCreationType.GENERATED;
import static com.workpal.keypairmanagement.enums.KeyCreationType.IMPORTED;
import static com.workpal.keypairmanagement.enums.KeyPairStatus.ACTIVE;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.workpal.keypairmanagement.enums.KeyCreationType;
import com.workpal.keypairmanagement.enums.KeyPairStatus;
import com.workpal.keypairmanagement.request.GenerateKeyPairRequest;
import com.workpal.keypairmanagement.request.KeyPairCreateRequest;

import lombok.Data;

@Data
@Document(value = "keyPair")
public class KeyPair {
	@Id
	private String id;
	private String name;
	private String description;
	private String publicKey;
	private KeyPairStatus status;	
	private KeyCreationType type;
	private Date createdDate;
	
	public KeyPair() {
		
	}
	
	public KeyPair(KeyPairCreateRequest keyPairCreateRequest) {
	
		this.name = keyPairCreateRequest.getName();
		this.description = keyPairCreateRequest.getDescription();
		this.publicKey = keyPairCreateRequest.getKey();
		this.type = IMPORTED;
		this.status = ACTIVE;
		this.createdDate = new Date();
		
	}
	
	public KeyPair(GenerateKeyPairRequest keyPairCreateRequest, String publicKey) {
		
		this.name = keyPairCreateRequest.getName();
		this.description = keyPairCreateRequest.getDescription();
		this.publicKey = publicKey;
		this.type = GENERATED;
		this.status = ACTIVE;
		this.createdDate = new Date();
		
	}

}
