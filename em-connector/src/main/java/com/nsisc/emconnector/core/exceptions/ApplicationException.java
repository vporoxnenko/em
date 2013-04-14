package com.nsisc.emconnector.core.exceptions;

/**
 * Runtime exceptions wrapper.
 * Need to control the lacks of exception processing
 * 
 * @author Valentin A. Poroxnenko
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
