package com.nsisc.emconnector.core.web.gwt.client.exceptions;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CommitDataException extends Exception implements Serializable,
		IsSerializable {

	public CommitDataException(String message) {
		super(message);
	}
}
