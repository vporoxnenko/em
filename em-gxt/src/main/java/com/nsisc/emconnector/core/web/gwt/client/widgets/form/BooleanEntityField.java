package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class BooleanEntityField extends AbstractEntityField<Boolean, CheckBox> {

	public BooleanEntityField(String fieldId,
			String shortName) {
		super(new CheckBox(), fieldId, shortName);
	}

	public BooleanEntityField(String fieldId,
			String shortName, int columnWidth) {
		super(new CheckBox(), fieldId, shortName, columnWidth);
	}

	public BooleanEntityField(String fieldId,
			String longName, String shortName, int columnWidth,
			boolean allowBlanks) {
		super(new CheckBox(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}
	
	public BooleanEntityField() {
		super(new CheckBox());
	}

}
