package com.nsisc.emconnector.core.exceptions;

/**
 * @author Valentin A. Poroxnenko
 */
@SuppressWarnings("serial")
public class ServiceLocateException extends ApplicationException {

	public ServiceLocateException(String message) {
		super(message);
	}
	
	public ServiceLocateException(String message, Throwable cause) {
		super(message, cause);
	}
}
