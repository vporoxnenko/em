package com.nsisc.emconnector.core.web.gwt.client.events;

import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;

public abstract class FileSubmitListener implements Listener<FormEvent>{

	@Override
	public void handleEvent(FormEvent be) {
		handleEvent(be.getResultHtml());
	}

	public abstract void handleEvent(String response);
}
