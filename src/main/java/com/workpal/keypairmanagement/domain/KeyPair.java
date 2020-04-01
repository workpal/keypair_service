package com.workpal.keypairmanagement.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.workpal.keypairmanagement.enums.KeyCreationType;
import com.workpal.keypairmanagement.enums.KeyPairStatus;
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
	
	public KeyPair(KeyPairCreateRequest keyPairCreateRequest) {
	
		this.name = keyPairCreateRequest.getName();
		this.description = keyPairCreateRequest.getDescription();
		this.publicKey = keyPairCreateRequest.getKey();
		this.type = KeyCreationType.IMPORTED;
		this.status = KeyPairStatus.ACTIVE;
		this.createdDate = new Date();
		
	}

}
