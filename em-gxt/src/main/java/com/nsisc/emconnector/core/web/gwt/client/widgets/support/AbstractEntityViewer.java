package com.nsisc.emconnector.core.web.gwt.client.widgets.support;

import java.io.Serializable;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nsisc.emconnector.core.web.gwt.client.CoreUIServiceAsync;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.ServerException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.AlertDialog;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityViewer;


public abstract class AbstractEntityViewer<K extends Serializable,D extends DefaultClientDataModel<K>, F extends FilterData>
		implements EntityViewer<UIContainer, D> {

	private D dataModel;
	private CoreUIServiceAsync<K, D,F> serviceAsync;
	private K entityId;
	private UIContainer component;

	@SuppressWarnings("unchecked")
	public AbstractEntityViewer(K entityId, Object serviceAsync) throws ServerException {
		this.serviceAsync = (CoreUIServiceAsync<K, D,F>) serviceAsync;
		this.entityId = entityId;
		createBaseUIComponent();
		loadModel();
	}
	
	public AbstractEntityViewer(D dm) {	
		dataModel = dm;
		createBaseUIComponent();
		if (dm != null)
			createUIContent();
	}
	
	public AbstractEntityViewer() {
		this(null);
	}

	public void createBaseUIComponent() {
		component = new UIContainer();
		component.setHeading(getTitle());
		component.setLayout(new FlowLayout());
	}

	private void loadModel() throws ServerException {
		this.serviceAsync.getDataModelById(entityId, new AsyncCallback<D>() {
			public void onFailure(Throwable th) {
				AlertDialog.showError("Loading model error", th);
			}

			public void onSuccess(D result) {
				dataModel = result;
				createUIContent();
			}
			
		});
	}

	public UIContainer getUIComponent() {
		return component;
	}

	public D getDataModel() {
		return dataModel;
	}

	public void setDataModel(D dataModel) {
		this.dataModel = dataModel;
	}

}
