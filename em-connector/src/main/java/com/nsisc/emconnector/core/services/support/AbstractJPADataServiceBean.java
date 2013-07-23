package com.nsisc.emconnector.core.services.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.nsisc.emconnector.core.exceptions.ApplicationException;
import com.nsisc.emconnector.model.PersistentObject;

/**
 * Abstract parent to work with entity manage services
 * 
 * @author Valentin A. Poroxnenko
 */
public abstract class AbstractJPADataServiceBean<K extends Serializable, T extends PersistentObject<K>>
		extends AbstractDataServiceBean<K, T, javax.persistence.Query> {

	public T findById(K id) {
		try {
			return getEntityManager().find(getEntityClass(), id);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public void save(T object) {
		try {
			getEntityManager().persist(object);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public void update(T object) {
		try {
			getEntityManager().refresh(object);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public void remove(T object) {
		try {
			getEntityManager().remove(object);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public void removeById(K id) {
		try {
			getEntityManager().remove(findById(id));
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public T merge(T object) {
		try {
			return getEntityManager().merge(object);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	protected Query createParamQuery(String queryString, Object... params) {
		try {
			Query result = getEntityManager().createQuery(queryString);
			for (int i = 1; i <= params.length; i++) {
				result.setParameter(i, params[i - 1]);
			}
			return result;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public boolean isObjectInContext(T object) {
		try {
			return getEntityManager().contains(object);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public int executeUpdate(String queryString) {
		try {
			Query query = getEntityManager().createQuery(queryString);
			return query.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public int executeParamUpdate(String queryString, Object... params) {
		try {
			Query query = getEntityManager().createQuery(queryString);
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i - 1]);
			}
			return query.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeQuery(
			Class<R> resultClass, String queryString) {
		try {
			List<R> result = (List<R>) getEntityManager().createQuery(
					queryString).getResultList();
			return result;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		try {
			List<R> result = (List<R>) createParamQuery(queryString, params)
					.getResultList();
			return result;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultQuery(
			Class<R> resultClass, String queryString) {
		try {
			return (R) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		try {
			return (R) createParamQuery(queryString, params)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public abstract EntityManager getEntityManager();
}
