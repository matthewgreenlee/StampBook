package com.goldenpond.stampbook.exception;

@SuppressWarnings("serial")
public class StampBookException extends RuntimeException {

	public StampBookException(Exception e) {
		super(e);
	}

	public StampBookException(String message) {
		super(message);
	}

}
