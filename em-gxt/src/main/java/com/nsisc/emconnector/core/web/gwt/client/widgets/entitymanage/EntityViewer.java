package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Widget;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityUIComponent;




public interface EntityViewer<V extends Widget, D extends DefaultClientDataModel<? extends Serializable>> extends EntityUIComponent<V>{

	D getDataModel();
	
	void setDataModel(D entity);
	
	void createBaseUIComponent();

	V getUIComponent();
	
	String getTitle();
	
	void createUIContent();
	
}
