package com.github.vporoxnenko.emconnector.core.services.support;

import com.github.vporoxnenko.emconnector.core.exceptions.ApplicationException;
import com.github.vporoxnenko.emconnector.core.services.DataService;
import com.github.vporoxnenko.emconnector.model.PersistentObject;
import java.io.Serializable;
import java.util.List;

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
			return getEntityClass().getDeclaredConstructor().newInstance();
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

	protected abstract Q createParamQuery(String queryString, Object... params);
}
