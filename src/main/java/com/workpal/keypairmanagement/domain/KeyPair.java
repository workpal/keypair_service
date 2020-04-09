package com.workpal.keypairmanagement.domain;

import static com.workpal.keypairmanagement.enums.KeyPairStatus.ACTIVE;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.workpal.keypairmanagement.enums.KeyCreationType;
import com.workpal.keypairmanagement.enums.KeyPairStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Document(value = "keyPair")
@ApiModel(description = "Keypair domain class")
public class KeyPair {
	@Id
	@ApiModelProperty(notes = "Unique identifier of the keypair. No two keypairs can have the same id.", example = "5e02fd9161b73f3ff6c4adc2", position = 0)
	private String id;
	@ApiModelProperty(notes = "Name of the keypair.", example = "openssl-keypair", position = 1)
	private String name;
	@ApiModelProperty(notes = "Description of the keypair.", example = "openssl-keypair description", position = 2)
	private String description;
	@ApiModelProperty(notes = "Publickey of the keypair.", example = "ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAH4A2KFIgLhQNmR82VQn5zGzHURA03JVzNL/U3P19hpIv3CQr1PETAj0e30/mid2ESwoYgSsYyKusD0DEeqG9joYzfdMO14zbd6nJNwmd38C+ltEI4mG2K+/7MZdJO6WXpfs6citVSy2tB6hucXemUqn441ghO88SxjrxrOCtU8=", position = 3)
	private String publicKey;
	@ApiModelProperty(notes = "Status of the keypair.", example = "ACTIVE", position = 4)
	private KeyPairStatus status;
	@ApiModelProperty(notes = "Type of the keypair.", example = "IMPORTED", position = 5)
	private KeyCreationType type;
	@ApiModelProperty(notes = "Creation date of the keypair.", example = "2020-03-25T05:22:19.673Z", position = 6)
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
