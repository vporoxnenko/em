package com.nsisc.emconnector.core.web.gwt.client.widgets;

import com.nsisc.emconnector.core.web.gwt.client.support.ClientDataModel;

public interface EntityFieldWidget<D extends ClientDataModel> {

	D getDataModel();
	
	void setDataModel(D selectedModel);

}
