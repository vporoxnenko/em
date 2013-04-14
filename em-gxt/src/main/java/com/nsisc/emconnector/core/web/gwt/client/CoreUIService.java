package com.nsisc.emconnector.core.web.gwt.client;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.ServerException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingConfig;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingResult;


/**
 * GWT RPC Remote service with basic CRUD methods to delegate to EJB
 * 
 * @author Valenton A. Poroxnenko
 *
 * @param <D>
 */
public interface CoreUIService<K extends Serializable, D extends DefaultClientDataModel<K>, F extends FilterData> extends RemoteService {
	
	void saveDataModel(D datModel) throws ServerException;
	
	void deleteDataModel(K id) throws ServerException;
	
	List<D> getDataModels(F filterData) throws ServerException;
	
	D getDataModelById(K entityId) throws ServerException;
	
	GridDataFetchingResult<D> getPagedDataModels(final GridDataFetchingConfig gridDataFetchingConfig, F filterData) throws ServerException;

}
