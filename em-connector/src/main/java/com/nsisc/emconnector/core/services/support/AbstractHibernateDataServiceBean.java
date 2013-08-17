package com.nsisc.emconnector.core.services.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nsisc.emconnector.core.exceptions.ApplicationException;
import com.nsisc.emconnector.core.filter.Filter;
import com.nsisc.emconnector.model.PersistentObject;

/**
 * Abstract parent to work with entity manage services
 * 
 * @author Valentin A. Poroxnenko
 */
public abstract class AbstractHibernateDataServiceBean<K extends Serializable, T extends PersistentObject<K>> 
extends AbstractDataServiceBean<K, T, org.hibernate.Query>{
	
	public T findById(K id) {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			T result = (T) getSession().load(getEntityClass(), id);
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public void save(T object) {
		try {
			getSession().beginTransaction();
			getSession().persist(object);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public void update(T object) {
		try {
			getSession().beginTransaction();
			getSession().refresh(object);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public void remove(T object) {
		try {
			getSession().beginTransaction();
			getSession().delete(object);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public void removeById(K id) {
		try {
			getSession().beginTransaction();
			getSession().delete(findById(id));
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public T merge(T object) {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			T result = (T) getSession().merge(object);
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	@Override
	protected Query createParamQuery(String queryString, Object... params) {
		try {
			Query result = getSession().createQuery(queryString);
			for (int i = 0; i < params.length; i++) {
				result.setParameter(i, params[i]);
			}
			return result;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public boolean isObjectInContext(T object) {
		try {
			getSession().beginTransaction();
			boolean result = getSession().contains(object); 
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public int executeUpdate(String queryString) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			int result = query.executeUpdate(); 
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public int executeParamUpdate(String queryString, Object... params) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i - 1]);
			}
			int result = query.executeUpdate(); 
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeQuery(
			Class<R> resultClass, String queryString) {
		try {
			getSession().beginTransaction();
			List<R> result = (List<R>) getSession().createQuery(
					queryString).list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> List<R> executeParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		try {
			getSession().beginTransaction();
			List<R> result = (List<R>) createParamQuery(queryString, params)
					.list();
			getSession().getTransaction().commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultQuery(
			Class<R> resultClass, String queryString) {
		try {
			getSession().beginTransaction();
			R result = (R) getSession().createQuery(queryString)
					.uniqueResult(); 
			getSession().getTransaction().commit();
			return result;
		} catch (NoResultException e) {
			getSession().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R extends PersistentObject<K>> R executeSingleResultParamQuery(
			Class<R> resultClass, String queryString, Object... params) {
		try {
			getSession().beginTransaction();
			R result = (R) createParamQuery(queryString, params)
					.uniqueResult(); 
			getSession().getTransaction().commit();
			return result;
		} catch (NoResultException e) {
			getSession().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			throw new ApplicationException(e);
		}
	}

	public Session getSession(){
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public <R extends PersistentObject<K>> List<R> executeQuery(
			Class<R> resultClass, Filter<?, ?> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R extends PersistentObject<K>> R executeSingleResultQuery(
			Class<R> resultClass, Filter<?, ?> filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected abstract SessionFactory getSessionFactory();

}
