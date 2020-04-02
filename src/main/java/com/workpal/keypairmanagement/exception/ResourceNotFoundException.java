package com.workpal.keypairmanagement.exception;

import java.util.Map;

import com.workpal.keypairmanagement.dto.ErrorResponse;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6568615042514698902L;

	private Map<String, Object> errorMap;
	private ErrorResponse errorResponse;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	public ResourceNotFoundException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	public ResourceNotFoundException(Map<String,Object> errorMap) {
		this.errorMap = errorMap;
	}
	
		
	public String getMessage() {
		return super.getMessage();
	}
	
	public ErrorResponse getErrorResponse() {
		return this.errorResponse;
	}
	
	public Map<String, Object> getErrMap() {
		return this.errorMap;
	}
}
