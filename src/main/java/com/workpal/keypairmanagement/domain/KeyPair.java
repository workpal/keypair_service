package com.workpal.keypairmanagement.domain;

import static com.workpal.keypairmanagement.enums.KeyPairStatus.ACTIVE;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.workpal.keypairmanagement.enums.KeyCreationType;
import com.workpal.keypairmanagement.enums.KeyPairStatus;

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

	public KeyPair(String name, String description, String key, KeyCreationType type) {
		this.name = name;
		this.description = description;
		this.publicKey = key;
		this.type = type;
		this.status = ACTIVE;
		this.createdDate = new Date();
	}

}
