package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.io.Serializable;

import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityBrowser;


public class BrowserEntityField<D extends DefaultClientDataModel<? extends Serializable>> extends
		AbstractEntityField<D, BrowserEntityFieldWidget<D,? extends EntityBrowser<? extends UIContainer, D, ? extends FilterData, ? extends DataModelWidgetManager>>> {

	public BrowserEntityField(BrowserEntityFieldWidget<D, ? extends EntityBrowser<? extends UIContainer, D, ? extends FilterData, ? extends DataModelWidgetManager>> editorField) {
		super(editorField);
		setColumnWidth(100);
	}

	@Override
	public D getDataValue() {
		return getEditorFieldWidget().getDataModel();
	}

	@Override
	public void setDataValue(D dataValue) {
		getEditorFieldWidget().setDataModel(dataValue);
	}


}
