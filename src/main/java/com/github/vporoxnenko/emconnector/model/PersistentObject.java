package com.github.vporoxnenko.emconnector.model;

import java.io.Serializable;

/**
 * Interface for ALL models,which have integer primary key
 * 
 * @author Valentin A. Poroxnenko
 *
 */
public interface PersistentObject<K extends Serializable> extends Serializable{

	/**
	 * 
	 * @return primary key
	 */
	K getId();

	/**
	 * 
	 * @param id primary key
	 */
	void setId(K id);
}
