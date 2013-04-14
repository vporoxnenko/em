package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.HtmlEditor;

public class HtmlAreaEntityField extends AbstractEntityField<String, HtmlEditor> {

	public HtmlAreaEntityField(String fieldId,
			String shortName) {
		super(new HtmlEditor(), fieldId, shortName);
	}

	public HtmlAreaEntityField(String fieldId,
			String shortName, int columnWidth) {
		super(new HtmlEditor(), fieldId, shortName, columnWidth);
	}

	public HtmlAreaEntityField(String fieldId,
			String longName, String shortName, int columnWidth,
			boolean allowBlanks) {
		super(new HtmlEditor(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}
	
	public HtmlAreaEntityField() {
		super(new HtmlEditor());
	}

	@Override
	public String getDataValue() {
		return (String) getEditorFieldWidget().getValue();
	}

	@Override
	public void setDataValue(String dataValue) {
		getEditorFieldWidget().setValue(dataValue);
	}

}
