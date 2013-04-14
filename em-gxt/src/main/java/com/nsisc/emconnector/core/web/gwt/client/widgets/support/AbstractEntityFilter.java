package com.nsisc.emconnector.core.web.gwt.client.widgets.support;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityFilter;


public abstract class AbstractEntityFilter<F extends FilterData> implements
		EntityFilter<F> {

	private EditorPanel component;
	private F filterData;

	public AbstractEntityFilter() {
		createBaseUIComponent();
	}

	private void createBaseUIComponent() {
		component = new EditorPanel();
		component.setFrame(true);
		component.setHeading(getTitle());
		component.setLayout(new FlowLayout());

		createUIContent(component);

	}

	public F getFilterData() {
		if (filterData == null)
			filterData = instantiateFilterData();
		return filterData;
	}

	public void setFilterData(F filterData) {
		this.filterData = filterData;
	}

	public EditorPanel getUIComponent() {
		return component;
	}

	public void commit() throws FormValidationException {
		validate();
		setFilterData(mapFormToFilterDataModel());
	}

	public int getHeight() {
		return 550;
	}

	public int getWidth() {
		return 360;
	}

	public abstract void createUIContent(EditorPanel parent);

	public abstract F mapFormToFilterDataModel();

}
