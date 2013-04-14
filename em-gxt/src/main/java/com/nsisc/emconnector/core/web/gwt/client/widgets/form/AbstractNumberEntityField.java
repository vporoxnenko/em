package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.NumberField;

public abstract class AbstractNumberEntityField<N extends Number> extends
		AbstractEntityField<N, NumberField> {

	public AbstractNumberEntityField(String fieldId, String shortName) {
		super(new NumberField(), fieldId, shortName);
	}

	public AbstractNumberEntityField(String fieldId, String shortName, int columnWidth) {
		super(new NumberField(), fieldId, shortName, columnWidth);
	}

	public AbstractNumberEntityField(String fieldId, String longName, String shortName,
			int columnWidth, boolean allowBlanks) {
		super(new NumberField(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}

	public AbstractNumberEntityField() {
		super(new NumberField());
	}

	public N getDataValue() {
		Number value = getEditorFieldWidget().getValue(); 
		if (value == null)
			return null;
		else
			return getCastedValue(value);
	}

	public abstract N getCastedValue(Number value);
}
