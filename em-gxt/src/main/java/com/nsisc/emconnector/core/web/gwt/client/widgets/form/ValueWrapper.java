package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class ValueWrapper<T extends Serializable> extends BaseModel {

	public static final String VALUE_KEY = "_value_";
	
	public ValueWrapper(T s) {
		set(VALUE_KEY, s);
	}
	
	@SuppressWarnings("unchecked")
	public T getBaseValue(){
		return (T)get(VALUE_KEY);
	}
}
