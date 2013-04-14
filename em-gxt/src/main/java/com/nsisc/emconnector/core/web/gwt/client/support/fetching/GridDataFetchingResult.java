package com.nsisc.emconnector.core.web.gwt.client.support.fetching;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;


public class GridDataFetchingResult<D extends DefaultClientDataModel<? extends Serializable>> implements Serializable, IsSerializable {

	private List<D> data;
	private int offset;
	private int limit;

	public GridDataFetchingResult(){
		
	}
	
	public GridDataFetchingResult(List<D> data, int offset, int limit){
		this.data = data;
		this.offset = offset;
		this.limit = limit;
	}
	
	public List<D> getData() {
		return data;
	}
	
	public void setData(List<D> data) {
		this.data = data;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}


}
