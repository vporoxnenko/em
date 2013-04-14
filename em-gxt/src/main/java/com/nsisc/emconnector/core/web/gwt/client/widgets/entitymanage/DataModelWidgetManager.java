package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import java.util.List;


public interface DataModelWidgetManager {
	
	void disableComponents(boolean isDisabled);
	
	boolean isComponentsDisabled();
	
	@SuppressWarnings("rawtypes")
	List<EntityField> getEntityFields();
	
}
