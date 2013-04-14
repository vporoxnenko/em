package com.nsisc.emconnector.core.web.gwt.client;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingConfig;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingResult;


public interface CoreUIServiceAsync<K extends Serializable,D extends DefaultClientDataModel<K>, F extends FilterData> {

	void saveDataModel(D datModel, AsyncCallback<Void> callback);

	void deleteDataModel(K id, AsyncCallback<Void> callback);

	void getDataModels(F filterData, AsyncCallback<List<D>> callback);
	
	void getDataModelById(K id, AsyncCallback<D> callback);
	
	void getPagedDataModels(final GridDataFetchingConfig gridDataFetchingConfig, F filterData, AsyncCallback<GridDataFetchingResult<D>> asyncCallback);
	
}
