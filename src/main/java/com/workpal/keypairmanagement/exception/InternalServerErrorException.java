package com.workpal.keypairmanagement.exception;

public class InternalServerErrorException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		super("Internal Server Error");
	}

	public InternalServerErrorException(String msg) {
		super(msg);
	}

}
