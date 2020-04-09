package com.workpal.keypairmanagement.exception.handler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.workpal.keypairmanagement.dto.Error;
import com.workpal.keypairmanagement.dto.ErrorResponse;
import com.workpal.keypairmanagement.dto.Notification;
import com.workpal.keypairmanagement.enums.ErrorCode;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		var notification = new Notification();		
		var bindingResult = methodArgumentNotValidException.getBindingResult();
		Map<String, Object> errorInfo = bindingResult.getFieldErrors().stream().collect(
				Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (field, message) -> field));		
		var error = Error.builder().errorCode(ErrorCode.VALIDATION_FAILURE).errorInfo(errorInfo).build();		
		notification.addError(error);
		var errorResponse = ErrorResponse.builder().errorMessages(notification.getErrors()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
