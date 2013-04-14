package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.proxy;

import com.google.gwt.user.client.ui.Widget;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityUIComponent;



public interface EnitityComponentProxy<U extends EntityUIComponent<? extends Widget>> {

	U getRealObject();
}
