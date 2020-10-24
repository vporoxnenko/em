package com.github.vporoxnenko.emconnector.core.services;


import com.github.vporoxnenko.emconnector.model.PersistentObject;
import java.io.Serializable;
import java.util.List;


public interface DataService<K extends Serializable, T extends PersistentObject<K>>{

	
	Class<T> getEntityClass();
	
	/**
	 * returns Entity by primary key
	 */
	T findById(K id);
	
	/**
	 * Create new empty entity.
	 * Entity is not in persistent context
	 */
	T initEntity();
	
	/**
	 * Perform an initial save of a previously unsaved entity. All subsequent
	 * persist actions of this entity should use the #update() method. This
	 * operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e.,
	 * database.
	 * 
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * Persist a previously saved entity and return it or a copy of it to the
	 * sender. A copy of the entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database.
	 * 
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * Remove entity
	 * 
	 * @param entity
	 */
	void remove(T entity);
	
	/**
	 * Remove entity by it's ID
	 * 
	 * @param id
	 */
	void removeById(K id);
	
	/**
	 * Update entity,which is NOT in persistent context(instead of update method)
	 * 
	 * @param entity
	 */
	T merge(T entity);
	
	/**
	 * Find all entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the property to query
	 * @param value
	 *            the property value to match
	 * @return List<T> found by query
	 */
	List<T> findByProperty(String propertyName, final Object value);
	
	/**
	 * Find all unique entity with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the property to query
	 * @param value
	 *            the property value to match
	 * @return T found by query
	 */
	T findUniqueByProperty(String propertyName, final Object value);
	
	/**
	 * 
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	boolean isObjectInContext(T entity);
	
	/**
	 * Run UPDATE/DELETE query
	 * 
	 * @param queryString
	 * @return
	 */
	int executeUpdate(String queryString);

	/**
	 * Run UPDATE/DELETE query with params
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	int executeParamUpdate(String queryString, Object... params);
	
	/**
	 * Run SELECT query
	 * 
	 * @param queryString
	 * @param resultClass
	 * 
	 * @return
	 */
	<R extends PersistentObject<K>> List<R> executeQuery(Class<R> resultClass, String queryString);
	
	/**
	 * Run SELECT query with params
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * 
	 * @return
	 */
	<R extends PersistentObject<K>> List<R> executeParamQuery(Class<R> resultClass, String queryString, Object... params);
	
	/**
	 * Run SELECT query with single returned result
	 * 
	 * @param queryString
	 * @param resultClass
	 * 
	 * @return
	 */
	<R extends PersistentObject<K>> R executeSingleResultQuery(Class<R> resultClass,String queryString);
	
	/**
	 * Run SELECT query with single returned result with params
	 * 
	 * @param queryString
	 * @param params
	 * @param resultClass
	 * 
	 * @return
	 */
	<R extends PersistentObject<K>> R executeSingleResultParamQuery(Class<R> resultClass,String queryString, Object... params);

}
