package com.workpal.keypairmanagement.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GenerateKeyPairRequest {

	@NotEmpty(message = "Name is mandatory")
	@Size(min = 3, max = 30, message = "Name must have length of 3 to 30 characters.")
	@Pattern(regexp = "^(?=.*[a-zA-Z]{2})[a-zA-Z0-9-_]+$", message = "Only Alphabets, Numbers, Underscore and Hypen and between 3 to 30 characters.")
	private String name;

	@NotEmpty(message = "Description is mandatory")
	private String description;

	private Boolean isMapped;
}
