/**
 * 
 */
package com.nsisc.emconnector.core.web.gwt.client.widgets.form;


/**
 * @author Valentin A. Poroxnenko
 *
 * creation date: Feb 21, 2011
 */
public class IntegerEntityField extends AbstractNumberEntityField<Integer> {

	public IntegerEntityField(String fieldId, String shortName) {
		super(fieldId, shortName);
	}

	public IntegerEntityField(String fieldId, String shortName, int columnWidth) {
		super(fieldId, shortName, columnWidth);
	}

	public IntegerEntityField(String fieldId, String longName, String shortName,
			int columnWidth, boolean allowBlanks) {
		super(fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}

	public IntegerEntityField() {
		super();
	}
	
	@Override
	public Integer getCastedValue(Number value) {
		return value.intValue();
	}

}
