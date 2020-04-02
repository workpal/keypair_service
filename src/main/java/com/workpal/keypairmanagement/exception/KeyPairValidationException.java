package com.workpal.keypairmanagement.exception;

public class KeyPairValidationException extends RuntimeException{
	
	private static final long serialVersionUID = -4343130078649517393L;

	public KeyPairValidationException() {
		super("Key pair validation is failure");
	}

	public KeyPairValidationException(String msg) {
		super(msg);
	}

}
