package com.nsisc.emconnector.core.web.gwt.server.debug;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import com.nsisc.emconnector.core.exceptions.ApplicationException;
import com.nsisc.emconnector.core.services.DataService;
import com.nsisc.emconnector.model.PersistentObject;

public abstract class DebugAbstractDataService<K extends Serializable, T extends PersistentObject<K>>
		implements DataService<K, T> {

	public static interface DebugPKGenerator<K>{
		
		K getnextValue();
		
	}
	
	private ConcurrentMap<K,T> storage = new ConcurrentHashMap<K, T>();
	private DebugPKGenerator<K> generator;
	
	public DebugAbstractDataService(DebugPKGenerator<K> generator){
		this.generator = generator;		
	}
	
	@Override
	public String getServiceName() {
		return this.getClass().getName();
	}

	@Override
	public T findById(K id) {
		return storage.get(id);
	}

	@Override
	public T initEntity() {
		try {
			return getEntityClass().newInstance();
		} catch (InstantiationException e) {
			throw new ApplicationException("Couldn't instantiate entity "
					+ getEntityClass(), e.getCause());
		} catch (IllegalAccessException e) {
			throw new ApplicationException("Couldn't instantiate entity "
					+ getEntityClass(), e.getCause());
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void save(T entity) {
		if(entity.getPrimaryKey() == null)
			entity.setPrimaryKey(generator.getnextValue());
		storage.put(entity.getPrimaryKey(), entity);
	}

	@Override
	public void update(T entity) {
		remove(entity);
		save(entity);
	}

	@Override
	public void remove(T entity) {
		storage.remove(entity.getPrimaryKey());
	}

	@Override
	public void removeById(K id) {
		storage.remove(id);
	}

	@Override
	public T merge(T entity) {
		return entity;
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T findUniqueByProperty(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll() {
		return new ArrayList<T>(storage.values());
	}

	@Override
	public boolean isObjectInContext(T entity) {
		return true;
	}

	@Override
	public int executeUpdate(String queryString) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeParamUpdate(String queryString, Object... params) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <R extends PersistentObject<K>> List<R> executeQuery(
			Class<R> resultClass, String queryString) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <R extends PersistentObject<K>> List<R> executeParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <R extends PersistentObject<K>> R executeSingleResultQuery(
			Class<R> resultClass, String queryString) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <R extends PersistentObject<K>> R executeSingleResultParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getLogger() {
		return Logger.getLogger(getEntityClass().getName());
	}

}
