package com.github.vporoxnenko.emconnector.core.exceptions;

/**
 * User exceptions base class
 * 
 * @author Valentin A. Poroxnenko
 */
@SuppressWarnings("serial")
public class UserException extends Exception {

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

}
