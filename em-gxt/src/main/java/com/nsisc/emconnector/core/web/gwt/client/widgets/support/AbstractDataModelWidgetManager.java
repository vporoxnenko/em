package com.nsisc.emconnector.core.web.gwt.client.widgets.support;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityField;


public abstract class AbstractDataModelWidgetManager implements DataModelWidgetManager {

	private boolean isDisabled = false;

	@SuppressWarnings("rawtypes")
	private List<EntityField> fields; 

	public AbstractDataModelWidgetManager(){
		this.fields = initEntityFields();
	}

	private void disablingWidgets() {
	    for (EntityField<? extends Serializable, ? extends Widget> ef : fields)
	    	ef.setEnabled((!isDisabled && ef.isEnabled()));
	}

	public void disableComponents(boolean isDisabled){
	    this.isDisabled = isDisabled;
	    disablingWidgets();
	}
	
	public boolean isComponentsDisabled(){
	    return isDisabled;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<EntityField> getEntityFields() {
		return fields;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract List<EntityField> initEntityFields();
}
