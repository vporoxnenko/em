package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.proxy;

import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityFilter;

public abstract class AbstractEntityFilterProxy<F extends FilterData>
	implements EntityFilter<F> {

    private EntityFilter<F> filter;

    public EntityFilter<F> getRealObject() {
	if (filter == null)
	    filter = instantiateFilter();
	return filter;
    }

    public F getFilterData() {
	return getRealObject().getFilterData();
    }

    public void setFilterData(F filterData) {
	getRealObject().setFilterData(filterData);
    }

    public void validate() throws FormValidationException{
	getRealObject().validate();
    }
    
    public EditorPanel getUIComponent(){
	return getRealObject().getUIComponent();
    }
    
    public F instantiateFilterData(){
    	return getRealObject().instantiateFilterData();
    }
    
    public void commit() throws FormValidationException {
	getRealObject().commit();
    }

    public int getHeight() {
	return getRealObject().getHeight();
    }

    public String getTitle() {
	return getRealObject().getTitle();
    }

    public int getWidth() {
	return 	getRealObject().getWidth();
    }
    
    public abstract EntityFilter<F> instantiateFilter();

}
