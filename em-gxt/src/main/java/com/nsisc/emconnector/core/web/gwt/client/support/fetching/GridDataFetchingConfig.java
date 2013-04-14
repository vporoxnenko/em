package com.nsisc.emconnector.core.web.gwt.client.support.fetching;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GridDataFetchingConfig implements Serializable, IsSerializable {

	private String sortFieldName;
	private int offset;
	private int limit;
	
	public String getSortFieldName() {
		return sortFieldName;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
