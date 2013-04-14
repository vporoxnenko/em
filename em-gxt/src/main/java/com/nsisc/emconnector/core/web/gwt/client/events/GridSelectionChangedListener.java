package com.nsisc.emconnector.core.web.gwt.client.events;

import java.io.Serializable;

import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;


public interface GridSelectionChangedListener<D extends DefaultClientDataModel<? extends Serializable>> {

	void selectionChanged(SelChangedEvnt<D> event);
}
