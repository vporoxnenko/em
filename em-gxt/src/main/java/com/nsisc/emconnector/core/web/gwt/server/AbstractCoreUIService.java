package com.nsisc.emconnector.core.web.gwt.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nsisc.emconnector.core.services.DataService;
import com.nsisc.emconnector.core.web.gwt.client.CoreUIService;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.ServerException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingConfig;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingResult;
import com.nsisc.emconnector.model.AbstractPersistentObject;

/**
 * Abstract parent class for GWT RPC implementors
 * 
 * @author Valentin A. Poroxnenko
 * 
 * @param <D>
 * @param <P>
 */
public abstract class AbstractCoreUIService<K extends Serializable, P extends AbstractPersistentObject<K>, D extends DefaultClientDataModel<K>, F extends FilterData>
		extends RemoteServiceServlet implements CoreUIService<K, D, F> {

	public void saveDataModel(D dataModel) throws ServerException {
		try {
			if (dataModel.getPrimaryKey() == null)
				getService().save(
						internalConvertToPersistentObject(null, dataModel));
			else {
				// some fields may be uneditable, but on client side we have an
				// ability to
				// hack JS code and post them
				P po = getService().findById(dataModel.getPrimaryKey());
				getService().merge(
						internalConvertToPersistentObject(po, dataModel));
			}
		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		}
	}

	public void deleteDataModel(K id) throws ServerException {
		try {
			getService().removeById(id);
		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		}
	}

	public List<D> getDataModels(F filterData) throws ServerException {
		try {
			// TODO: implement filter
			List<P> entities = getService().findAll();
			List<D> models = new ArrayList<D>(entities.size());
			for (P en : entities)
				models.add(internalConvertToGridModel(en));
			return models;
		} catch (Exception ex) {
			throw new ServerException(ex);
		}
	}

	public D getDataModelById(K entityId) throws ServerException {
		try {
			P entity = getService().findById(entityId);
			return entity != null ? internalConvertToGridModel(entity) : null;
		} catch (Exception ex) {
			throw new ServerException(ex);
		}
	}

	public GridDataFetchingResult<D> getPagedDataModels(
			GridDataFetchingConfig gridDataFetchingConfig, F filterData)
			throws ServerException {
		List<D> models = getDataModels(filterData);

		if (gridDataFetchingConfig.getSortFieldName() != null) {
			final String sortField = gridDataFetchingConfig.getSortFieldName();
			if (sortField != null) {
				/*
				 * Collections.sort(models, gridDataFetchingConfig.getSortDir()
				 * .comparator(new Comparator() { public int compare(Object o1,
				 * Object o2) { return compareModels((D) o1, (D) o2); } }));
				 */
			}
		}

		ArrayList<D> sublist = new ArrayList<D>();
		int start = gridDataFetchingConfig.getOffset();
		int limit = models.size();
		if (gridDataFetchingConfig.getLimit() > 0) {
			limit = Math.min(start + gridDataFetchingConfig.getLimit(), limit);
		}
		for (int i = gridDataFetchingConfig.getOffset(); i < limit; i++) {
			sublist.add(models.get(i));
		}
		return new GridDataFetchingResult<D>(sublist,
				gridDataFetchingConfig.getOffset(), models.size());
	}

	private D internalConvertToGridModel(P entity) {
		D result = convertToGridModel(entity);
		result.setPrimaryKey(entity.getPrimaryKey());
		return result;
	}

	private P internalConvertToPersistentObject(P base, D dataModel) {
		P result = convertToPersistentObject(base, dataModel);
		result.setPrimaryKey(dataModel.getPrimaryKey());
		return result;
	}

	public D convertToGridModel(P data) {
		return ModelConverterUtil.convertToClientDataModel(
				getClientModelClass(), data, true);
	}

	public P convertToPersistentObject(P base, D data) {
		return ModelConverterUtil.convertToPersistenceObject(
				getPersistentObjectClass(), base, data, true);
	}

	public abstract String getServiceName();

	public abstract int compareModels(D o1, D o2);

	public abstract DataService<K, P> getService();

	public abstract Class<P> getPersistentObjectClass();

	public abstract Class<D> getClientModelClass();

}
