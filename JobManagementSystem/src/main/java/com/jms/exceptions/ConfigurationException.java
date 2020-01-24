package com.jms.exceptions;

public class ConfigurationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String GENERIC_ERROR_CODE ="100";
	
	private String errorCode;
	
	public ConfigurationException(String message) {
		super(message);
		this.errorCode = GENERIC_ERROR_CODE;
	}
	
	public ConfigurationException(String message,String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ConfigurationException(String message,String errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
