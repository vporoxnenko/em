package com.nsisc.emconnector.core.web.gwt.client.events;

import java.io.Serializable;

import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;


public interface GridDoubleClickListener<D extends DefaultClientDataModel<? extends Serializable>> extends Listener<GridEvent<D>> {

}
