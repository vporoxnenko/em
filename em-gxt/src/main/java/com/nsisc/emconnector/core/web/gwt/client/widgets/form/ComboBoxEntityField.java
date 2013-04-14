package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.util.List;

import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;

public class ComboBoxEntityField extends AbstractEntityField<String, SimpleComboBox<String>> {

	
	public ComboBoxEntityField() {
		super(new SimpleComboBox<String>());
	}

	@Override
	public String getDataValue() {
		return (String) getEditorFieldWidget().getSimpleValue();
	}

	@Override
	public void setDataValue(String dataValue) {
		getEditorFieldWidget().setSimpleValue(dataValue);
	}
	
	public void clear() {
		getEditorFieldWidget().removeAll();
	}
	
	public void addOptions(List<String> items) {
		getEditorFieldWidget().add(items);
	}
	
}
