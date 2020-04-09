package com.workpal.keypairmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	private List<Error> errors = new ArrayList<>();

	public List<Error> addError(Error error) {
		this.errors.add(error);
		return errors;
	}
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public List<Error> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return "Notification { errors=" + errors + "}";
	}
	
	
	
}
