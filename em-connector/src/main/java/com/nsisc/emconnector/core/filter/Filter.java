package com.nsisc.emconnector.core.filter;

import java.io.Serializable;

/**
 * Filter definition interface
 * 
 * @author Valentin A. Poroxnenko
 *
 */
public interface Filter<P,V> extends Serializable{

	P getProperty();
	
	V getValue();
	
	FilterOperation getOperation();
}
