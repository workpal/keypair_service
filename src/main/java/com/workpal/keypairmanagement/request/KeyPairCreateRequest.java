package com.workpal.keypairmanagement.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "create keypair request class")
public class KeyPairCreateRequest {

	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 30, message = "Name must have length of 3 to 30 characters.")
	@Pattern(regexp = "^(?=.*[a-zA-Z]{2})[a-zA-Z0-9-_]+$", message = "Only Alphabets, Numbers, Underscore and Hypen and between 3 to 30 characters.")
	@ApiModelProperty(notes = "Name of the keypair.", example = "openssl-keypair", position = 1, required = true)
	private String name;

	@NotEmpty(message = "Description is mandatory")
	@ApiModelProperty(notes = "Description of the keypair.", example = "openssl-keypair description", position = 2, required = true)
	private String description;

	@NotEmpty(message = "Key is mandatory")
	@ApiModelProperty(notes = "Public key of the keypair.", example = "ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAH4A2KFIgLhQNmR82VQn5zGzHURA03JVzNL/U3P19hpIv3CQr1PETAj0e30/mid2ESwoYgSsYyKusD0DEeqG9joYzfdMO14zbd6nJNwmd38C+ltEI4mG2K+/7MZdJO6WXpfs6citVSy2tB6hucXemUqn441ghO88SxjrxrOCtU8=", position = 3, required = true)
	private String key;

}
