package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.util.Date;

import com.extjs.gxt.ui.client.widget.form.DateField;

public class DateEntityField extends AbstractEntityField<Date, DateField> {

	public DateEntityField(String fieldId,
			String shortName) {
		super(new DateField(), fieldId, shortName);
	}

	public DateEntityField(String fieldId,
			String shortName, int columnWidth) {
		super(new DateField(), fieldId, shortName, columnWidth);
	}

	public DateEntityField(String fieldId,
			String longName, String shortName, int columnWidth,
			boolean allowBlanks) {
		super(new DateField(), fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}
	
	public DateEntityField() {
		super(new DateField());
	}

}
