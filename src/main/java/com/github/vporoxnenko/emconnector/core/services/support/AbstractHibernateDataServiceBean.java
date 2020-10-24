package com.github.vporoxnenko.emconnector.core.services.support;

import com.github.vporoxnenko.emconnector.model.PersistentObject;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Abstract parent to work with entity manage services
 * 
 * @author Valentin A. Poroxnenko
 */
public abstract class AbstractHibernateDataServiceBean<K extends Serializable, T extends PersistentObject<K>>
		extends AbstractDataServiceBean<K, T, org.hibernate.Query> {

	public T findById(K id) {
		@SuppressWarnings("unchecked")
		T result = (T) getSession().get(getEntityClass(), id);

		return result;
	}

	public void save(T object) {
		getSession().persist(object);
	}

	public void update(T object) {
		getSession().refresh(object);
	}

	public void remove(T object) {
		getSession().delete(object);
	}

	public void removeById(K id) {
		getSession().delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	public T merge(T object) {
		return (T) getSession().merge(object);
	}

	@Override
	protected Query createParamQuery(String queryString, Object... params) {
		Query result = getSession().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			result.setParameter(i, params[i]);
		}
		return result;
	}

	public boolean isObjectInContext(T object) {
		return getSession().contains(object);
	}

	public int executeUpdate(String queryString) {
		Query query = getSession().createQuery(queryString);
		return query.executeUpdate();
	}

	public int executeParamUpdate(String queryString, Object... params) {
		Query query = getSession().createQuery(queryString);
		for (int i = 1; i <= params.length; i++) {
			query.setParameter(i, params[i - 1]);
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeQuery(
			Class<R> resultClass, String queryString) {
		return (List<R>) getSession().createQuery(queryString).list();
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		return (List<R>) createParamQuery(queryString, params).list();
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultQuery(
			Class<R> resultClass, String queryString) {
		return (R) getSession().createQuery(queryString).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		return (R) createParamQuery(queryString, params).uniqueResult();
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected abstract SessionFactory getSessionFactory();

}
