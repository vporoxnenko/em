package com.nsisc.emconnector.core.web.gwt.client.exceptions;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FormValidationException extends Exception implements Serializable,
		IsSerializable {

	public FormValidationException() {
		super();
	}
	
	public FormValidationException(String message) {
		super(message);
	}
}
