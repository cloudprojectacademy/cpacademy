package com.cpacademy.core.cpa.exception;

import java.io.Serializable;

abstract public class CPAException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = -4029708234569785274L;

	private Object[] args;
	private String exceptionKey;
		
	public CPAException() {
		this(null, null, null, null);
	}

	public CPAException(Throwable cause) {
		this(null, cause, null, null);
	}

	public CPAException(String message) {
		this(message, null, null, null);
	}

	public CPAException(String message, String exceptionKey, Object[] args) {
		this(message, null, exceptionKey, args);
	}
	
	public CPAException(String message, Throwable cause) {
		this(message, cause, null, null);
	}

	public CPAException(String message, Throwable cause, String exceptionKey, Object[] args) {
		super(message);
		if (cause != null)	this.initCause(cause);
		if(exceptionKey != null && exceptionKey.length() > 0) this.exceptionKey = exceptionKey;
		this.args = args;
	}
	
	
	public String getExceptionKey() {
		return exceptionKey;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}	
}