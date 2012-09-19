package com.goldenpond.stampbook.exception;

public class StampBookException extends RuntimeException {

	public StampBookException(Exception e) {
		super(e);
	}

	public StampBookException(String message) {
		super(message);
	}

}
