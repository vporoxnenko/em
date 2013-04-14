package com.nsisc.emconnector.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract implementation of PersistentObject. Implement default equals() and
 * hashCode()
 * 
 * @author Valentin A. Poroxnenko
 * 
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractPersistentObject<K extends Serializable>
		implements PersistentObject<K>, Serializable {

	private K id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	@Override
	public K getPrimaryKey() {
		return this.id;
	}

	@Override
	public void setPrimaryKey(K id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PersistentObject)) {
			return false;
		} else if (this.getPrimaryKey().equals(
				((PersistentObject<K>) obj).getPrimaryKey())) {
			return true;
		} else {
			return super.equals(obj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (getPrimaryKey() instanceof Number)
			return ((Number) getPrimaryKey()).intValue();
		else
			return getPrimaryKey().hashCode();
	}
}
