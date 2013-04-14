package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityUIComponent;

public interface EntityFilter<F extends FilterData> extends EntityUIComponent<EditorPanel>{

    F getFilterData();
    
    void setFilterData(F filterData);
    
    F instantiateFilterData();
    
    void validate() throws FormValidationException;
    
    void commit() throws FormValidationException;
    
}
