package com.nsisc.emconnector.core.services.support;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.nsisc.emconnector.core.exceptions.ApplicationException;
import com.nsisc.emconnector.core.services.DataService;
import com.nsisc.emconnector.model.PersistentObject;

/**
 * Abstract parent to work with entity manage services
 * 
 * @author Valentin A. Poroxnenko
 */
public abstract class AbstractDataServiceBean<K extends Serializable, T extends PersistentObject<K>, Q>
		implements DataService<K, T> {

	protected static final String SELECT_ALL_PATTERN = "select model from %s model";
	protected static final String SELECT_BY_PROPERTY_PATTERN = "select model from %s model where model.%s = ?";

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

	public List<T> findAll() {
		return executeQuery(getEntityClass(), String.format(SELECT_ALL_PATTERN,
				getEntityClass().getName()));
	}

	public List<T> findByProperty(String propertyName, Object value) {
		return executeParamQuery(getEntityClass(), String.format(
				SELECT_BY_PROPERTY_PATTERN, getEntityClass().getSimpleName(),
				propertyName), value);
	}

	public T findUniqueByProperty(String propertyName, final Object value) {
		return executeSingleResultParamQuery(getEntityClass(), String.format(
				SELECT_BY_PROPERTY_PATTERN, getEntityClass().getSimpleName(),
				propertyName), value);
	}

	public Logger getLogger() {
		return Logger.getLogger(getEntityClass().getName());
	}

	protected abstract Q createParamQuery(String queryString, Object... params);
}
