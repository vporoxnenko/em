package com.nsisc.emconnector.core.web.gwt.client.events;

import java.io.Serializable;
import java.util.List;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionProvider;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;



public class SelChangedEvnt<D extends DefaultClientDataModel<? extends Serializable>> extends
		SelectionChangedEvent<D> {

	public SelChangedEvnt(SelectionProvider<D> provider,
			List<D> selection) {
		super(provider, selection);
	}

}
