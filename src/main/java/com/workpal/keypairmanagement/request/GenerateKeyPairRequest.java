package com.workpal.keypairmanagement.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Generate keypair request class")
public class GenerateKeyPairRequest {

	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 30, message = "Name must have length of 3 to 30 characters.")
	@Pattern(regexp = "^(?=.*[a-zA-Z]{2})[a-zA-Z0-9-_]+$", message = "Only Alphabets, Numbers, Underscore and Hypen and between 3 to 30 characters.")
	@ApiModelProperty(notes = "Name of the keypair.", example = "openssl-keypair", position = 1, required = true)
	private String name;

	@NotEmpty(message = "Description is mandatory")
	@ApiModelProperty(notes = "Description of the keypair.", example = "openssl-keypair description", position = 2, required = true)
	private String description;

}
