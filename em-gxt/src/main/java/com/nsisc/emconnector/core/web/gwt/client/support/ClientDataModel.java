package com.nsisc.emconnector.core.web.gwt.client.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface ClientDataModel extends Serializable, IsSerializable {

	<X> X get(String property);

	Map<String, Object> getProperties();

	Collection<String> getPropertyNames();

	<X> X remove(String property);

	<X> X set(String property, X value);
}
