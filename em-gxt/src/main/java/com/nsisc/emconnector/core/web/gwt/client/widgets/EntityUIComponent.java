package com.nsisc.emconnector.core.web.gwt.client.widgets;

import com.google.gwt.user.client.ui.Widget;

public interface EntityUIComponent<V extends Widget> {

	V getUIComponent();
	
	int getHeight();
	
	int getWidth();
	
	String getTitle();
}
