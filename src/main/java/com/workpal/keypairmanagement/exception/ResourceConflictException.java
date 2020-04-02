package com.workpal.keypairmanagement.exception;

import java.util.Map;

import com.workpal.keypairmanagement.dto.ErrorResponse;

public class ResourceConflictException extends Exception {

	private static final long serialVersionUID = -8959361798133202738L;
	
	ErrorResponse errorResponse;
	
	Map<String, Object> errorMapObject;

	public ResourceConflictException(String message) {
		super(message);
	}
	
	public ResourceConflictException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ResourceConflictException(Map<String, Object> errorMapObject) {
		this.errorMapObject = errorMapObject;
	}

	public String getMessage() {
		return super.getMessage();
	}
	
	public ErrorResponse getErrorResponse() {
		return this.errorResponse;
	}
	
	public Map<String, Object> getErrMap() {
		return this.errorMapObject;
	}
	

}
