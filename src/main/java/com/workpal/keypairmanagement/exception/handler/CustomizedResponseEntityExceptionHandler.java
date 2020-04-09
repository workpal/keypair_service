package com.workpal.keypairmanagement.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.workpal.keypairmanagement.dto.Error;
import com.workpal.keypairmanagement.dto.ErrorResponse;
import com.workpal.keypairmanagement.enums.ErrorCode;
import com.workpal.keypairmanagement.exception.InternalServerErrorException;
import com.workpal.keypairmanagement.exception.KeyPairValidationException;
import com.workpal.keypairmanagement.exception.ResourceConflictException;
import com.workpal.keypairmanagement.exception.ResourceDuplicationException;
import com.workpal.keypairmanagement.exception.ResourceNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {

		if (ex.getErrMap() != null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getErrMap());
		}
		var errors = new ArrayList<Error>();
		var errInfoMap = new HashMap<String, Object>();
		errInfoMap.put("name", ex.getMessage());
		var error = Error.builder().errorCode(ErrorCode.RESOURCE_NOT_FOUND).errorInfo(errInfoMap).build();
		errors.add(error);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().errorMessages(errors).build());
	}

	

	@ExceptionHandler(ResourceDuplicationException.class)
	ResponseEntity<?> handleDuplicateException(ResourceDuplicationException ex) {
		var errors = new ArrayList<Error>();
		var errInfoMap = new HashMap<String, Object>();
		errInfoMap.put("name", ex.getMessage());
		var error = Error.builder().errorCode(ErrorCode.DUPLICATE_NAME).errorInfo(errInfoMap).build();
		errors.add(error);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder().errorMessages(errors).build());
	}

	

	@ExceptionHandler(ResourceConflictException.class)
	ResponseEntity<?> handleResourceConflictException(ResourceConflictException ex) {
		if (ex.getErrMap() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getErrMap());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getErrorResponse());
	}

	

	@ExceptionHandler(InternalServerErrorException.class)
	ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
		System.err.println("Error : " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	

	@ExceptionHandler(MissingServletRequestParameterException.class)
	ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	@ExceptionHandler(KeyPairValidationException.class)
	ResponseEntity<?> handleKeyPairValidationException(KeyPairValidationException ex) {
		var errors = new ArrayList<Error>();
		var errInfoMap = new HashMap<String, Object>();
		errInfoMap.put("name", ex.getMessage());
		var error = Error.builder().errorCode(ErrorCode.NOT_VALID_KEY).errorInfo(errInfoMap).build();
		errors.add(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.builder().errorMessages(errors).build());
	}
}