/**
 * 
 */
package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

/**
 * @author Valentin A. Poroxnenko
 *
 * creation date: Feb 21, 2011
 */
public class DoubleEntityField extends AbstractNumberEntityField<Double> {

	public DoubleEntityField(String fieldId, String shortName) {
		super(fieldId, shortName);
	}

	public DoubleEntityField(String fieldId, String shortName, int columnWidth) {
		super(fieldId, shortName, columnWidth);
	}

	public DoubleEntityField(String fieldId, String longName, String shortName,
			int columnWidth, boolean allowBlanks) {
		super(fieldId, longName, shortName, columnWidth,
				allowBlanks);
	}

	public DoubleEntityField() {
		super();
	}
	
	@Override
	public Double getCastedValue(Number value) {
		return value.doubleValue();
	}

}
