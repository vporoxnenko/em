package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class TextEntityField extends
		AbstractEntityField<String, TextField<String>> {

	public TextEntityField(String fieldId,
			String shortName) {
		super(new TextField<String>(), fieldId, shortName);
	}

	public TextEntityField(String fieldId,
			String shortName, int columnWidth) {
		super(new TextField<String>(), fieldId, shortName, columnWidth);
	}

	public TextEntityField(String fieldId,
			String longName, String shortName, int columnWidth,
			boolean allowBlanks) {
		super(new TextField<String>(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}

	public TextEntityField() {
		super(new TextField<String>());
	}

}
