package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.TextArea;

public class TextAreaEntityField extends AbstractEntityField<String, TextArea> {

	public TextAreaEntityField(String fieldId,
			String shortName) {
		super(new TextArea(), fieldId, shortName);
	}

	public TextAreaEntityField(String fieldId,
			String shortName, int columnWidth) {
		super(new TextArea(), fieldId, shortName, columnWidth);
	}

	public TextAreaEntityField(String fieldId,
			String longName, String shortName, int columnWidth,
			boolean allowBlanks) {
		super(new TextArea(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}
	
	public TextAreaEntityField() {
		super(new TextArea());
	}

}
