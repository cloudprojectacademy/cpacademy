package com.cpacademy.core.exception;

public class ProcessingException extends CPAException {

	private static final long serialVersionUID = -4029708234569785274L;

	public ProcessingException() {
		super();
	}

	public ProcessingException(String message) {
		super(message);
	}

	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessingException(String message, Throwable cause, String i18nKey, Object[] args) {
		super(message, cause, i18nKey, args);
	}

	public ProcessingException(Throwable cause) {
		super(cause);
	}
}
