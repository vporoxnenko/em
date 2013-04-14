package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.proxy;

import java.io.Serializable;

import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityViewer;

public abstract class AbstractEntityViewerProxy<D extends DefaultClientDataModel<? extends Serializable>>
		implements EntityViewer<UIContainer, D> {

	private EntityViewer<UIContainer, D> viewer;
	private D dataModel;
	
	
	public void createBaseUIComponent() {
		getRealObject().createBaseUIComponent();
	}
	
	public UIContainer getUIComponent() {
		return getRealObject().getUIComponent();
	}

	public D getDataModel() {
		return dataModel;
	}

	public void setDataModel(D dataModel) {
		this.dataModel = dataModel;
	}

	public EntityViewer<UIContainer, D> getRealObject() {
		if (viewer == null) {
			viewer = instantiateViewer();
		}
		viewer.setDataModel(dataModel);
		return viewer;
	}
	
	public String getTitle() {
		return getRealObject().getTitle();
	}
	
	public int getHeight() {
		return getRealObject().getHeight();
	}

	public int getWidth() {
		return getRealObject().getWidth();
	}
		
	public void createUIContent() {
		getRealObject().createUIContent();	
	}
	public abstract EntityViewer<UIContainer, D> instantiateViewer();

}
