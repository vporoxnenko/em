package com.nsisc.emconnector.core.web.gwt.client.exceptions;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ServerException extends Exception implements Serializable, IsSerializable{

	public ServerException() {
		super();
	}
	
	public ServerException(String message) {
		super(message);
	}

	public ServerException(Exception ex) {
		super(ex);
	}
	
}
