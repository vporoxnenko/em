package com.nsisc.emconnector.core.web.gwt.client.support;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class DefaultClientDataModel<K extends Serializable> extends BaseModel implements ClientDataModel, Serializable, IsSerializable{
	
	public static final String ID_COLUMN_NAME = "primaryKey";
	
	public void setPrimaryKey(K id){
		set(ID_COLUMN_NAME, id);
	}
	
	@SuppressWarnings("unchecked")
	public K getPrimaryKey(){
		return (K)get(ID_COLUMN_NAME);
	}
}
