package com.cpacademy.core.exception;

public class PersistenceException extends CPAException {
	private static final long serialVersionUID = -4029708234569785274L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, String exceptionKey, Object[] args) {
		super(message, exceptionKey, args);
	}
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public PersistenceException(String message, Throwable cause, String exceptionKey, Object[] args) {
		super(message, cause, exceptionKey, args);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}
}
